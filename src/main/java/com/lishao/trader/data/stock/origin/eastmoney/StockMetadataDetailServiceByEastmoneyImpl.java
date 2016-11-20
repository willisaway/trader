package com.lishao.trader.data.stock.origin.eastmoney;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lishao.system.utils.DateUtil;
import com.lishao.system.utils.HttpUtil;
import com.lishao.trader.data.stock.origin.StockMetadataDetailService;

@Component
public class StockMetadataDetailServiceByEastmoneyImpl implements StockMetadataDetailService {

	@Override
	public Date getListingDate(String stockCodeFull) {
		Date listingDate = null;
		String url="http://quote.eastmoney.com/{stockCodeFull}.html";
		url = url.replace("{stockCodeFull}", stockCodeFull);
		try {
			String htmlString = HttpUtil.getInputHtml(url,"GBK");
			if(htmlString.indexOf("上市时间：")>0){
				int beginIndex=htmlString.indexOf("上市时间：")+5;
				String strDate = htmlString.substring(beginIndex, beginIndex+10);
				listingDate = DateUtil.formatPeriodCodeD(strDate);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listingDate;
	}

	@Override
	public Map getStockMetadataDetail(String stockCodeFull) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String []args){
		StockMetadataDetailService test = new StockMetadataDetailServiceByEastmoneyImpl();
		test.getListingDate("sz002811");
	}
}
