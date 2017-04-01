package com.lishao.trader.stock.provider;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.lishao.system.core.base.BaseProviderImpl;
import com.lishao.trader.stock.model.GpClassMetadata;

@CacheConfig(cacheNames = "GpClassMetadata")
@Service
public class GpClassMetadataProvider extends BaseProviderImpl<GpClassMetadata>{

}
