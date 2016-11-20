package com.lishao.trader.stock.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lishao.trader.stock.bean.entity.GpClassKlineW;
import com.lishao.trader.stock.dao.GpClassKlineWMapper;
import com.lishao.trader.stock.service.GpClassKlineWService;

@Service
public class GpClassKlineWServiceImpl implements GpClassKlineWService {
	@Resource
	GpClassKlineWMapper classKlineWMapper;
	@Override
	public GpClassKlineW selectLastClassKlineWByClassifyCode(String classifyCode) {
		return classKlineWMapper.selectLastClassKlineWByClassifyCode(classifyCode);
	}

}
