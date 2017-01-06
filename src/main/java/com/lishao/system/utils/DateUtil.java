package com.lishao.system.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	//以下是日期转换成字符串方法
	public static String date2String(Date date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	public static String date2Day(Date date){
		return date2String(date,"yyyy-MM-dd");
	}
	//日期转化成时分秒格式
	public static String date2Second(Date date){
		return date2String(date,"yyyy-MM-dd HH:mm:ss");
	}
	public static boolean isSameWeek(String date1,String date2){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(date1);
			d2 = format.parse(date2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSameWeek(d1, d2);
	}
	public static boolean isSameWeek(Date date1, Date date2) {
		Date d1 = date1;
		Date d2 = date2;
		//首先判断天数间隔是否大于7
		if(Math.abs(d1.getTime()-d2.getTime())/1000/60/60/24>7){
			return false;
		}
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(d1);
		cal2.setTime(d2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		// subYear==0,说明是同一年
		if (subYear == 0) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		// 例子:cal1是"2005-1-1"，cal2是"2004-12-25"
		// java对"2004-12-25"处理成第52周
		// "2004-12-26"它处理成了第1周，和"2005-1-1"相同了
		// 大家可以查一下自己的日历
		// 处理的比较好
		// 说明:java的一月用"0"标识，那么12月用"11"
		else if (subYear == 1 && cal2.get(Calendar.MONTH) == 11) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		// 例子:cal1是"2004-12-31"，cal2是"2005-1-1"
		else if (subYear == -1 && cal1.get(Calendar.MONTH) == 11) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}
	public static boolean isSameMonth(String date1,String date2){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(date1);
			d2 = format.parse(date2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSameMonth(d1, d2);
	}
	public static boolean isSameMonth(Date date1, Date date2) {
		Date d1 = date1;
		Date d2 = date2;
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(d1);
		cal2.setTime(d2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		//subYear==0,说明是同一年
		if (subYear == 0) {
			if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH))
				return true;
		}
		return false;
	}
	public static void main(String [] args) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sdf.parse("2005-01-01");
		Date date2 = sdf.parse("2004-12-26");
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		System.out.println(cal1.get(Calendar.YEAR));
		System.out.println(cal1.get(Calendar.WEEK_OF_YEAR));
		System.out.println(cal2.get(Calendar.YEAR));
		System.out.println(cal2.get(Calendar.WEEK_OF_YEAR));
		System.out.println(isSameMonth("2004-12-01", "2004-12-31"));
	}
}
