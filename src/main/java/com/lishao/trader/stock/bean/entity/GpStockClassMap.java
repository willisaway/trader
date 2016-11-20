package com.lishao.trader.stock.bean.entity;

import java.math.BigDecimal;
import java.util.Date;

public class GpStockClassMap extends GpStockClassMapKey {
    private BigDecimal weight;

    private Date incluDate;
    
    private String deletedFlag;

    private String originCode;

    private Date createdDate;

    private Date lastUpdDate;

    private Integer modificationNum;

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
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

	public Date getIncluDate() {
		return incluDate;
	}

	public void setIncluDate(Date incluDate) {
		this.incluDate = incluDate;
	}
}