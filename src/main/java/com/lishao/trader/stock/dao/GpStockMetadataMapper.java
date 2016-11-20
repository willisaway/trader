package com.lishao.trader.stock.dao;

import java.util.List;

import com.lishao.trader.stock.bean.entity.GpStockMetadata;

public interface GpStockMetadataMapper {
    int deleteByPrimaryKey(String stockCodeFull);

    int insert(GpStockMetadata record);

    int insertSelective(GpStockMetadata record);

    GpStockMetadata selectByPrimaryKey(String stockCodeFull);

    int updateByPrimaryKeySelective(GpStockMetadata record);

    int updateByPrimaryKey(GpStockMetadata record);
    
    List<GpStockMetadata> selectAll();
}