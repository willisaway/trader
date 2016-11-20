package com.lishao.trader.stock.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lishao.trader.stock.bean.entity.GpClassKline15F;
import com.lishao.trader.stock.dao.GpClassKline15FMapper;
import com.lishao.trader.stock.service.GpClassKline15FService;

@Service
public class GpClassKline15FServiceImpl implements GpClassKline15FService {
	@Resource
	GpClassKline15FMapper classKline15FMapper;
	@Override
	public GpClassKline15F selectLastClassKline15FByClassifyCode(String classifyCode) {
		return classKline15FMapper.selectLastClassKline15FByClassifyCode(classifyCode);
	}

}
