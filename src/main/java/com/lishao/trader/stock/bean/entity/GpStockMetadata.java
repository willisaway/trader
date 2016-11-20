package com.lishao.trader.stock.bean.entity;

import java.math.BigDecimal;
import java.util.Date;

public class GpStockMetadata {
    private String stockCodeFull;

    private String stockCode;

    private String stockName;

    private Date listingDate;
    
    private Long totalStockNum;

    private Long tradableStockNum;

    private BigDecimal marketValue;
    
    private BigDecimal tradableMarketValue;

    private Date lastUpdKlineDate;

    private String deletedFlag;

    private String originCode;

    private Date createdDate;

    private Date lastUpdDate;

    private Integer modificationNum;

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

	public Date getLastUpdKlineDate() {
        return lastUpdKlineDate;
    }

    public void setLastUpdKlineDate(Date lastUpdKlineDate) {
        this.lastUpdKlineDate = lastUpdKlineDate;
    }

    public String getDeletedFlag() {
        return deletedFlag;
    }

    public void setDeletedFlag(String deletedFlag) {
        this.deletedFlag = deletedFlag == null ? null : deletedFlag.trim();
    }

    public String getOriginCode() {
        return originCode;
    }

    public void setOriginCode(String originCode) {
        this.originCode = originCode == null ? null : originCode.trim();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastUpdDate() {
        return lastUpdDate;
    }

    public void setLastUpdDate(Date lastUpdDate) {
        this.lastUpdDate = lastUpdDate;
    }

    public Integer getModificationNum() {
        return modificationNum;
    }

    public void setModificationNum(Integer modificationNum) {
        this.modificationNum = modificationNum;
    }

	public Date getListingDate() {
		return listingDate;
	}

	public void setListingDate(Date listingDate) {
		this.listingDate = listingDate;
	}
}