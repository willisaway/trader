package com.lishao.trader.stock.dao;

import com.lishao.trader.stock.bean.entity.GpClassDimension;

public interface GpClassDimensionMapper {
    int deleteByPrimaryKey(String dimensionCode);

    int insert(GpClassDimension record);

    int insertSelective(GpClassDimension record);

    GpClassDimension selectByPrimaryKey(String dimensionCode);

    int updateByPrimaryKeySelective(GpClassDimension record);

    int updateByPrimaryKey(GpClassDimension record);
}