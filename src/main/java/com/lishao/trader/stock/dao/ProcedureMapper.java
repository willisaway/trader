package com.lishao.trader.stock.dao;

import java.util.Map;

public interface ProcedureMapper {
	public Map prcStockKlineDDownload(Map paraMap);
	public Map prcStockKlineWDownload(Map paraMap);
	public Map prcStockKlineMDownload(Map paraMap);
	public Map prcStockKline15FDownload(Map paraMap);
	public Map prcStockKline30FDownload(Map paraMap);
	public Map prcStockKline60FDownload(Map paraMap);
	public Map prcClassKlineDDownload(Map paraMap);
	public Map prcClassKlineWDownload(Map paraMap);
	public Map prcClassKlineMDownload(Map paraMap);
	public Map prcClassKline15FDownload(Map paraMap);
	public Map prcClassKline30FDownload(Map paraMap);
	public Map prcClassKline60FDownload(Map paraMap);
}
