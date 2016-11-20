package com.lishao.trader.stock.service;

import java.util.Map;

import com.lishao.trader.stock.bean.entity.GpStockKlineD;

public interface GpStockKlineDService {
	public GpStockKlineD selectLastStockKlineDByStockCode(String stockCode);
	public GpStockKlineD selectOneKLineD(String stockCode,String periodCode);
	public int updateTradeDetailCount(Map paraMap);
	public boolean checkHasSumTradeDetail(String stockCode,String periodCode);//校验是否已经合计交易明细
}
