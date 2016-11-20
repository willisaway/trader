package com.lishao.trader.stock.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lishao.trader.stock.bean.entity.GpStockKline30F;
import com.lishao.trader.stock.dao.GpStockKline30FMapper;
import com.lishao.trader.stock.service.GpStockKline30FService;

@Service
public class GpStockKline30FServiceImpl implements GpStockKline30FService {
	@Resource
	GpStockKline30FMapper stockKline30FMapper;
	@Override
	public GpStockKline30F selectLastStockKline30FByStockCode(String stockCode) {
		return stockKline30FMapper.selectLastStockKline30FByStockCode(stockCode);
	}

}
