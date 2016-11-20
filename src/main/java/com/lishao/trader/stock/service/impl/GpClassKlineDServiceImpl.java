package com.lishao.trader.stock.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lishao.trader.stock.bean.entity.GpClassKlineD;
import com.lishao.trader.stock.dao.GpClassKlineDMapper;
import com.lishao.trader.stock.service.GpClassKlineDService;

@Service
public class GpClassKlineDServiceImpl implements GpClassKlineDService {
	@Resource
	GpClassKlineDMapper classKlineDMapper;

	public GpClassKlineD selectLastClassKlineDByClassifyCode(String classifyCode){
		return classKlineDMapper.selectLastClassKlineDByClassifyCode(classifyCode);
	}
}
