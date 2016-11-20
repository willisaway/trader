package com.lishao.trader.common.bean.entity;

import java.util.Date;

public class TradeCalendar {
    private String rowId;

    private String marketId;

    private String tradeYear;

    private String tradeMonth;

    private String tradeWeek;

    private Date tradeDate;

    private String deletedFlag;

    private String originCode;

    private Date createdDate;

    private Date lastUpdDate;

    private Integer modificationNum;

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId == null ? null : rowId.trim();
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId == null ? null : marketId.trim();
    }

    public String getTradeYear() {
        return tradeYear;
    }

    public void setTradeYear(String tradeYear) {
        this.tradeYear = tradeYear == null ? null : tradeYear.trim();
    }

    public String getTradeMonth() {
        return tradeMonth;
    }

    public void setTradeMonth(String tradeMonth) {
        this.tradeMonth = tradeMonth == null ? null : tradeMonth.trim();
    }

    public String getTradeWeek() {
        return tradeWeek;
    }

    public void setTradeWeek(String tradeWeek) {
        this.tradeWeek = tradeWeek == null ? null : tradeWeek.trim();
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
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
}