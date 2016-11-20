package com.lishao.trader.stock.service;

import com.lishao.trader.stock.bean.entity.GpClassKlineW;

public interface GpClassKlineWService {
	public GpClassKlineW selectLastClassKlineWByClassifyCode(String classifyCode);
}
