package com.lishao.system.utils;

import java.util.Comparator;
import java.util.Date;
import java.util.Map;

public class ComparatorStockKlineDAsc implements Comparator<Map> {
	public int compare(Map stockKlineDMap1, Map stockKlineDMap2) {
		Date date1=(Date)stockKlineDMap1.get("sortColumn");
		Date date2=(Date)stockKlineDMap2.get("sortColumn");
		return date1.compareTo(date2);			
	}
}
