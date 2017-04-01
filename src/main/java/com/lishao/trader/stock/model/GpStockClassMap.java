package com.lishao.trader.stock.model;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;
import com.lishao.system.core.base.BaseModel;

@TableName("GP_STOCK_CLASS_MAP")
public class GpStockClassMap extends BaseModel{
    private String stockCode;

    private String classifyCode;

    private BigDecimal weight;

    private Date incluDate;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }

    public String getClassifyCode() {
        return classifyCode;
    }

    public void setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode == null ? null : classifyCode.trim();
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Date getIncluDate() {
        return incluDate;
    }

    public void setIncluDate(Date incluDate) {
        this.incluDate = incluDate;
    }
}