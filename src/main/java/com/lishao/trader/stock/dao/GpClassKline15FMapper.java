package com.lishao.trader.stock.dao;

import com.lishao.trader.stock.bean.entity.GpClassKline15F;
import com.lishao.trader.stock.bean.entity.GpClassKline15FKey;

public interface GpClassKline15FMapper {
    int deleteByPrimaryKey(GpClassKline15FKey key);

    int insert(GpClassKline15F record);

    int insertSelective(GpClassKline15F record);

    GpClassKline15F selectByPrimaryKey(GpClassKline15FKey key);

    int updateByPrimaryKeySelective(GpClassKline15F record);

    int updateByPrimaryKey(GpClassKline15F record);
    
    public GpClassKline15F selectLastClassKline15FByClassifyCode(String classifyCode);
}