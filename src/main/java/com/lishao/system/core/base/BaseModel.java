package com.lishao.system.core.base;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseModel implements Serializable {
	@TableId(value = "row_id", type = IdType.AUTO)
	private Long rowId;
	@TableField("remark")
	private String remark;
	@TableField("deleted_flag")
	private String deletedFlag;
	@TableField("origin_code")
	private String originCode;
	@TableField("created_date")
    private Date createdDate;
	@TableField("last_upd_date")
    private Date lastUpdDate;
	@TableField("modification_num")
    private Integer modificationNum;
	
	public Long getRowId() {
		return rowId;
	}
	
	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
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
