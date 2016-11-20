package com.lishao.trader.stock.service;

import com.lishao.trader.stock.bean.entity.GpClassKlineD;

public interface GpClassKlineDService {
	public GpClassKlineD selectLastClassKlineDByClassifyCode(String classifyCode);
}
