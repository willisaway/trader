package com.lishao.trader.stock.dao;

import com.lishao.trader.stock.bean.entity.GpClassKline30F;
import com.lishao.trader.stock.bean.entity.GpClassKline30FKey;

public interface GpClassKline30FMapper {
    int deleteByPrimaryKey(GpClassKline30FKey key);

    int insert(GpClassKline30F record);

    int insertSelective(GpClassKline30F record);

    GpClassKline30F selectByPrimaryKey(GpClassKline30FKey key);

    int updateByPrimaryKeySelective(GpClassKline30F record);

    int updateByPrimaryKey(GpClassKline30F record);
    
    public GpClassKline30F selectLastClassKline30FByClassifyCode(String classifyCode);
}