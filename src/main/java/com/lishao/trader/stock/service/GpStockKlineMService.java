package com.lishao.trader.stock.service;

import com.lishao.trader.stock.bean.entity.GpStockKlineM;

public interface GpStockKlineMService {
	public GpStockKlineM selectLastStockKlineMByStockCode(String stockCode);
}
