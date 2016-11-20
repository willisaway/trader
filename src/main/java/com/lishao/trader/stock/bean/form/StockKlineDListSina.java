package com.lishao.trader.stock.bean.form;

import java.math.BigDecimal;
import java.util.Date;

public class StockKlineDListSina {
	/**日期*/
	private Date date;
	/**开盘价*/
	private BigDecimal open = new BigDecimal(0.0);
	/**最高价*/
	private BigDecimal high = new BigDecimal(0.0);
	/**收盘价*/
	private BigDecimal close = new BigDecimal(0.0);
	/**最低价*/
	private BigDecimal low = new BigDecimal(0.0);
	/**成交量*/
	private BigDecimal volume = new BigDecimal(0.0);
	/**成交金额*/
	private BigDecimal amount = new BigDecimal(0.0);
	/**复权因子*/
	private BigDecimal fuquan = new BigDecimal(0.0);
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigDecimal getOpen() {
		return open;
	}
	public void setOpen(BigDecimal open) {
		this.open = open;
	}
	public BigDecimal getHigh() {
		return high;
	}
	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	public BigDecimal getClose() {
		return close;
	}
	public void setClose(BigDecimal close) {
		this.close = close;
	}
	public BigDecimal getLow() {
		return low;
	}
	public void setLow(BigDecimal low) {
		this.low = low;
	}
	public BigDecimal getVolume() {
		return volume;
	}
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getFuquan() {
		return fuquan;
	}
	public void setFuquan(BigDecimal fuquan) {
		this.fuquan = fuquan;
	}
	
}
