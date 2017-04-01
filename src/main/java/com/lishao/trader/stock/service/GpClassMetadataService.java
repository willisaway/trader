package com.lishao.trader.stock.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lishao.system.core.base.BaseService;
import com.lishao.trader.stock.model.GpClassMetadata;
import com.lishao.trader.stock.provider.GpClassMetadataProvider;

@Service
public class GpClassMetadataService extends BaseService<GpClassMetadataProvider,GpClassMetadata>{
	@Resource
	GpClassMetadataProvider gpClassMetadataProvider;
	
	@Resource
	public void setProvider(GpClassMetadataProvider provider) {
        this.provider = provider;
    }
}
