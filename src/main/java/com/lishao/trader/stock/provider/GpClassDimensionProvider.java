package com.lishao.trader.stock.provider;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.lishao.system.core.base.BaseProviderImpl;
import com.lishao.trader.stock.model.GpClassDimension;

@CacheConfig(cacheNames = "GpClassDimension")
@Service
public class GpClassDimensionProvider extends BaseProviderImpl<GpClassDimension>{

}
