package com.lishao.trader.stock.dao;

import com.lishao.system.core.base.BaseMapper;
import com.lishao.trader.stock.model.GpClassDimension;

public interface GpClassDimensionMapper extends BaseMapper<GpClassDimension>{
    int deleteByPrimaryKey(Integer rowId);

    int insertSelective(GpClassDimension record);

    GpClassDimension selectByPrimaryKey(Integer rowId);

    int updateByPrimaryKeySelective(GpClassDimension record);

    int updateByPrimaryKey(GpClassDimension record);
}