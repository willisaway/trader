package com.lishao.trader.data.stock.origin.sina;

import java.util.Date;
import java.util.Map;

import com.lishao.system.utils.DateUtil;
import com.lishao.system.utils.HttpUtil;
import com.lishao.trader.data.stock.origin.StockMetadataDetailService;

public class StockMetadataDetailServiceBySinaImpl{

	public Date getListingDate(String stockCodeFull) {
		Date listingDate = null;
		String url="http://finance.sina.com.cn/realstock/company/{stockCodeFull}/nc.shtml";
		url = url.replace("{stockCodeFull}", stockCodeFull);
		try {
			String htmlString = HttpUtil.getInputHtml(url,"GBK");
			if(htmlString.indexOf("上市日期：</b>")>0){
				int beginIndex=htmlString.indexOf("上市日期：</b>")+9;
				String strDate = htmlString.substring(beginIndex, beginIndex+10);
				listingDate = DateUtil.formatPeriodCodeD(strDate);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return listingDate;
	}

	public Map getStockMetadataDetail(String stockCodeFull){
		return null;
	}
	
	public static void main(String []args){
		StockMetadataDetailServiceBySinaImpl test = new StockMetadataDetailServiceBySinaImpl();
		test.getListingDate("sh600647");
	}
}
