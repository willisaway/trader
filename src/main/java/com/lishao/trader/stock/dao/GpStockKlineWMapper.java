package com.lishao.trader.stock.dao;

import com.lishao.trader.stock.bean.entity.GpStockKlineW;
import com.lishao.trader.stock.bean.entity.GpStockKlineWKey;

public interface GpStockKlineWMapper {
    int deleteByPrimaryKey(GpStockKlineWKey key);

    int insert(GpStockKlineW record);

    int insertSelective(GpStockKlineW record);

    GpStockKlineW selectByPrimaryKey(GpStockKlineWKey key);

    int updateByPrimaryKeySelective(GpStockKlineW record);

    int updateByPrimaryKey(GpStockKlineW record);
    
    public GpStockKlineW selectLastStockKlineWByStockCode(String stockCode);
}