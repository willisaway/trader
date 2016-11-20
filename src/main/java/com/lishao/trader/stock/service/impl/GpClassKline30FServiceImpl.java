package com.lishao.trader.stock.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lishao.trader.stock.bean.entity.GpClassKline30F;
import com.lishao.trader.stock.dao.GpClassKline30FMapper;
import com.lishao.trader.stock.service.GpClassKline30FService;

@Service
public class GpClassKline30FServiceImpl implements GpClassKline30FService {
	@Resource
	GpClassKline30FMapper classKline30FMapper;
	@Override
	public GpClassKline30F selectLastClassKline30FByClassifyCode(String classifyCode) {
		return classKline30FMapper.selectLastClassKline30FByClassifyCode(classifyCode);
	}

}
