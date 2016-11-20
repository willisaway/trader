package com.lishao.trader.stock.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lishao.trader.stock.bean.entity.GpStockKline15F;
import com.lishao.trader.stock.dao.GpStockKline15FMapper;
import com.lishao.trader.stock.service.GpStockKline15FService;

@Service
public class GpStockKline15FServiceImpl implements GpStockKline15FService {
	@Resource
	GpStockKline15FMapper stockKline15FMapper;
	@Override
	public GpStockKline15F selectLastStockKline15FByStockCode(String stockCode) {
		return stockKline15FMapper.selectLastStockKline15FByStockCode(stockCode);
	}

}
