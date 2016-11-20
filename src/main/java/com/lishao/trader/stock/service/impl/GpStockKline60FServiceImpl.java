package com.lishao.trader.stock.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lishao.trader.stock.bean.entity.GpStockKline60F;
import com.lishao.trader.stock.dao.GpStockKline60FMapper;
import com.lishao.trader.stock.service.GpStockKline60FService;

@Service
public class GpStockKline60FServiceImpl implements GpStockKline60FService {
	@Resource
	GpStockKline60FMapper stockKline60FMapper;
	@Override
	public GpStockKline60F selectLastStockKline60FByStockCode(String stockCode) {
		return stockKline60FMapper.selectLastStockKline60FByStockCode(stockCode);
	}

}
