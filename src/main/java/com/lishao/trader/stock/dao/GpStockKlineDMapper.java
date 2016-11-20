package com.lishao.trader.stock.dao;

import java.util.Map;

import com.lishao.trader.stock.bean.entity.GpStockKlineD;

public interface GpStockKlineDMapper {
    int deleteByPrimaryKey(String periodCode);

    int insert(GpStockKlineD record);

    int insertSelective(GpStockKlineD record);

    GpStockKlineD selectByPrimaryKey(Map paraMap);

    int updateByPrimaryKeySelective(GpStockKlineD record);

    int updateByPrimaryKey(GpStockKlineD record);
    
    public GpStockKlineD selectLastStockKlineDByStockCode(String stockCode);
    
    int updateTradeDetailCount(Map paraMap);
}