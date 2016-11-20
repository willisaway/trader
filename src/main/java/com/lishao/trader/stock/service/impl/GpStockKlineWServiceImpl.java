package com.lishao.trader.stock.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lishao.trader.stock.bean.entity.GpStockKlineW;
import com.lishao.trader.stock.dao.GpStockKlineWMapper;
import com.lishao.trader.stock.service.GpStockKlineWService;

@Service
public class GpStockKlineWServiceImpl implements GpStockKlineWService {
	@Resource
	GpStockKlineWMapper stockKlineWMapper;
	@Override
	public GpStockKlineW selectLastStockKlineWByStockCode(String stockCode) {
		return stockKlineWMapper.selectLastStockKlineWByStockCode(stockCode);
	}

}
