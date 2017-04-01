package com.lishao.trader.market.model;

import java.util.Date;

import com.lishao.system.core.base.BaseModel;

public class TradeMarket extends BaseModel{
    private String marketCode;

    private String marketName;

    private String marketDesc;
    
    public String getMarketCode() {
        return marketCode;
    }

    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode == null ? null : marketCode.trim();
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName == null ? null : marketName.trim();
    }

    public String getMarketDesc() {
        return marketDesc;
    }

    public void setMarketDesc(String marketDesc) {
        this.marketDesc = marketDesc == null ? null : marketDesc.trim();
    }
}