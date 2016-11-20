package com.lishao.system.utils;

import java.text.DecimalFormat;

public class NumberUtil {
	final static DecimalFormat df0 = (DecimalFormat) DecimalFormat.getInstance(); 
	final static DecimalFormat df2 = (DecimalFormat) DecimalFormat.getInstance();
	final static DecimalFormat df3 = (DecimalFormat) DecimalFormat.getInstance();
	static {
		df0.applyPattern("0");
		df2.applyPattern("0.00");
		df3.applyPattern("0.000");
	}
	public static String decimalFormat0(double dData){
		return df0.format(dData);
	}
	
	public static String decimalFormat2(double dData){
		return df2.format(dData);
	}
	
	public static String decimalFormat3(double dData){
		return df3.format(dData);
	}
	
	public static double parseDouble(String data){
		double result = 0.0;
		try {
			if (data == null || data.trim().length() == 0)
				result = Double.valueOf(0.0);
			else
				result = Double.valueOf(data.trim());
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			System.out.print(data);
		}
		return result;
	}
}
