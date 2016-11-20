package com.lishao.trader.base.bean;

import java.util.Date;

/**
 * Entity Bean
 * @author LYG
 *
 */
public class EntityBean {
	private String deletedFlag = "0";
	private String originCode;//
	private Date createdDate;
	private Date lastUpdDate;
	private Integer modificationNum;
	public String getDeletedFlag() {
		return deletedFlag;
	}
	public void setDeletedFlag(String deletedFlag) {
		this.deletedFlag = deletedFlag;
	}
	public String getOriginCode() {
		return originCode;
	}
	public void setOriginCode(String originCode) {
		this.originCode = originCode;
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
}
