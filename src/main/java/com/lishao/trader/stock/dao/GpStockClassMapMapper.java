package com.lishao.trader.stock.dao;

import java.util.List;

import com.lishao.trader.stock.bean.entity.GpStockClassMap;
import com.lishao.trader.stock.bean.entity.GpStockClassMapKey;

public interface GpStockClassMapMapper {
    int deleteByPrimaryKey(GpStockClassMapKey key);

    int insert(GpStockClassMap record);

    int insertSelective(GpStockClassMap record);

    GpStockClassMap selectByPrimaryKey(GpStockClassMapKey key);
    
    List<GpStockClassMap> selectByClassifyCode(GpStockClassMapKey key);

    int updateByPrimaryKeySelective(GpStockClassMap record);

    int updateByPrimaryKey(GpStockClassMap record);
    
    int deleteByClassifyCode(GpStockClassMap record);
}