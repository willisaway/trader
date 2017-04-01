package com.lishao.trader.stock.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lishao.system.core.base.BaseService;
import com.lishao.trader.stock.model.GpStockClassMap;
import com.lishao.trader.stock.provider.GpStockClassMapProvider;

@Service
public class GpStockClassMapService extends BaseService<GpStockClassMapProvider,GpStockClassMap>{
	@Resource
	GpStockClassMapProvider gpStockClassMapProvider;
	
	@Resource
	public void setProvider(GpStockClassMapProvider provider) {
        this.provider = provider;
    }
	
	public void deleteByClassifyCode(String classifyCode){
		gpStockClassMapProvider.deleteByClassifyCode(classifyCode);
	}
	
	public GpStockClassMap queryByClassifyStockCode(String classifyCode,String stockCode){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("classifyCode", classifyCode);
		params.put("stockCode", stockCode);
		List<GpStockClassMap> list = queryAll(params);
		GpStockClassMap gpStockClassMap = null;
		if(list!=null&&list.size()>0){
			gpStockClassMap = list.get(0);
		}
		return gpStockClassMap;
	}
}
