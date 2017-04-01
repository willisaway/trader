package com.lishao.trader.stock.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lishao.system.core.base.BaseService;
import com.lishao.trader.stock.model.GpClassDimension;
import com.lishao.trader.stock.provider.GpClassDimensionProvider;

@Service
public class GpClassDimensionService extends BaseService<GpClassDimensionProvider,GpClassDimension>{
	@Resource
	GpClassDimensionProvider gpClassDimensionProvider;
	
	@Resource
	public void setProvider(GpClassDimensionProvider provider) {
        this.provider = provider;
    }
}
