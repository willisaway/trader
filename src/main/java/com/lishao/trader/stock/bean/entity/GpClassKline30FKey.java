package com.lishao.trader.stock.bean.entity;

import com.lishao.trader.base.bean.EntityBean;

public class GpClassKline30FKey extends EntityBean{
    private String classifyCode;

    private String periodCode;

    public String getClassifyCode() {
        return classifyCode;
    }

    public void setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode == null ? null : classifyCode.trim();
    }

    public String getPeriodCode() {
        return periodCode;
    }

    public void setPeriodCode(String periodCode) {
        this.periodCode = periodCode == null ? null : periodCode.trim();
    }
}