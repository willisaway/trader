package com.lishao.trader.data.stock.origin;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.lishao.system.utils.ModuleReturn;
import com.lishao.trader.data.stock.origin.sina.bean.StockAll;
import com.lishao.trader.data.stock.origin.sina.callback.StockAllCallBack;
import com.lishao.trader.stock.bean.entity.GpStockClassMap;

/**
 * 获取外部数据接口
 * @author neusoft
 *
 */
public interface OriginDataService {
	//刷新所有股票的实时行情
	public StockAll getStockHqAll(StockAllCallBack callback);
	public List<Map> getStockFuquanKlineD(String objectCode,Calendar calendar);
	public List<Map> getFundKlineD(String objectCode,Calendar calendar);
	public List<Map> getClassKlineD(String objectCode,Calendar calendar);
	public List<Map> getKlineD(String type,String stockCode,Calendar calendar);
	public ModuleReturn getTradeDetail(String type,String objectCode,String periodCode);
	public List<GpStockClassMap> getStockClassMap(String classifyCode);
}
