package com.lishao.trader.stock.service;

import com.lishao.trader.stock.bean.entity.GpClassKlineM;

public interface GpClassKlineMService {
	public GpClassKlineM selectLastClassKlineMByClassifyCode(String classifyCode);
}
