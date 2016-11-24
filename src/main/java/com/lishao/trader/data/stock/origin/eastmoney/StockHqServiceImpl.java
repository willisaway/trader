package com.lishao.trader.data.stock.origin.eastmoney;

import static com.lishao.system.utils.ConfigUtil.getProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.lishao.system.component.threadpool.ThreadPoolManager;
import com.lishao.system.utils.HttpUtil;
import com.lishao.trader.data.stock.origin.StockHqService;
import com.lishao.trader.data.stock.origin.eastmoney.callback.StockHqByClassCallBack;
import com.lishao.trader.stock.bean.entity.GpStockClassMap;

import net.sf.json.JSONObject;

@Component
public class StockHqServiceImpl implements StockHqService {
	Logger logger = Logger.getLogger(StockHqServiceImpl.class);
	
	/**
	 * 根据指数代码取成分股及行情
	 */
	@Override
	public List<String> getStockHqListByClassifyCode(String classifyCode,StockHqByClassCallBack callback) {
		long page=1;
		long pageSize=20;
		long totalPage=1;
		List<String> stockHqList = new ArrayList<String>();
		for(;page<=totalPage;page++){
			Map jsonMap=getStockHqListByClassifyCodeByPage(classifyCode,page,pageSize);
			if(jsonMap!=null){
				totalPage=Long.parseLong(jsonMap.get("pages")+"");
				List<String> tmpString = (List<String>)jsonMap.get("rank");
				stockHqList.addAll(tmpString);
			}
		}
		//调用回调方法
		if(callback!=null&&stockHqList.size()>0){
			callback.setStockHqList(stockHqList);
			ThreadPoolManager.addTask(callback);
		}
		return stockHqList;
	}
	/**
	 * 根据指数代码取成分股及行情(分页)
	 * http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C.IE0000011&sty=FCOIATA&sortType=C&sortRule=-1&page=2&pageSize=20&js=var%20quote_123%3d{rank:[(x)],pages:(pc)}&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.5809072624812357
	 * http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C.IE3999952&sty=FCOIATA&sortType=C&sortRule=-1&page=2&pageSize=20&js=var%20quote_123%3d{rank:[(x)],pages:(pc)}&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.9010959551724071
	 */
	public Map getStockHqListByClassifyCodeByPage(String classifyCode,long page,long pageSize){
		long time = new Date().getTime();
		String url="http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C.IE{classifyCode}{classifyType}&sty=FCOIATA&sortType=C&sortRule=-1&page={page}&pageSize={pageSize}&js=var%20quote_123%3d{rank:[(x)],pages:(pc)}&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.08016937637874744"+time;
		url=url.replace("{classifyCode}", classifyCode);
		url=url.replace("{page}", page+"");url=url.replace("{pageSize}", pageSize+"");
		String classifyType="";
		if(classifyCode.startsWith("00")){
			classifyType="1";//上海
		}else if(classifyCode.startsWith("399")){
			classifyType="2";//深圳
		}
		url=url.replace("{classifyType}", classifyType);
		Map jsonMap=null;
		try {
			String jsonStr = HttpUtil.getInputHtmlWithRetry(url);
			jsonStr = jsonStr.substring(jsonStr.indexOf("{"));
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			jsonMap = (Map)JSONObject.toBean(jsonObject, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMap;
	}
	/**
	 * 根据指数代码取成分股,暂不使用
	 */
	@Override
	public List<GpStockClassMap> getStockClassMap(String classifyCode) {
		List<String> stockHqList = getStockHqListByClassifyCode(classifyCode,null);
		List<GpStockClassMap> returnList = new ArrayList<GpStockClassMap>();
		for(String stockHq:stockHqList){
			String[] stockCols = stockHq.split(",");
			GpStockClassMap bean = new GpStockClassMap();
			bean.setClassifyCode(classifyCode);
			bean.setStockCode(stockCols[1]);
			returnList.add(bean);
		}
		return returnList;
	}

}
