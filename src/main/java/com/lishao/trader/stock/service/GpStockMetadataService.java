package com.lishao.trader.stock.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lishao.system.core.base.BaseService;
import com.lishao.trader.stock.model.GpStockMetadata;
import com.lishao.trader.stock.provider.GpStockMetadataProvider;

@Service
public class GpStockMetadataService extends BaseService<GpStockMetadataProvider,GpStockMetadata>{
	@Resource
	GpStockMetadataProvider gpStockMetadataProvider;
	
	@Resource
	public void setProvider(GpStockMetadataProvider provider) {
        this.provider = provider;
    }
	
	public void saveAll(List<GpStockMetadata> list){
		gpStockMetadataProvider.saveAll(list);
	}
}
