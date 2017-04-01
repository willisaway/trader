package com.lishao.trader.stock.model;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;
import com.lishao.system.core.base.BaseModel;

@TableName("GP_STOCK_METADATA")
public class GpStockMetadata extends BaseModel{
    private String stockCodeFull;

    private String stockCode;

    private String stockName;

    private Date listingDate;

    private Long totalStockNum;

    private Long tradableStockNum;

    private BigDecimal marketValue;

    private BigDecimal tradableMarketValue;

    public String getStockCodeFull() {
        return stockCodeFull;
    }

    public void setStockCodeFull(String stockCodeFull) {
        this.stockCodeFull = stockCodeFull == null ? null : stockCodeFull.trim();
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName == null ? null : stockName.trim();
    }

    public Date getListingDate() {
        return listingDate;
    }

    public void setListingDate(Date listingDate) {
        this.listingDate = listingDate;
    }

    public Long getTotalStockNum() {
        return totalStockNum;
    }

    public void setTotalStockNum(Long totalStockNum) {
        this.totalStockNum = totalStockNum;
    }

    public Long getTradableStockNum() {
        return tradableStockNum;
    }

    public void setTradableStockNum(Long tradableStockNum) {
        this.tradableStockNum = tradableStockNum;
    }

    public BigDecimal getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }

    public BigDecimal getTradableMarketValue() {
        return tradableMarketValue;
    }

    public void setTradableMarketValue(BigDecimal tradableMarketValue) {
        this.tradableMarketValue = tradableMarketValue;
    }
}