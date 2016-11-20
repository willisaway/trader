package com.lishao.trader.stock.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lishao.trader.stock.dao.GpClassDimensionMapper;
import com.lishao.trader.stock.service.GpClassDimensionService;

@Service
public class GpClassDimensionServiceImpl implements GpClassDimensionService {
	@Resource
	GpClassDimensionMapper stockClassDimensionMapper;
}
