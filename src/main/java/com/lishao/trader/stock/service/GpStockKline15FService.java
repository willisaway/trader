package com.lishao.trader.stock.service;

import com.lishao.trader.stock.bean.entity.GpStockKline15F;

public interface GpStockKline15FService {
	public GpStockKline15F selectLastStockKline15FByStockCode(String stockCode);
}
