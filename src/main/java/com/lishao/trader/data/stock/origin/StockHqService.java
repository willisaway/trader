package com.lishao.trader.data.stock.origin;

import java.util.List;

import com.lishao.trader.data.stock.origin.eastmoney.callback.StockHqByClassCallBack;
import com.lishao.trader.data.stock.origin.sina.bean.KLineBean;

public interface StockHqService {
	public List<String> getStockHqListByClassifyCode(String classifyCode,StockHqByClassCallBack callback);
//	public List<GpStockClassMap> getStockClassMap(String classifyCode);
}
