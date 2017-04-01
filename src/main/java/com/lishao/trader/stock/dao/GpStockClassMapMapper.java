package com.lishao.trader.stock.dao;

import com.lishao.system.core.base.BaseMapper;
import com.lishao.trader.stock.model.GpStockClassMap;

public interface GpStockClassMapMapper extends BaseMapper<GpStockClassMap>{
    int deleteByPrimaryKey(Integer rowId);

    int insertSelective(GpStockClassMap record);

    GpStockClassMap selectByPrimaryKey(Integer rowId);

    int updateByPrimaryKeySelective(GpStockClassMap record);

    int updateByPrimaryKey(GpStockClassMap record);
}