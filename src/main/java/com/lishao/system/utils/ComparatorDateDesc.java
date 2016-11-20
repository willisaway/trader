package com.lishao.system.utils;

import java.util.Comparator;
import java.util.Date;

public class ComparatorDateDesc implements Comparator<Date> {
	public int compare(Date key1, Date key2) {			
		return key2.compareTo(key1);			
	}
}
