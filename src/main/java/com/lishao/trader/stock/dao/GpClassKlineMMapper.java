package com.lishao.trader.stock.dao;

import com.lishao.trader.stock.bean.entity.GpClassKlineM;
import com.lishao.trader.stock.bean.entity.GpClassKlineMKey;

public interface GpClassKlineMMapper {
    int deleteByPrimaryKey(GpClassKlineMKey key);

    int insert(GpClassKlineM record);

    int insertSelective(GpClassKlineM record);

    GpClassKlineM selectByPrimaryKey(GpClassKlineMKey key);

    int updateByPrimaryKeySelective(GpClassKlineM record);

    int updateByPrimaryKey(GpClassKlineM record);
    
    public GpClassKlineM selectLastClassKlineMByClassifyCode(String classifyCode);
}