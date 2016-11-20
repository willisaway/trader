package com.lishao.trader.stock.service;

import java.util.List;

import com.lishao.trader.stock.bean.entity.GpStockClassMap;
import com.lishao.trader.stock.bean.entity.GpStockClassMapKey;

public interface GpStockClassMapService {
	public GpStockClassMap selectByPrimaryKey(String classifyCode,String stockCode);
	public List<GpStockClassMap> selectByClassifyCode(String classifyCode);
	public int deleteByClassifyCode(String classifyCode);
	public int updateByPrimaryKeySelective(GpStockClassMap record);
	public int insertSelective(GpStockClassMap record);
}
