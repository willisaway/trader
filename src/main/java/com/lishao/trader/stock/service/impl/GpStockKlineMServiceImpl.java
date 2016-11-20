package com.lishao.trader.stock.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lishao.trader.stock.bean.entity.GpStockKlineM;
import com.lishao.trader.stock.dao.GpStockKlineMMapper;
import com.lishao.trader.stock.service.GpStockKlineMService;

@Service
public class GpStockKlineMServiceImpl implements GpStockKlineMService {
	@Resource
	GpStockKlineMMapper stockKlineMMapper;
	@Override
	public GpStockKlineM selectLastStockKlineMByStockCode(String stockCode) {
		return stockKlineMMapper.selectLastStockKlineMByStockCode(stockCode);
	}

}
