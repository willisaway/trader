package com.lishao.system.core.mybatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;

/**
 * 重写mybatisplus的page
 * @author ibm
 *
 */
public class Page<T> extends Pagination {

	private static final long serialVersionUID = 1L;

	/**

	 * 查询数据列表

	 */
	private List<T> records = new ArrayList<T>();//主要改动在这里

	/**

	 * 查询参数

	 */
	private Map<String, Object> condition = new ConcurrentHashMap<String, Object>();

	public Page() {
		/* 注意，传入翻页参数 */
	}

	public Page(int current, int size) {
		super(current, size);
	}

	public Page(int current, int size, String orderByField) {
		super(current, size);
		this.setOrderByField(orderByField);
	}

	public Page(int current, int size, String orderByField,Boolean isAsc) {
		super(current, size);
		this.setOrderByField(orderByField);
		this.setAsc(isAsc);
	}
	
	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public Map<String, Object> getCondition() {
		return condition;
	}

	public void setCondition(Map<String, Object> condition) {
		this.condition = condition;
	}

	@Override
	public String toString() {
		StringBuffer pg = new StringBuffer();
		pg.append(" Page:{ [").append(super.toString()).append("], ");
		if (records != null) {
			pg.append("records-size:").append(records.size());
		} else {
			pg.append("records is null");
		}
		return pg.append(" }").toString();
	}

}
