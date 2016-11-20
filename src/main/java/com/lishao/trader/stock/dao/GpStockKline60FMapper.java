package com.lishao.trader.stock.dao;

import com.lishao.trader.stock.bean.entity.GpStockKline60F;
import com.lishao.trader.stock.bean.entity.GpStockKline60FKey;

public interface GpStockKline60FMapper {
    int deleteByPrimaryKey(GpStockKline60FKey key);

    int insert(GpStockKline60F record);

    int insertSelective(GpStockKline60F record);

    GpStockKline60F selectByPrimaryKey(GpStockKline60FKey key);

    int updateByPrimaryKeySelective(GpStockKline60F record);

    int updateByPrimaryKey(GpStockKline60F record);
    
    public GpStockKline60F selectLastStockKline60FByStockCode(String stockCode);
}