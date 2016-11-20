package com.lishao.trader.stock.dao;

import com.lishao.trader.stock.bean.entity.GpStockKline30F;
import com.lishao.trader.stock.bean.entity.GpStockKline30FKey;

public interface GpStockKline30FMapper {
    int deleteByPrimaryKey(GpStockKline30FKey key);

    int insert(GpStockKline30F record);

    int insertSelective(GpStockKline30F record);

    GpStockKline30F selectByPrimaryKey(GpStockKline30FKey key);

    int updateByPrimaryKeySelective(GpStockKline30F record);

    int updateByPrimaryKey(GpStockKline30F record);
    
    public GpStockKline30F selectLastStockKline30FByStockCode(String stockCode);
}