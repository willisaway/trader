package com.lishao.trader.data.stock.origin;

import java.util.List;
import java.util.Map;

import com.lishao.trader.data.stock.origin.sina.bean.KLineBean;

public interface StockKlineService {
	public List<Map> getKLineD(String objectCode,String objectCodeFull,String startPeriod);
	public List<Map> getKLineW(String objectCode,String objectCodeFull,String startPeriod);
	public List<Map> getKLineM(String objectCode,String objectCodeFull,String startPeriod);
	public List<Map> getKLine15F(String objectCode,String objectCodeFull,String startPeriod);
	public List<Map> getKLine30F(String objectCode,String objectCodeFull,String startPeriod);
	public List<Map> getKLine60F(String objectCode,String objectCodeFull,String startPeriod);
	/**
	 * 
	 * @param objectCodeFull
	 * @param scale 15分传15,日线传240,周线传120,月线传7200
	 * @return
	 */
	public List<Map> getKLine(String objectCode,String objectCodeFull,String scale,String startPeriod);
}
