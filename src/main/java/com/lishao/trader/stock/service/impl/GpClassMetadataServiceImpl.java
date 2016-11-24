package com.lishao.trader.stock.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lishao.trader.stock.bean.entity.GpClassMetadata;
import com.lishao.trader.stock.dao.GpClassMetadataMapper;
import com.lishao.trader.stock.service.GpClassMetadataService;

@Service
public class GpClassMetadataServiceImpl implements GpClassMetadataService {
	@Resource
	GpClassMetadataMapper stockClassMetadataMapper;

	public List<GpClassMetadata> selectByDimensionCode(String dimensionCode){
		return stockClassMetadataMapper.selectByDimensionCode(dimensionCode);
	}
	
	public List<GpClassMetadata> selectAll(){
		return stockClassMetadataMapper.selectAll();
	}
}
