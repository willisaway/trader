package com.lishao.trader.stock.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;
import com.lishao.system.core.base.BaseModel;

@TableName("GP_CLASS_METADATA")
public class GpClassMetadata extends BaseModel{
    private String dimensionCode;

    private String classifyCode;

    private String classifyCodeFull;

    private String classifyName;

    private String classifyBy;

    public String getDimensionCode() {
        return dimensionCode;
    }

    public void setDimensionCode(String dimensionCode) {
        this.dimensionCode = dimensionCode == null ? null : dimensionCode.trim();
    }

    public String getClassifyCode() {
        return classifyCode;
    }

    public void setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode == null ? null : classifyCode.trim();
    }

    public String getClassifyCodeFull() {
        return classifyCodeFull;
    }

    public void setClassifyCodeFull(String classifyCodeFull) {
        this.classifyCodeFull = classifyCodeFull == null ? null : classifyCodeFull.trim();
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName == null ? null : classifyName.trim();
    }

    public String getClassifyBy() {
        return classifyBy;
    }

    public void setClassifyBy(String classifyBy) {
        this.classifyBy = classifyBy == null ? null : classifyBy.trim();
    }
}