package com.lishao.trader.market.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lishao.system.core.base.BaseService;
import com.lishao.trader.market.model.TradeMarket;
import com.lishao.trader.market.provider.TradeMarketProvider;

@Service
public class TradeMarketService extends BaseService<TradeMarketProvider, TradeMarket>{
	@Resource
	TradeMarketProvider tradeMarketProvider;
	
	@Resource
	public void setProvider(TradeMarketProvider provider) {
        this.provider = provider;
    }
}
