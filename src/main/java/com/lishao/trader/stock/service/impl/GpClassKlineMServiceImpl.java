package com.lishao.trader.stock.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lishao.trader.stock.bean.entity.GpClassKlineM;
import com.lishao.trader.stock.dao.GpClassKlineMMapper;
import com.lishao.trader.stock.service.GpClassKlineMService;

@Service
public class GpClassKlineMServiceImpl implements GpClassKlineMService {
	@Resource
	GpClassKlineMMapper classKlineMMapper;
	@Override
	public GpClassKlineM selectLastClassKlineMByClassifyCode(String classifyCode) {
		return classKlineMMapper.selectLastClassKlineMByClassifyCode(classifyCode);
	}

}
