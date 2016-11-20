package com.lishao.trader.data.stock.origin.sina.bean;

import java.util.List;

/**
 * 针对
 * http://money.finance.sina.com.cn/d/api/openapi_proxy.php/?__s=[["hq","hs_a","",0,30,100]]&callback=FDC_DC.theTableData
 * @author ibm
 *
 */
public class StockAll {
	long code;
	String day;
	int count;
	List<String>fields;
	List<List> items;
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<String> getFields() {
		return fields;
	}
	public void setFields(List<String> fields) {
		this.fields = fields;
	}
	public List<List> getItems() {
		return items;
	}
	public void setItems(List<List> items) {
		this.items = items;
	}
	
}
