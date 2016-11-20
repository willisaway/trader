package com.lishao.trader.stock.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lishao.trader.stock.bean.entity.GpStockMetadata;
import com.lishao.trader.stock.dao.GpStockMetadataMapper;
import com.lishao.trader.stock.service.GpStockMetadataService;

@Service
public class GpStockMetadataServiceImpl implements GpStockMetadataService {
	@Resource
	GpStockMetadataMapper stockMetadataMapper;

	public List<GpStockMetadata> selectAll(){
		return stockMetadataMapper.selectAll();
	}
	/**
	 * 保存股票元数据
	 * @param stockList
	 */
	public void saveAll(List<GpStockMetadata> stockList){
		for(GpStockMetadata tempBean:stockList){
			GpStockMetadata record=selectByPrimaryKey(tempBean.getStockCodeFull());
			if(record==null){
				record=tempBean;
				insertSelective(record);
			}else{
				record.setStockName(tempBean.getStockName());
				updateByPrimaryKeySelective(record);
			}
		}
	}
	public GpStockMetadata selectByPrimaryKey(String stockCodeFull){
		GpStockMetadata bean = stockMetadataMapper.selectByPrimaryKey(stockCodeFull);
		return bean;
	}
	public int insertSelective(GpStockMetadata record){
		int iResult = stockMetadataMapper.insertSelective(record);
		return iResult;
	}
	public int updateByPrimaryKeySelective(GpStockMetadata record){
		int iResult = stockMetadataMapper.updateByPrimaryKeySelective(record);
		return iResult;
	}
}
