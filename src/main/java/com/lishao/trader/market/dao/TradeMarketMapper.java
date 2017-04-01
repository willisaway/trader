package com.lishao.trader.market.dao;

import com.lishao.system.core.base.BaseMapper;
import com.lishao.trader.market.model.TradeMarket;

public interface TradeMarketMapper extends BaseMapper<TradeMarket>{
    int deleteByPrimaryKey(Integer rowId);

    int insertSelective(TradeMarket record);

    TradeMarket selectByPrimaryKey(Integer rowId);

    int updateByPrimaryKeySelective(TradeMarket record);

    int updateByPrimaryKey(TradeMarket record);
}