package com.lishao.trader.stock.dao;

import com.lishao.system.core.base.BaseMapper;
import com.lishao.trader.stock.model.GpStockMetadata;

public interface GpStockMetadataMapper extends BaseMapper<GpStockMetadata>{
    int deleteByPrimaryKey(Integer rowId);

    int insertSelective(GpStockMetadata record);

    GpStockMetadata selectByPrimaryKey(Integer rowId);

    int updateByPrimaryKeySelective(GpStockMetadata record);

    int updateByPrimaryKey(GpStockMetadata record);
}