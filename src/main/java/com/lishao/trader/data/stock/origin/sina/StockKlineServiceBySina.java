package com.lishao.trader.data.stock.origin.sina;

import static com.lishao.system.utils.ConfigUtil.getProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.lishao.system.utils.HttpUtil;
import com.lishao.system.utils.NumberUtil;
import com.lishao.trader.data.stock.origin.StockKlineService;
import com.lishao.trader.data.stock.origin.sina.bean.KLineBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
public class StockKlineServiceBySina implements StockKlineService{
	Logger logger = Logger.getLogger(StockKlineServiceBySina.class);
	/** 重试次数 默认10 */
	public static int RETRY_COUNT = Integer.parseInt(getProperty("retryCount"));
	/** 重试等待时间 默认3s */
	public static int RETRY_DELAY = Integer.parseInt(getProperty("retryDelay"));
	@Override
	public List<Map> getKLineD(String objectCode,String objectCodeFull,String startPeriod) {
		return getKLine(objectCode,objectCodeFull, "240",startPeriod);
	}
	@Override
	public List<Map> getKLineW(String objectCode,String objectCodeFull,String startPeriod) {
		return getKLine(objectCode,objectCodeFull, "1200",startPeriod);
	}

	@Override
	public List<Map> getKLineM(String objectCode,String objectCodeFull,String startPeriod) {
		return getKLine(objectCode,objectCodeFull, "7200",startPeriod);
	}

	@Override
	public List<Map> getKLine15F(String objectCode,String objectCodeFull,String startPeriod) {
		return getKLine(objectCode,objectCodeFull, "15",startPeriod);
	}

	@Override
	public List<Map> getKLine30F(String objectCode,String objectCodeFull,String startPeriod) {
		return getKLine(objectCode,objectCodeFull, "30",startPeriod);
	}

	@Override
	public List<Map> getKLine60F(String objectCode,String objectCodeFull,String startPeriod) {
		return getKLine(objectCode,objectCodeFull, "60",startPeriod);
	}

	public List<Map> getKLine(String objectCode,String objectCodeFull, String scale,String startPeriod){
		List<Map> sinaList=null;
		String htmlString=getKLine(objectCode, objectCodeFull, scale);
		if(htmlString!=null&&!"null".equalsIgnoreCase(htmlString)){
			JSONArray jsonArray = JSONArray.fromObject(htmlString.toString());
			sinaList = (List<Map>)JSONArray.toList(jsonArray, Map.class);
		}
		List<Map> beforeList=new ArrayList<Map>();//在startPeriod之前的list
		List<Map> afterList=new ArrayList<Map>();//在startPeriod之后的list
		List<Map> returnList=beforeList;
		if(sinaList!=null){
			for(int i=0;i<sinaList.size();i++){
				Map sinaMap = sinaList.get(i);
				//校验有效性，比如sz399908存在这种数据{day:"2002-01-04",open:"0.000",high:"0.000",low:"0.000",close:"939.014",volume:"0"},{day:"2002-01-11",open:"0.000",high:"0.000",low:"0.000",close:"895.955",volume:"0"}
				if(NumberUtil.parseDouble(sinaMap.get("open").toString())==0
						||NumberUtil.parseDouble(sinaMap.get("high").toString())==0
						||NumberUtil.parseDouble(sinaMap.get("low").toString())==0
						||NumberUtil.parseDouble(sinaMap.get("close").toString())==0){
					continue;
				}
				Map returnMap = new HashMap();
				returnMap.put("objectCode", objectCode);
				returnMap.put("periodCode", sinaMap.get("day"));
				returnMap.put("priceOpen", NumberUtil.parseDouble(sinaMap.get("open").toString()));
				returnMap.put("priceHigh", NumberUtil.parseDouble(sinaMap.get("high").toString()));
				returnMap.put("priceLow", NumberUtil.parseDouble(sinaMap.get("low").toString()));
				returnMap.put("priceClose", NumberUtil.parseDouble(sinaMap.get("close").toString()));
				returnMap.put("tradeVolumn", NumberUtil.parseDouble(sinaMap.get("volume").toString()));
				returnMap.put("tradeAmount", NumberUtil.parseDouble(sinaMap.get("volume").toString()));
				returnMap.put("fuquan", 0);
				if(i==(sinaList.size()-1)){
					returnMap.put("originCode", "T");
				}else{
					returnMap.put("originCode", "H");
				}
				if(startPeriod!=null&&startPeriod.equals(sinaMap.get("day"))){
					returnList=afterList;
				}
				returnList.add(returnMap);
			}
		}
		return returnList;
	}
	
	public String getKLine(String objectCode,String objectCodeFull, String scale) {
		String url = "http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol={objectCodeFull}&scale={scale}&ma=no&datalen=1023";
		url=url.replace("{objectCodeFull}", objectCodeFull);
		url=url.replace("{scale}", scale);
		String htmlString = null;
		for (int i = 0; i < RETRY_COUNT; i++) {/** 重试次数 默认10 */
			try {
				htmlString = HttpUtil.getInputHtml(url);
				if (i > 0) {
					logger.info("第" +  (i+1) + "次读取" + objectCodeFull + " scale=" + scale);
					Thread.sleep(RETRY_DELAY);// 控制频率
				}
				if (htmlString !=null && htmlString.length() > 0) {
					break;
				}
			} catch (Exception e) {
				logger.info("发送请求异常，classifyCode："+objectCodeFull+",scale:"+scale);
				e.printStackTrace();
			}
		}
		return htmlString;
	}

	public static void main(String []args){
		new StockKlineServiceBySina().getKLine("399006", "sz399006", "30", "2016-11-14 10:30:00");
		System.out.println(NumberUtil.parseDouble("0.00")==0);
	}
}
