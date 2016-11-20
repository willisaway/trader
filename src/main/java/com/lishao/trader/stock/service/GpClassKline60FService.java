package com.lishao.trader.stock.service;

import com.lishao.trader.stock.bean.entity.GpClassKline60F;

public interface GpClassKline60FService {
	public GpClassKline60F selectLastClassKline60FByClassifyCode(String classifyCode);
}
