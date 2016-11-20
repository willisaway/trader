package com.lishao.trader.stock.service;

import java.util.List;

import com.lishao.trader.stock.bean.entity.GpStockMetadata;

public interface GpStockMetadataService {
	public List<GpStockMetadata> selectAll();
	public void saveAll(List<GpStockMetadata> stockList);
	public GpStockMetadata selectByPrimaryKey(String stockCodeFull);
	public int insertSelective(GpStockMetadata record);
	public int updateByPrimaryKeySelective(GpStockMetadata record);
}
