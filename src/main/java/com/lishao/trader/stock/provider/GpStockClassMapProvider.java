package com.lishao.trader.stock.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.lishao.system.core.base.BaseProviderImpl;
import com.lishao.trader.stock.model.GpStockClassMap;

@CacheConfig(cacheNames = "GpStockClass")
@Service
public class GpStockClassMapProvider extends BaseProviderImpl<GpStockClassMap>{
	public void deleteByClassifyCode(String classifyCode){
		Map<String,Object> params = new HashMap();
		params.put("classifyCode", classifyCode);
		List<GpStockClassMap> list = queryAll(params);
		for(GpStockClassMap gpStockClassMap:list){
			gpStockClassMap.setDeletedFlag("1");
			update(gpStockClassMap);
		}
	}
}
