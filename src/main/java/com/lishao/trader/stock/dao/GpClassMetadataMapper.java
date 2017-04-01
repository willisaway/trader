package com.lishao.trader.stock.dao;

import com.lishao.system.core.base.BaseMapper;
import com.lishao.trader.stock.model.GpClassMetadata;

public interface GpClassMetadataMapper extends BaseMapper<GpClassMetadata>{
    int deleteByPrimaryKey(Integer rowId);

    int insertSelective(GpClassMetadata record);

    GpClassMetadata selectByPrimaryKey(Integer rowId);

    int updateByPrimaryKeySelective(GpClassMetadata record);

    int updateByPrimaryKey(GpClassMetadata record);
}