package com.lishao.trader.stock.service;

import com.lishao.trader.stock.bean.entity.GpClassKline30F;

public interface GpClassKline30FService {
	public GpClassKline30F selectLastClassKline30FByClassifyCode(String classifyCode);
}
