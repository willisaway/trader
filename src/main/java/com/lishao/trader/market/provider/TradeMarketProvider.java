package com.lishao.trader.market.provider;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.lishao.system.core.base.BaseProviderImpl;
import com.lishao.trader.market.model.TradeMarket;

@CacheConfig(cacheNames = "tradeMarket")
@Service
public class TradeMarketProvider extends BaseProviderImpl<TradeMarket>{
	
}
