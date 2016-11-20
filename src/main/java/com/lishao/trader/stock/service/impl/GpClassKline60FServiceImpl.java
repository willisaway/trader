package com.lishao.trader.stock.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lishao.trader.stock.bean.entity.GpClassKline60F;
import com.lishao.trader.stock.dao.GpClassKline60FMapper;
import com.lishao.trader.stock.service.GpClassKline60FService;

@Service
public class GpClassKline60FServiceImpl implements GpClassKline60FService {
	@Resource
	GpClassKline60FMapper classKline60FMapper;
	@Override
	public GpClassKline60F selectLastClassKline60FByClassifyCode(String classifyCode) {
		return classKline60FMapper.selectLastClassKline60FByClassifyCode(classifyCode);
	}

}
