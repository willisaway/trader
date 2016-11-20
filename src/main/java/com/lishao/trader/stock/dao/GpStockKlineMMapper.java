package com.lishao.trader.stock.dao;

import com.lishao.trader.stock.bean.entity.GpStockKlineM;
import com.lishao.trader.stock.bean.entity.GpStockKlineMKey;

public interface GpStockKlineMMapper {
    int deleteByPrimaryKey(GpStockKlineMKey key);

    int insert(GpStockKlineM record);

    int insertSelective(GpStockKlineM record);

    GpStockKlineM selectByPrimaryKey(GpStockKlineMKey key);

    int updateByPrimaryKeySelective(GpStockKlineM record);

    int updateByPrimaryKey(GpStockKlineM record);
    
    public GpStockKlineM selectLastStockKlineMByStockCode(String stockCode);
}