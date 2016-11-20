package com.lishao.trader.stock.bean.entity;

import java.util.Date;

public class GpClassMetadata extends GpClassMetadataKey {
	private String classifyCodeFull;
	
    private String classifyName;

    private String deletedFlag;

    private String originCode;

    private Date createdDate;

    private Date lastUpdDate;

    private Integer modificationNum;

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName == null ? null : classifyName.trim();
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

	public String getClassifyCodeFull() {
		return classifyCodeFull;
	}

	public void setClassifyCodeFull(String classifyCodeFull) {
		this.classifyCodeFull = classifyCodeFull;
	}
}