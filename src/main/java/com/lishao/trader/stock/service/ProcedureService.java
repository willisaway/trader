package com.lishao.trader.stock.service;

import java.util.List;
import java.util.Map;

public interface ProcedureService {
	public void prcStockKlineDDownload(Map paraMap);
	public void prcStockKlineDDownload(List<Map> paraMapList);
	public void prcStockKlineWDownload(Map paraMap);
	public void prcStockKlineWDownload(List<Map> paraMapList);
	public void prcStockKlineMDownload(Map paraMap);
	public void prcStockKlineMDownload(List<Map> paraMapList);
	public void prcStockKline15FDownload(Map paraMap);
	public void prcStockKline15FDownload(List<Map> paraMapList);
	public void prcStockKline30FDownload(Map paraMap);
	public void prcStockKline30FDownload(List<Map> paraMapList);
	public void prcStockKline60FDownload(Map paraMap);
	public void prcStockKline60FDownload(List<Map> paraMapList);
	public void prcClassKlineDDownload(Map paraMap);
	public void prcClassKlineDDownload(List<Map> paraMapList);
	public void prcClassKlineWDownload(Map paraMap);
	public void prcClassKlineWDownload(List<Map> paraMapList);
	public void prcClassKlineMDownload(Map paraMap);
	public void prcClassKlineMDownload(List<Map> paraMapList);
	public void prcClassKline15FDownload(Map paraMap);
	public void prcClassKline15FDownload(List<Map> paraMapList);
	public void prcClassKline30FDownload(Map paraMap);
	public void prcClassKline30FDownload(List<Map> paraMapList);
	public void prcClassKline60FDownload(Map paraMap);
	public void prcClassKline60FDownload(List<Map> paraMapList);
}
