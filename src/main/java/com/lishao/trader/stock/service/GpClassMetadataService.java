package com.lishao.trader.stock.service;

import java.util.List;

import com.lishao.trader.stock.bean.entity.GpClassMetadata;

public interface GpClassMetadataService {
	public List<GpClassMetadata> selectByDimensionCode(String dimensionCode);
}
