package com.lishao.trader.stock.dao;

import java.util.List;

import com.lishao.trader.stock.bean.entity.GpClassMetadata;
import com.lishao.trader.stock.bean.entity.GpClassMetadataKey;

public interface GpClassMetadataMapper {
    int deleteByPrimaryKey(GpClassMetadataKey key);

    int insert(GpClassMetadata record);

    int insertSelective(GpClassMetadata record);

    GpClassMetadata selectByPrimaryKey(GpClassMetadataKey key);

    int updateByPrimaryKeySelective(GpClassMetadata record);

    int updateByPrimaryKey(GpClassMetadata record);
    
    List<GpClassMetadata> selectByDimensionCode(String dimensionCode);
    List<GpClassMetadata> selectAll();
}