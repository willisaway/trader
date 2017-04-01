package com.lishao.trader.stock.provider;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lishao.system.core.base.BaseProviderImpl;
import com.lishao.system.core.util.RedissonUtil;
import com.lishao.trader.stock.model.GpStockMetadata;

@CacheConfig(cacheNames = "GpStockMetadata")
@Service
public class GpStockMetadataProvider extends BaseProviderImpl<GpStockMetadata>{
	Logger logger=Logger.getLogger(GpStockMetadataProvider.class);
	
	public void saveAll(List<GpStockMetadata> list){
		for(GpStockMetadata gpStockMetadata:list){
			try {
				save(gpStockMetadata);
			} catch (Exception e) {
				logger.error(e);
				logger.error("股票代码保存异常:"+gpStockMetadata.getStockCode());
			}
		}
	}
	@Transactional
	public GpStockMetadata save(GpStockMetadata gpStockMetadata) {
		//用来更新的bean
		GpStockMetadata updateBean = null;
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("stockCode", gpStockMetadata.getStockCode());
		List<GpStockMetadata> list = queryAll(params);
		if(list!=null&&list.size()>0){//如果数据库中已经找到了，则更新
			updateBean = list.get(0);
			updateBean.setStockName(gpStockMetadata.getStockName());
			updateBean.setMarketValue(gpStockMetadata.getTradableMarketValue());
			updateBean.setTradableMarketValue(gpStockMetadata.getTradableMarketValue());
		}else{//否则是新增
			updateBean = gpStockMetadata;
		}
		updateBean = update(updateBean);
		return updateBean;
	}
}
