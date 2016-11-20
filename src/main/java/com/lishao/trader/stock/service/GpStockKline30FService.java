package com.lishao.trader.stock.service;

import com.lishao.trader.stock.bean.entity.GpStockKline30F;

public interface GpStockKline30FService {
	public GpStockKline30F selectLastStockKline30FByStockCode(String stockCode);
}
