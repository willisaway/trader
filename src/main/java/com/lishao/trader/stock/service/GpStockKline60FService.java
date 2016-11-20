package com.lishao.trader.stock.service;

import com.lishao.trader.stock.bean.entity.GpStockKline60F;

public interface GpStockKline60FService {
	public GpStockKline60F selectLastStockKline60FByStockCode(String stockCode);
}
