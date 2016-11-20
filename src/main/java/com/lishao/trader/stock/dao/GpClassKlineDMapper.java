package com.lishao.trader.stock.dao;

import com.lishao.trader.stock.bean.entity.GpClassKlineD;

public interface GpClassKlineDMapper {
    int deleteByPrimaryKey(String periodCode);

    int insert(GpClassKlineD record);

    int insertSelective(GpClassKlineD record);

    GpClassKlineD selectByPrimaryKey(String periodCode);

    int updateByPrimaryKeySelective(GpClassKlineD record);

    int updateByPrimaryKey(GpClassKlineD record);
    
    public GpClassKlineD selectLastClassKlineDByClassifyCode(String classifyCode);
}