package com.lishao.system.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static Date formatPeriodCodeD(String periodCode){
		Date rtnDate=null;
		String format = "yyyy-MM-dd";
//		if (periodCode.indexOf('-') > 0) {
//			if (periodCode.length() == 10)
//				format = "yyyy-MM-dd";
//			else
//				format = "yyyy-M-d";
//		} else if (periodCode.indexOf('.') > 0) {
//			if (periodCode.length() == 10)
//				format = "yyyy.MM.dd";
//			else
//				format = "yyyy.M.d";
//		} else if (periodCode.indexOf('/') > 0) {
//			if (periodCode.length() == 10)
//				format = "yyyy/MM/dd";
//			else
//				format = "yyyy/M/d";
//		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			rtnDate = sdf.parse(periodCode);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnDate;
	}
	public static String date2Day(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	//日期转化成时分秒格式
	public static String date2Second(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
}
