package com.lishao.trader.stock.dao;

import com.lishao.trader.stock.bean.entity.GpClassKlineW;
import com.lishao.trader.stock.bean.entity.GpClassKlineWKey;

public interface GpClassKlineWMapper {
    int deleteByPrimaryKey(GpClassKlineWKey key);

    int insert(GpClassKlineW record);

    int insertSelective(GpClassKlineW record);

    GpClassKlineW selectByPrimaryKey(GpClassKlineWKey key);

    int updateByPrimaryKeySelective(GpClassKlineW record);

    int updateByPrimaryKey(GpClassKlineW record);
    
    public GpClassKlineW selectLastClassKlineWByClassifyCode(String classifyCode);
}