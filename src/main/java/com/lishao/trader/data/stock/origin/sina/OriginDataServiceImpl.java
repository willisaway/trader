package com.lishao.trader.data.stock.origin.sina;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.springframework.stereotype.Component;

import static com.lishao.system.utils.ConfigUtil.*;

import com.lishao.system.component.threadpool.ThreadPoolManager;
import com.lishao.system.utils.HttpUtil;
import com.lishao.system.utils.ModuleReturn;
import com.lishao.system.utils.NumberUtil;
import com.lishao.system.utils.StringUtil;
import com.lishao.system.utils.TimeUtil;
import com.lishao.trader.common.ConstantUtil;
import com.lishao.trader.data.stock.origin.OriginDataService;
import com.lishao.trader.data.stock.origin.sina.bean.StockAll;
import com.lishao.trader.data.stock.origin.sina.callback.StockAllCallBack;
import com.lishao.trader.stock.bean.entity.GpStockClassMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 从新浪取数据的实现类
 * @author lishao
 *
 */
@Component
public class OriginDataServiceImpl implements OriginDataService{
	Logger log = Logger.getLogger(OriginDataServiceImpl.class);
	/** 重试次数 默认10 */
	public static int RETRY_COUNT = Integer.parseInt(getProperty("retryCount"));
	/** 重试等待时间 默认3s */
	public static int RETRY_DELAY = Integer.parseInt(getProperty("retryDelay"));
	
	//取股票日线数据
	public List<Map> getStockFuquanKlineD(String objectCode,Calendar calendar){
		return getKlineD(ConstantUtil.objectTypeStock,objectCode,calendar);
	}
	//取基金日线数据
	public List<Map> getFundKlineD(String objectCode,Calendar calendar){
		return getKlineD(ConstantUtil.objectTypeFund,objectCode,calendar);
	}
	//取指数日线数据
	public List<Map> getClassKlineD(String objectCode,Calendar calendar){
		return getKlineD(ConstantUtil.objectTypeStockClass,objectCode,calendar);
	}
	public List<Map> getKlineD(String type,String stockCode,Calendar calendar){
		int year = calendar.get(Calendar.YEAR);
		int season = TimeUtil.getSeason(calendar);
		Calendar calendarNow = Calendar.getInstance();
		calendarNow.setTimeInMillis(System.currentTimeMillis());
		List<Map> resMapList = new ArrayList<Map>();
		while(true){
			List<Map> tmpMapList = getFuquanKlineD(type,stockCode, year, season);
			resMapList.addAll(tmpMapList);
			//停止条件
			if(year==calendarNow.get(Calendar.YEAR)&&season==TimeUtil.getSeason(calendarNow)){
				break;
			}
			if(season==4){
				season=1;
				year++;
			}else{
				season++;
			}
		}
		return resMapList;
	}
	
	public List<Map> getFuquanKlineD(String type,String objectCode,int year,int season){
		String htmlString = null;
		for (int i = 0; i < RETRY_COUNT; i++) {/** 重试次数 默认10 */
			try {
				htmlString = HttpUtil.getInputHtml(getKLineDUrl(type, objectCode, year, season));
				if (i > 0) {
					log.info("第" +  (i+1) + "次读取" + objectCode + " year=" + year + "&jidu=" + season);
					Thread.sleep(RETRY_DELAY);// 控制频率
				}
				if (htmlString !=null && htmlString.length() > 0) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		List<Map> resMapList = getFuquanKlineD(type,objectCode,htmlString);
		return resMapList;
	}
	
	public String getKLineDUrl(String type,String objectCode,int year,int season){
		//http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_FuQuanMarketHistory/stockid/300228.phtml?year=2013&jidu=2
		//http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/150029.phtml?year=2013&jidu=2
		//http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/000001/type/S.phtml?year=2015&jidu=2
		String url="";
		if(ConstantUtil.objectTypeStock.equals(type)){
			String URL = "http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_FuQuanMarketHistory/stockid/";
			url = URL + objectCode + ".phtml?year=" + year + "&jidu=" + season;
		}else if(ConstantUtil.objectTypeFund.equals(type)){
			String URL = "http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/";
			url = URL + objectCode + ".phtml?year=" + year + "&jidu=" + season;
		}else if(ConstantUtil.objectTypeStockClass.equals(type)){
			String URL = "http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/";
			url = URL + objectCode + "/type/S.phtml?year=" + year + "&jidu=" + season;
		}
		return url;
	}
	
	/**
	 * 解析html页面
	 * @param stockCode
	 * @param htmlString
	 * @return
	 */
	public List<Map> getFuquanKlineD(String type,String objectCode,String htmlString){
		List<Map> resMapList = new ArrayList<Map>();
		Parser myParser;
		try {
			myParser = new Parser(htmlString);
			myParser.setEncoding("UTF-8");
			
			NodeFilter tableFilter = new NodeClassFilter(TableTag.class);
			OrFilter lastFilter = new OrFilter();
			lastFilter.setPredicates(new NodeFilter[] { tableFilter });
			NodeList nodeList = myParser.parse(lastFilter);
			for (int i = 0; i <= nodeList.size(); i++){
				if (nodeList.elementAt(i) instanceof TableTag) {
					TableTag tag = (TableTag) nodeList.elementAt(i);
					if (!tag.getText().contains("id=\"FundHoldSharesTable\"")) {
						continue;
					}
					TableRow[] rows = tag.getRows();
					for (int j = 2; j < rows.length; j++) {
						TableRow tr = (TableRow) rows[j];
						Map dataFromSina = createObjectFuQuanMarketHistory(type,objectCode,tr);
						if (dataFromSina != null && dataFromSina.get("periodCode") != null) {
							resMapList.add(dataFromSina);
						}
					}
				}
			}
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resMapList;
	}
	
	/**
	 * 
	 * @param type 1-股票;2-指数
	 * @param objectCode
	 * @param tr
	 * @return
	 */
	public static Map createObjectFuQuanMarketHistory(String type,String objectCode,TableRow tr){
		Map originDataFromSinaMap = new HashMap();
		originDataFromSinaMap.put("objectCode", objectCode);
		TableColumn[] td = tr.getColumns();	
		int pos = 0;
		try {			
			//日期 开盘价 最高价 收盘价 最低价 交易量(股) 交易金额(元) 复权因子 
			originDataFromSinaMap.put("periodCode",td[pos++].toPlainTextString().trim());
			originDataFromSinaMap.put("priceOpen",NumberUtil.parseDouble(td[pos++].toPlainTextString()));//开盘价
			originDataFromSinaMap.put("priceHigh",NumberUtil.parseDouble(td[pos++].toPlainTextString()));//最高价
			originDataFromSinaMap.put("priceClose",NumberUtil.parseDouble(td[pos++].toPlainTextString()));//收盘价
			originDataFromSinaMap.put("priceLow",NumberUtil.parseDouble(td[pos++].toPlainTextString()));//最低价
			originDataFromSinaMap.put("tradeVolumn",NumberUtil.parseDouble(td[pos++].toPlainTextString()));//交易量(股)
			originDataFromSinaMap.put("tradeAmount",NumberUtil.parseDouble(td[pos++].toPlainTextString()));//交易金额(元)
			if(ConstantUtil.objectTypeStock.equals(type)){
				originDataFromSinaMap.put("fuquan",NumberUtil.parseDouble(td[pos++].toPlainTextString()));//复权因子
			}
			originDataFromSinaMap.put("originCode","H");
		}catch (Exception ex){
			ex.printStackTrace();
		}finally {			
		}
		return originDataFromSinaMap;
	}
	
	/**
	 * 取交易明细数据
	 * http://market.finance.sina.com.cn/downxls.php?date=2010-03-29&symbol=sh600718
	 */
	public ModuleReturn getTradeDetail(String type,String objectCode,String periodCode){
		ModuleReturn objReturn = new ModuleReturn(1);
		try {
			String url="http://market.finance.sina.com.cn/downxls.php?date=";
			url=url+periodCode+"&symbol="+objectCode;
			List<String[]> list = new ArrayList();
			BigDecimal amountIn=new BigDecimal(0);
			BigDecimal amountOut=new BigDecimal(0);
			BigDecimal largeAmountIn=new BigDecimal(0);
			BigDecimal largeAmountOut=new BigDecimal(0);
			long handCount=0;long avgHandCount=0;long detailRowCount=0;
			InputStream is = HttpUtil.getInputStream(url);
			if(is==null){
				objReturn.setReturnValue(-1, "取得的流数据为空");
			}
			BufferedReader buffer=null;
			if(objReturn.getReturnValue()>0){
				buffer = new BufferedReader(new InputStreamReader(is,"GBK"));
			}
			String strLine = null;
			if(objReturn.getReturnValue()>0){
				for(int i=0;(strLine=buffer.readLine())!=null;i++){
				    String [] strArray = strLine.split("\t");
				    if(i>0){
				    	if(checkTradeDetailValidate(strArray)){
				    		list.add(strArray);
					    	if(!"--".equals(strArray[2])){
					    		if(Double.valueOf(strArray[2])>0){
					    			amountIn=amountIn.add(new BigDecimal(strArray[4]));
					    		}else{
					    			amountOut=amountOut.add(new BigDecimal(strArray[4]).negate());
					    		}
					    	}
					    	handCount+=Long.parseLong(strArray[3]);
					    	detailRowCount=i;
				    	}else{
				    		objReturn.setReturnValue(-1, "当天未取到数据");
				    	}
				    }
				}
			}
			if(objReturn.getReturnValue()>0){
				//计算成交的平均手数
				avgHandCount=handCount/detailRowCount;
				//计算大单流入流出
				for(int i=0;i<list.size();i++){
					String []strArray=list.get(i);
					if(!"--".equals(strArray[2])&&Long.parseLong(strArray[3])>=avgHandCount){
						if(Double.valueOf(strArray[2])>0){
							largeAmountIn=largeAmountIn.add(new BigDecimal(strArray[4]));
						}else{
							largeAmountOut=largeAmountOut.add(new BigDecimal(strArray[4]).negate());
						}
					}
				}
				objReturn.setReturnPara("amountIn", amountIn);objReturn.setReturnPara("amountOut", amountOut);
				objReturn.setReturnPara("amountFlow", amountIn.subtract(amountOut));
				objReturn.setReturnPara("largeAmountIn", largeAmountIn);objReturn.setReturnPara("largeAmountOut", largeAmountOut);
				objReturn.setReturnPara("largeAmountFlow", largeAmountIn.subtract(largeAmountOut));
				objReturn.setReturnPara("avgHandCount", avgHandCount);
				System.out.println("流入资金:"+StringUtil.CashAddComma(amountIn.toString())+",流出资金:"+StringUtil.CashAddComma(amountOut.toString())+",平均手数:"+avgHandCount+",大单流入:"+StringUtil.CashAddComma(largeAmountIn.toString())+",大单流出："+StringUtil.CashAddComma(largeAmountOut.toString()));
				System.out.println("净流入流出:"+StringUtil.CashAddComma(amountIn.add(amountOut).toString())+",大单净流入流出:"+StringUtil.CashAddComma(largeAmountIn.add(largeAmountOut).toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			objReturn.setReturnValue(-1, "执行异常!");
			log.error(objectCode+"|"+periodCode+"|取交易明细数据执行异常,"+e.getMessage());
		}
		
		return objReturn;
	}
	/**
	 * 验证交易明细数组正确性
	 * @param strArray
	 * @return
	 */
	private boolean checkTradeDetailValidate(String [] strArray){
		boolean bResult = true;
		if(strArray==null||strArray.length<5){
			bResult=false;
		}
		return bResult;
	}
	
	/**
	 * 取所有stock的实时数据，所有stock元数据可从这儿取
	 */
	public StockAll getStockHqAll(StockAllCallBack callback){
		StockAll stockAll=null;
		String originUrl="http://money.finance.sina.com.cn/d/api/openapi_proxy.php/?__s=[[\"hq\",\"hs_a\",\"\",0,{page},{number}]]&callback=FDC_DC.theTableData";
		int number=80;//每页取数的数据
		originUrl=originUrl.replace("{number}", number+"");
		int page=1;
		int totalPage=1;
		int stockCount=1;
		try {
			for(;page<=totalPage;page++){
				String url=originUrl.replace("{page}", page+"");
				//去取数
				String jsonStr = HttpUtil.getInputHtml(url);
				jsonStr=jsonStr.substring(jsonStr.indexOf("[{")+1);
				jsonStr=jsonStr.substring(0,jsonStr.indexOf("}]")+1);
				System.out.println(jsonStr);
				JSONObject jsonObject = JSONObject.fromObject(jsonStr);
				StockAll stockTemp = (StockAll)JSONObject.toBean(jsonObject, StockAll.class);
				if(stockAll==null){
					stockAll=stockTemp;
					stockCount=stockTemp.getCount();
					//计算总页数
					if(stockCount%number==0){
						totalPage=stockCount/number;
					}else{
						totalPage=stockCount/number+1;
					}
				}else{
					stockAll.getItems().addAll(stockTemp.getItems());
				}
				//处理回调
				callback.setStockAll(stockTemp);
				ThreadPoolManager.addTask(callback);
				//处理回调完毕
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stockAll;
	}
	
	/**
	 * 取指数的成分股
	 * @param classifyCode
	 * @return
	 */
	public List<GpStockClassMap> getStockClassMap(String classifyCode){
		List<GpStockClassMap> stockClassList=new ArrayList<GpStockClassMap>();
		ModuleReturn objRtn=new ModuleReturn(1);
		do{
			objRtn = getStockClassMap(classifyCode, objRtn.getReturnValue());
			if(objRtn.getReturnValue()>0){
				stockClassList.addAll((List<GpStockClassMap>)objRtn.getReturnPara("stockClassList"));
			}
		}while(objRtn.getReturnValue()>1);//如果值大于1,则还有下一页
		return stockClassList;
	}
	public ModuleReturn getStockClassMap(String classifyCode,long page){
		ModuleReturn objRtn = new ModuleReturn(1);
		String url="http://vip.stock.finance.sina.com.cn/corp/view/vII_NewestComponent.php?page={page}&indexid={classifyCode}";
		url=url.replace("{page}", page+"");
		url=url.replace("{classifyCode}", classifyCode);
		String htmlString = null;
		for (int i = 0; i < RETRY_COUNT; i++) {/** 重试次数 默认10 */
			try {
				htmlString = HttpUtil.getInputHtml(url);
				if (i > 0) {
					System.out.println("第" +  (i+1) + "次读取" + classifyCode + " page=" + page);
					Thread.sleep(RETRY_DELAY);// 控制频率
				}
				if (htmlString !=null && htmlString.length() > 0) {
					break;
				}
			} catch (Exception e) {
				objRtn.setReturnValue(-1, "发送请求异常，classifyCode："+classifyCode+",page:"+page);
				e.printStackTrace();
			}
		}
		objRtn=getStockClassMap(classifyCode, page, htmlString);
		return objRtn;
	}
	public ModuleReturn getStockClassMap(String classifyCode,long page,String htmlString){
		ModuleReturn objRtn = new ModuleReturn(1);
		List<GpStockClassMap> stockClassList=new ArrayList<GpStockClassMap>();
		try {
			Parser myParser = new Parser(htmlString);
			myParser.setEncoding("UTF-8");
			NodeFilter tableFilter = new NodeClassFilter(TableTag.class);
			OrFilter lastFilter = new OrFilter();
			lastFilter.setPredicates(new NodeFilter[] { tableFilter });
			NodeList nodeList = myParser.parse(lastFilter);
			for (int i = 0; i <= nodeList.size(); i++){
				if (nodeList.elementAt(i) instanceof TableTag) {
					TableTag tag = (TableTag) nodeList.elementAt(i);
					if (!tag.getText().contains("id=\"NewStockTable\"")) {
						continue;
					}
					TableRow[] rows = tag.getRows();
					for (int j = 2; j < rows.length; j++) {
						TableRow tr = (TableRow) rows[j];
						GpStockClassMap bean = createStockClassObject(classifyCode,tr);
						if (bean != null && bean.getStockCode() != null) {
							stockClassList.add(bean);
						}
					}
				}
			}
			//判断是否有下一页
			if(htmlString.indexOf("下一页")>0){
				objRtn.setReturnValue(page+1);
			}else{
				objRtn.setReturnValue(1);
			}
			objRtn.setReturnPara("stockClassList", stockClassList);
		} catch (Exception e) {
			objRtn.setReturnValue(-1, "解析html异常");e.printStackTrace();
		}
		return objRtn;
	}
	public GpStockClassMap createStockClassObject(String classifyCode,TableRow tr){
		GpStockClassMap bean = new GpStockClassMap();
		TableColumn[] td = tr.getColumns();
		int pos=0;
		bean.setClassifyCode(classifyCode);
		bean.setStockCode(td[pos++].toPlainTextString().trim());
		pos++;
		return bean;
	}
}
