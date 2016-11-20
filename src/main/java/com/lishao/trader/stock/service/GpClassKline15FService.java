package com.lishao.trader.stock.service;

import com.lishao.trader.stock.bean.entity.GpClassKline15F;

public interface GpClassKline15FService {
	public GpClassKline15F selectLastClassKline15FByClassifyCode(String classifyCode);
}
