package com.lishao.trader.stock.service;

import com.lishao.trader.stock.bean.entity.GpStockKlineW;

public interface GpStockKlineWService {
	public GpStockKlineW selectLastStockKlineWByStockCode(String stockCode);
}
