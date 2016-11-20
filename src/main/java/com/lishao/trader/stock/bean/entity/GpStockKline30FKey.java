package com.lishao.trader.stock.bean.entity;

import com.lishao.trader.base.bean.EntityBean;

public class GpStockKline30FKey extends EntityBean{
    private String stockCode;

    private String periodCode;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }

    public String getPeriodCode() {
        return periodCode;
    }

    public void setPeriodCode(String periodCode) {
        this.periodCode = periodCode == null ? null : periodCode.trim();
    }
}