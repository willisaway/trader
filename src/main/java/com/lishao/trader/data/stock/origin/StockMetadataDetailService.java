package com.lishao.trader.data.stock.origin;

import java.util.Date;
import java.util.Map;

/**
 * 股票详情数据
 * @author ibm
 *
 */
public interface StockMetadataDetailService {
	/**
	 * 获取上市日期
	 * @param stockCode
	 * @return
	 */
	public Date getListingDate(String stockCodeFull);
	public Map getStockMetadataDetail(String stockCodeFull);
}
