package com.lishao.trader.stock.bean.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.lishao.trader.base.bean.EntityBean;

public class GpStockKlineD extends EntityBean {
    private String rowId;

    private String stockCode;

    private String periodCode;
    
    private Long sequenceNo;

    private String snapTime;

    private BigDecimal priceOpen;

    private BigDecimal priceHigh;

    private BigDecimal priceLow;

    private BigDecimal priceClose;

    private BigDecimal priceCloseRef1;

    private Long tradeVolumn;

    private Long avgVolumn;
    
    private Long avgHandCount;
    
    private BigDecimal tradeAmount;

    private BigDecimal fuquan;

    private BigDecimal amountFlow;
    
    private BigDecimal amountIn;
    
    private BigDecimal amountOut;
    
    private BigDecimal largeAmountFlow;
    
    private BigDecimal largeAmountIn;
    
    private BigDecimal largeAmountOut;

    private BigDecimal incPer;

    private Long buy1Count;

    private BigDecimal buy1Price;

    private Long buy2Count;

    private BigDecimal buy2Price;

    private Long buy3Count;

    private BigDecimal buy3Price;

    private Long buy4Count;

    private BigDecimal buy4Price;

    private Long buy5Count;

    private BigDecimal buy5Price;

    private Long sell1Count;

    private BigDecimal sell1Price;

    private Long sell2Count;

    private BigDecimal sell2Price;

    private Long sell3Count;

    private BigDecimal sell3Price;

    private Long sell4Count;

    private BigDecimal sell4Price;

    private Long sell5Count;

    private BigDecimal sell5Price;

    private BigDecimal closeEma5;

    private BigDecimal closeEma10;

    private BigDecimal closeEma20;

    private BigDecimal closeEma30;

    private BigDecimal closeEma60;

    private BigDecimal closeEma150;

    private Long volumnEma5;

    private Long volumnEma10;

    private Long volumnEma20;

    private Long volumnEma30;

    private Long volumnEma60;

    private Long volumnEma150;

    private BigDecimal amountEma5;

    private BigDecimal amountEma10;

    private BigDecimal amountEma20;

    private BigDecimal amountEma30;

    private BigDecimal amountEma60;

    private BigDecimal amountEma150;

    private BigDecimal closeEma6;

    private BigDecimal closeEma13;

    private BigDecimal closeEma12;

    private BigDecimal closeEma26;

    private BigDecimal difS;

    private BigDecimal deaS;

    private BigDecimal deaSAngle;

    private BigDecimal difF;

    private BigDecimal deaF;

    private BigDecimal deaFAngle;

    private BigDecimal kdjK;

    private BigDecimal kdjD;

    private BigDecimal kdjJ;

    private BigDecimal tr;

    private BigDecimal atr;

    private BigDecimal trend;

    private BigDecimal rising;

    private BigDecimal rapidRising;

    private BigDecimal shake;

    private BigDecimal slump;

    private String status;

    private BigDecimal pvSum1;

    private BigDecimal pvSum5;

    private BigDecimal pvSum10;

    private BigDecimal pvSum20;

    private BigDecimal pvSum30;

    private BigDecimal pvSum60;

    private Long ranking;

    private String lastFlag;

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId == null ? null : rowId.trim();
    }

    public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getPeriodCode() {
		return periodCode;
	}

	public void setPeriodCode(String periodCode) {
		this.periodCode = periodCode;
	}

	public Long getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(Long sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public String getSnapTime() {
        return snapTime;
    }

    public void setSnapTime(String snapTime) {
        this.snapTime = snapTime == null ? null : snapTime.trim();
    }

    public BigDecimal getPriceOpen() {
        return priceOpen;
    }

    public void setPriceOpen(BigDecimal priceOpen) {
        this.priceOpen = priceOpen;
    }

    public BigDecimal getPriceHigh() {
        return priceHigh;
    }

    public void setPriceHigh(BigDecimal priceHigh) {
        this.priceHigh = priceHigh;
    }

    public BigDecimal getPriceLow() {
        return priceLow;
    }

    public void setPriceLow(BigDecimal priceLow) {
        this.priceLow = priceLow;
    }

    public BigDecimal getPriceClose() {
        return priceClose;
    }

    public void setPriceClose(BigDecimal priceClose) {
        this.priceClose = priceClose;
    }

    public BigDecimal getPriceCloseRef1() {
        return priceCloseRef1;
    }

    public void setPriceCloseRef1(BigDecimal priceCloseRef1) {
        this.priceCloseRef1 = priceCloseRef1;
    }

    public Long getTradeVolumn() {
        return tradeVolumn;
    }

    public void setTradeVolumn(Long tradeVolumn) {
        this.tradeVolumn = tradeVolumn;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public BigDecimal getFuquan() {
        return fuquan;
    }

    public void setFuquan(BigDecimal fuquan) {
        this.fuquan = fuquan;
    }

    public BigDecimal getAmountFlow() {
        return amountFlow;
    }

    public void setAmountFlow(BigDecimal amountFlow) {
        this.amountFlow = amountFlow;
    }

    public BigDecimal getLargeAmountIn() {
		return largeAmountIn;
	}

	public void setLargeAmountIn(BigDecimal largeAmountIn) {
		this.largeAmountIn = largeAmountIn;
	}

	public BigDecimal getLargeAmountOut() {
		return largeAmountOut;
	}

	public void setLargeAmountOut(BigDecimal largeAmountOut) {
		this.largeAmountOut = largeAmountOut;
	}

	public BigDecimal getIncPer() {
        return incPer;
    }

    public void setIncPer(BigDecimal incPer) {
        this.incPer = incPer;
    }

    public Long getBuy1Count() {
        return buy1Count;
    }

    public void setBuy1Count(Long buy1Count) {
        this.buy1Count = buy1Count;
    }

    public BigDecimal getBuy1Price() {
        return buy1Price;
    }

    public void setBuy1Price(BigDecimal buy1Price) {
        this.buy1Price = buy1Price;
    }

    public Long getBuy2Count() {
        return buy2Count;
    }

    public void setBuy2Count(Long buy2Count) {
        this.buy2Count = buy2Count;
    }

    public BigDecimal getBuy2Price() {
        return buy2Price;
    }

    public void setBuy2Price(BigDecimal buy2Price) {
        this.buy2Price = buy2Price;
    }

    public Long getBuy3Count() {
        return buy3Count;
    }

    public void setBuy3Count(Long buy3Count) {
        this.buy3Count = buy3Count;
    }

    public BigDecimal getBuy3Price() {
        return buy3Price;
    }

    public void setBuy3Price(BigDecimal buy3Price) {
        this.buy3Price = buy3Price;
    }

    public Long getBuy4Count() {
        return buy4Count;
    }

    public void setBuy4Count(Long buy4Count) {
        this.buy4Count = buy4Count;
    }

    public BigDecimal getBuy4Price() {
        return buy4Price;
    }

    public void setBuy4Price(BigDecimal buy4Price) {
        this.buy4Price = buy4Price;
    }

    public Long getBuy5Count() {
        return buy5Count;
    }

    public void setBuy5Count(Long buy5Count) {
        this.buy5Count = buy5Count;
    }

    public BigDecimal getBuy5Price() {
        return buy5Price;
    }

    public void setBuy5Price(BigDecimal buy5Price) {
        this.buy5Price = buy5Price;
    }

    public Long getSell1Count() {
        return sell1Count;
    }

    public void setSell1Count(Long sell1Count) {
        this.sell1Count = sell1Count;
    }

    public BigDecimal getSell1Price() {
        return sell1Price;
    }

    public void setSell1Price(BigDecimal sell1Price) {
        this.sell1Price = sell1Price;
    }

    public Long getSell2Count() {
        return sell2Count;
    }

    public void setSell2Count(Long sell2Count) {
        this.sell2Count = sell2Count;
    }

    public BigDecimal getSell2Price() {
        return sell2Price;
    }

    public void setSell2Price(BigDecimal sell2Price) {
        this.sell2Price = sell2Price;
    }

    public Long getSell3Count() {
        return sell3Count;
    }

    public void setSell3Count(Long sell3Count) {
        this.sell3Count = sell3Count;
    }

    public BigDecimal getSell3Price() {
        return sell3Price;
    }

    public void setSell3Price(BigDecimal sell3Price) {
        this.sell3Price = sell3Price;
    }

    public Long getSell4Count() {
        return sell4Count;
    }

    public void setSell4Count(Long sell4Count) {
        this.sell4Count = sell4Count;
    }

    public BigDecimal getSell4Price() {
        return sell4Price;
    }

    public void setSell4Price(BigDecimal sell4Price) {
        this.sell4Price = sell4Price;
    }

    public Long getSell5Count() {
        return sell5Count;
    }

    public void setSell5Count(Long sell5Count) {
        this.sell5Count = sell5Count;
    }

    public BigDecimal getSell5Price() {
        return sell5Price;
    }

    public void setSell5Price(BigDecimal sell5Price) {
        this.sell5Price = sell5Price;
    }

    public BigDecimal getCloseEma5() {
        return closeEma5;
    }

    public void setCloseEma5(BigDecimal closeEma5) {
        this.closeEma5 = closeEma5;
    }

    public BigDecimal getCloseEma10() {
        return closeEma10;
    }

    public void setCloseEma10(BigDecimal closeEma10) {
        this.closeEma10 = closeEma10;
    }

    public BigDecimal getCloseEma20() {
        return closeEma20;
    }

    public void setCloseEma20(BigDecimal closeEma20) {
        this.closeEma20 = closeEma20;
    }

    public BigDecimal getCloseEma30() {
        return closeEma30;
    }

    public void setCloseEma30(BigDecimal closeEma30) {
        this.closeEma30 = closeEma30;
    }

    public BigDecimal getCloseEma60() {
        return closeEma60;
    }

    public void setCloseEma60(BigDecimal closeEma60) {
        this.closeEma60 = closeEma60;
    }

    public BigDecimal getCloseEma150() {
        return closeEma150;
    }

    public void setCloseEma150(BigDecimal closeEma150) {
        this.closeEma150 = closeEma150;
    }

    public Long getVolumnEma5() {
        return volumnEma5;
    }

    public void setVolumnEma5(Long volumnEma5) {
        this.volumnEma5 = volumnEma5;
    }

    public Long getVolumnEma10() {
        return volumnEma10;
    }

    public void setVolumnEma10(Long volumnEma10) {
        this.volumnEma10 = volumnEma10;
    }

    public Long getVolumnEma20() {
        return volumnEma20;
    }

    public void setVolumnEma20(Long volumnEma20) {
        this.volumnEma20 = volumnEma20;
    }

    public Long getVolumnEma30() {
        return volumnEma30;
    }

    public void setVolumnEma30(Long volumnEma30) {
        this.volumnEma30 = volumnEma30;
    }

    public Long getVolumnEma60() {
        return volumnEma60;
    }

    public void setVolumnEma60(Long volumnEma60) {
        this.volumnEma60 = volumnEma60;
    }

    public Long getVolumnEma150() {
        return volumnEma150;
    }

    public void setVolumnEma150(Long volumnEma150) {
        this.volumnEma150 = volumnEma150;
    }

    public BigDecimal getAmountEma5() {
        return amountEma5;
    }

    public void setAmountEma5(BigDecimal amountEma5) {
        this.amountEma5 = amountEma5;
    }

    public BigDecimal getAmountEma10() {
        return amountEma10;
    }

    public void setAmountEma10(BigDecimal amountEma10) {
        this.amountEma10 = amountEma10;
    }

    public BigDecimal getAmountEma20() {
        return amountEma20;
    }

    public void setAmountEma20(BigDecimal amountEma20) {
        this.amountEma20 = amountEma20;
    }

    public BigDecimal getAmountEma30() {
        return amountEma30;
    }

    public void setAmountEma30(BigDecimal amountEma30) {
        this.amountEma30 = amountEma30;
    }

    public BigDecimal getAmountEma60() {
        return amountEma60;
    }

    public void setAmountEma60(BigDecimal amountEma60) {
        this.amountEma60 = amountEma60;
    }

    public BigDecimal getAmountEma150() {
        return amountEma150;
    }

    public void setAmountEma150(BigDecimal amountEma150) {
        this.amountEma150 = amountEma150;
    }

    public BigDecimal getCloseEma6() {
        return closeEma6;
    }

    public void setCloseEma6(BigDecimal closeEma6) {
        this.closeEma6 = closeEma6;
    }

    public BigDecimal getCloseEma13() {
        return closeEma13;
    }

    public void setCloseEma13(BigDecimal closeEma13) {
        this.closeEma13 = closeEma13;
    }

    public BigDecimal getCloseEma12() {
        return closeEma12;
    }

    public void setCloseEma12(BigDecimal closeEma12) {
        this.closeEma12 = closeEma12;
    }

    public BigDecimal getCloseEma26() {
        return closeEma26;
    }

    public void setCloseEma26(BigDecimal closeEma26) {
        this.closeEma26 = closeEma26;
    }

    public BigDecimal getDifS() {
        return difS;
    }

    public void setDifS(BigDecimal difS) {
        this.difS = difS;
    }

    public BigDecimal getDeaS() {
        return deaS;
    }

    public void setDeaS(BigDecimal deaS) {
        this.deaS = deaS;
    }

    public BigDecimal getDeaSAngle() {
        return deaSAngle;
    }

    public void setDeaSAngle(BigDecimal deaSAngle) {
        this.deaSAngle = deaSAngle;
    }

    public BigDecimal getDifF() {
        return difF;
    }

    public void setDifF(BigDecimal difF) {
        this.difF = difF;
    }

    public BigDecimal getDeaF() {
        return deaF;
    }

    public void setDeaF(BigDecimal deaF) {
        this.deaF = deaF;
    }

    public BigDecimal getDeaFAngle() {
        return deaFAngle;
    }

    public void setDeaFAngle(BigDecimal deaFAngle) {
        this.deaFAngle = deaFAngle;
    }

    public BigDecimal getKdjK() {
        return kdjK;
    }

    public void setKdjK(BigDecimal kdjK) {
        this.kdjK = kdjK;
    }

    public BigDecimal getKdjD() {
        return kdjD;
    }

    public void setKdjD(BigDecimal kdjD) {
        this.kdjD = kdjD;
    }

    public BigDecimal getKdjJ() {
        return kdjJ;
    }

    public void setKdjJ(BigDecimal kdjJ) {
        this.kdjJ = kdjJ;
    }

    public BigDecimal getTr() {
        return tr;
    }

    public void setTr(BigDecimal tr) {
        this.tr = tr;
    }

    public BigDecimal getAtr() {
        return atr;
    }

    public void setAtr(BigDecimal atr) {
        this.atr = atr;
    }

    public BigDecimal getTrend() {
        return trend;
    }

    public void setTrend(BigDecimal trend) {
        this.trend = trend;
    }

    public BigDecimal getRising() {
        return rising;
    }

    public void setRising(BigDecimal rising) {
        this.rising = rising;
    }

    public BigDecimal getRapidRising() {
        return rapidRising;
    }

    public void setRapidRising(BigDecimal rapidRising) {
        this.rapidRising = rapidRising;
    }

    public BigDecimal getShake() {
        return shake;
    }

    public void setShake(BigDecimal shake) {
        this.shake = shake;
    }

    public BigDecimal getSlump() {
        return slump;
    }

    public void setSlump(BigDecimal slump) {
        this.slump = slump;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public BigDecimal getPvSum1() {
        return pvSum1;
    }

    public void setPvSum1(BigDecimal pvSum1) {
        this.pvSum1 = pvSum1;
    }

    public BigDecimal getPvSum5() {
        return pvSum5;
    }

    public void setPvSum5(BigDecimal pvSum5) {
        this.pvSum5 = pvSum5;
    }

    public BigDecimal getPvSum10() {
        return pvSum10;
    }

    public void setPvSum10(BigDecimal pvSum10) {
        this.pvSum10 = pvSum10;
    }

    public BigDecimal getPvSum20() {
        return pvSum20;
    }

    public void setPvSum20(BigDecimal pvSum20) {
        this.pvSum20 = pvSum20;
    }

    public BigDecimal getPvSum30() {
        return pvSum30;
    }

    public void setPvSum30(BigDecimal pvSum30) {
        this.pvSum30 = pvSum30;
    }

    public BigDecimal getPvSum60() {
        return pvSum60;
    }

    public void setPvSum60(BigDecimal pvSum60) {
        this.pvSum60 = pvSum60;
    }

    public Long getRanking() {
        return ranking;
    }

    public void setRanking(Long ranking) {
        this.ranking = ranking;
    }

    public String getLastFlag() {
        return lastFlag;
    }

    public void setLastFlag(String lastFlag) {
        this.lastFlag = lastFlag == null ? null : lastFlag.trim();
    }

	public Long getAvgVolumn() {
		return avgVolumn;
	}

	public void setAvgVolumn(Long avgVolumn) {
		this.avgVolumn = avgVolumn;
	}

	public Long getAvgHandCount() {
		return avgHandCount;
	}

	public void setAvgHandCount(Long avgHandCount) {
		this.avgHandCount = avgHandCount;
	}

	public BigDecimal getAmountIn() {
		return amountIn;
	}

	public void setAmountIn(BigDecimal amountIn) {
		this.amountIn = amountIn;
	}

	public BigDecimal getAmountOut() {
		return amountOut;
	}

	public void setAmountOut(BigDecimal amountOut) {
		this.amountOut = amountOut;
	}

	public BigDecimal getLargeAmountFlow() {
		return largeAmountFlow;
	}

	public void setLargeAmountFlow(BigDecimal largeAmountFlow) {
		this.largeAmountFlow = largeAmountFlow;
	}
}