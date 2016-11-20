package com.lishao.trader.stock.dao;

import com.lishao.trader.stock.bean.entity.GpStockKline15F;
import com.lishao.trader.stock.bean.entity.GpStockKline15FKey;

public interface GpStockKline15FMapper {
    int deleteByPrimaryKey(GpStockKline15FKey key);

    int insert(GpStockKline15F record);

    int insertSelective(GpStockKline15F record);

    GpStockKline15F selectByPrimaryKey(GpStockKline15FKey key);

    int updateByPrimaryKeySelective(GpStockKline15F record);

    int updateByPrimaryKey(GpStockKline15F record);
    
    public GpStockKline15F selectLastStockKline15FByStockCode(String stockCode);
}