package com.lishao.trader.stock.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lishao.trader.stock.bean.entity.GpStockClassMap;
import com.lishao.trader.stock.bean.entity.GpStockClassMapKey;
import com.lishao.trader.stock.dao.GpStockClassMapMapper;
import com.lishao.trader.stock.service.GpStockClassMapService;

@Service
public class GpStockClassMapServiceImpl implements GpStockClassMapService {
	@Resource
	GpStockClassMapMapper stockClassMapMapper;
	
	public GpStockClassMap selectByPrimaryKey(String classifyCode,String stockCode){
		GpStockClassMapKey key = new GpStockClassMapKey();
		key.setClassifyCode(classifyCode);key.setStockCode(stockCode);
		return stockClassMapMapper.selectByPrimaryKey(key);
	}
	
	public List<GpStockClassMap> selectByClassifyCode(String classifyCode){
		GpStockClassMapKey key = new GpStockClassMapKey();
		key.setClassifyCode(classifyCode);
		return stockClassMapMapper.selectByClassifyCode(key);
	}
	
	public int deleteByClassifyCode(String classifyCode){
		GpStockClassMap record = new GpStockClassMap();
		record.setClassifyCode(classifyCode);record.setLastUpdDate(new Date());
		return stockClassMapMapper.deleteByClassifyCode(record);
	}
	
	public int updateByPrimaryKeySelective(GpStockClassMap record){
		return stockClassMapMapper.updateByPrimaryKeySelective(record);
	}
	public int insertSelective(GpStockClassMap record){
		return stockClassMapMapper.insertSelective(record);
	}
}
