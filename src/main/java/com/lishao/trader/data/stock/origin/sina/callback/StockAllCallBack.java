package com.lishao.trader.data.stock.origin.sina.callback;

import com.lishao.system.component.threadpool.ThreadExtend;
import com.lishao.trader.data.stock.origin.sina.bean.StockAll;

/**
 * 取数之后的回调方法，抽象类，可以继承多种实现
 * @author ibm
 *
 */
public abstract class StockAllCallBack extends ThreadExtend {
	public StockAll stockAll;
	//get set
	public StockAll getStockAll() {
		return stockAll;
	}
	public void setStockAll(StockAll stockAll) {
		this.stockAll = stockAll;
	}
}
