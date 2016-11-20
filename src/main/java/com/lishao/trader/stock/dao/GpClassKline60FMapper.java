package com.lishao.trader.stock.dao;

import com.lishao.trader.stock.bean.entity.GpClassKline60F;
import com.lishao.trader.stock.bean.entity.GpClassKline60FKey;

public interface GpClassKline60FMapper {
    int deleteByPrimaryKey(GpClassKline60FKey key);

    int insert(GpClassKline60F record);

    int insertSelective(GpClassKline60F record);

    GpClassKline60F selectByPrimaryKey(GpClassKline60FKey key);

    int updateByPrimaryKeySelective(GpClassKline60F record);

    int updateByPrimaryKey(GpClassKline60F record);
    
    public GpClassKline60F selectLastClassKline60FByClassifyCode(String classifyCode);
}