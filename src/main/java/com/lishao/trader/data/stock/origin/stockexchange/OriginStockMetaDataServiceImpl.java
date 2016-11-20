package com.lishao.trader.data.stock.origin.stockexchange;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lishao.system.utils.DateUtil;
import com.lishao.system.utils.HttpUtil;
import com.lishao.system.utils.ModuleReturn;
import com.lishao.system.utils.NumberUtil;
import com.lishao.trader.data.stock.origin.OriginDataService;

@Component
public class OriginStockMetaDataServiceImpl{
	
	//取上海证券交易所的股票数据
	public List<Map> getStockShMetaData(){
		List<Map> mapList = new ArrayList<Map>();
		try {
			String url="http://query.sse.com.cn/security/stock/downloadStockListFile.do?csrcCode=&stockCode=&areaName=&stockType=1";
			InputStream is = HttpUtil.getInputStream(url);
			BufferedReader buffer=null;
			buffer = new BufferedReader(new InputStreamReader(is,"GBK"));
			String strLine = null;
			for(int i=0;(strLine=buffer.readLine())!=null;i++){
				String [] strArray = strLine.split("\t");
				if(i>0){
					Map map = new HashMap();
					map.put("stockCodeFull", "sh"+strArray[2].trim());
					map.put("stockCode", strArray[2].trim());
					map.put("stockName", strArray[3].trim());
					map.put("listingDate", DateUtil.formatPeriodCodeD(strArray[4].trim()));
					map.put("totalStockNum", NumberUtil.parseDouble(strArray[5].trim()));
					map.put("tradableStockNum", NumberUtil.parseDouble(strArray[6].trim()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return mapList;
	}
	
	public static void main(String []args){
		OriginStockMetaDataServiceImpl originStockMetaDataServiceImpl=new OriginStockMetaDataServiceImpl();
		originStockMetaDataServiceImpl.getStockShMetaData();
	}
}
