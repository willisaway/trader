package com.lishao.system.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.*;
import java.util.HashMap;
import java.text.NumberFormat;
import java.io.BufferedReader;
import java.io.StringReader;
import java.io.*;

public class StringUtil {
	public StringUtil() {
	}

	/**
	 * 将aObj 转化伟String,如果aObj为null则放回默认值aDefault
	 *
	 * @param aObj Object
	 * @param aDefault String 默认值
	 * @return String
	 */
	public static String objToString(Object aObj, String aDefault) {
		String strRtn = aObj == null ? aDefault : (aObj + "");
		return strRtn;
	}

	public static String objToString(Object aObj) {
		return objToString(aObj, "");
	}

	/**
	 * 将aRepeatNum个aStr以aSep为分隔符连成String
	 *
	 * @param aStr String
	 * @param aSep String
	 * @param aRepeatNum int
	 * @return String
	 */
	public static String repeatString(String aStr, String aSep, int aRepeatNum) {
		StringBuffer sbRtn = new StringBuffer();
		aStr = objToString(aStr);
		aSep = objToString(aSep);
		for (int i = 0; i < aRepeatNum; i++) {
			if (i > 0) {
				sbRtn.append(aSep);
			}
			sbRtn.append(aStr);
		}
		return sbRtn.toString();
	}

	public static String collectionToString(Collection aColl, String aSep){
		String strRtn="";
		strRtn=collectionToString(aColl,aSep,"","");
		return strRtn;
	}
	/**
	 * 将aColl中的元素以aSep为分隔符连成String,当aColl中的元素为null时将元素视为""
	 *
	 * @param aColl Collection
	 * @param aSep String
	 * @return String
	 */
	public static String collectionToString(Collection aColl, String aSep,String aSepBegin,String aSepEnd) {
		StringBuffer sbRtn = new StringBuffer();
		if (aColl == null) {
			aColl = new HashSet();
		}
		aSep = objToString(aSep);
		Iterator iter = aColl.iterator();
		int i = 0;
		while (iter.hasNext()) {
			String strItem = objToString(iter.next());
			strItem=aSepBegin+strItem+aSepEnd;
			if (i > 0) {
				sbRtn.append(aSep);
			}
			sbRtn.append(strItem);
			i++;
		}
		return sbRtn.toString();
	}
	public static String mapToString(Map aMap, List aKeyList, String aSep) {
		if (aMap == null || aKeyList == null) {
			return "";
		}
		StringBuffer sbRtn = new StringBuffer();
		Iterator iter = aKeyList.iterator();
		int i = 0;
		while (iter.hasNext()) {
			if (i > 0) {
				sbRtn.append(aSep);
			}
			String strKey = (String) iter.next();
			sbRtn.append(StringUtil.objToString(aMap.get(strKey)));
			i++;
		}
		return sbRtn.toString();
	}

	/**
	 * 将aMap中的元素以aSep1和aSep2为分隔符连成String,当aColl中的元素为null时将元素视为""
	 *
	 * @param aColl Collection
	 * @param aSep String
	 * @return String
	 */
	public static String mapToString(Map aMap, String aSepAfterKey, String aSepCol) {
		StringBuffer sbRtn = new StringBuffer();
		if (aMap == null) {
			return "";
		}
		Set objSet = aMap.keySet();
		Iterator iter = objSet.iterator();
		int i = 0;
		while (iter.hasNext()) {
			if (i > 0) {
				sbRtn.append(aSepCol);
			}
			String strItem = "" + iter.next();
			sbRtn.append(strItem).append(aSepAfterKey);
			sbRtn.append(aMap.get(strItem));
			i++;
		}
		return sbRtn.toString();
	}

	/**
	 * 将aIt遍历得到的元素以aSep为分隔符连接成字符串,当aIt.next()得到的元素为null时将元素视为""
	 *
	 * @param aIt Iterator
	 * @param aSep String
	 * @return String
	 */
	public static String IteratorToString(Iterator aIt, String aSep) {
		StringBuffer sbRtn = new StringBuffer();
		aSep = objToString(aSep);
		int i = 0;
		while (aIt.hasNext()) {
			String strItem = objToString(aIt.next());
			if (i > 0) {
				sbRtn.append(aSep);
			}
			sbRtn.append(strItem);
			i++;
		}
		return sbRtn.toString();
	}

	/**
	 * 将aNumber按照aFormat格式化为String
	 *
	 * @param aNumber String
	 * @param aFormat String example: "#,#00.0#"
	 * @return String
	 */
	public static String numberToString(String aNumber, String aFormat) {
		String strRtn = "";
		DecimalFormat objFormat = new DecimalFormat();
		try {
			objFormat.applyPattern(aFormat);
			double d = Double.parseDouble(aNumber);
			strRtn = objFormat.format(d);
		} catch (Exception ex) {
			ex.printStackTrace();
			strRtn = null;
		}
		return strRtn;
	}

	/**
	 * 将aDate按照aFormat格式化为String
	 *
	 * @param aDate Date
	 * @param aFormat String example:"yyyy'/'MM'/'dd HH:mm ss"
	 * @return String
	 */
	public static String dateToString(Date aDate, String aFormat) {
		String strRtn = "";
		if (aDate!=null) {
			SimpleDateFormat objFormat = new SimpleDateFormat(aFormat);
			strRtn = objFormat.format(aDate);
		}
		return strRtn;
	}

	/**
	 * 将aDate按照aFormat格式化为Date
	 *
	 * @param aData String
	 * @param aFormat String example:"yyyy'/'MM'/'dd HH:mm ss"
	 * @return Date
	 */
	public static Date stringToDate(String aData, String aFormat) {
		Date objDate = null;
		SimpleDateFormat objFormat = new SimpleDateFormat(aFormat);
		try {
			objDate = objFormat.parse(aData);
		} catch (Exception ex) {
			objDate = null;
		}
		return objDate;
	}

	public static String getCurrentDate(){
		String strRtn = "";
		java.util.Date objDate = new java.util.Date();
		strRtn = dateToString(objDate,"yyyy-MM-dd");
		return strRtn;
	}

	/**
	 * 将aDataString中的aFind替换为AReplace返回结果String
	 *
	 * @param aDataString String
	 * @param aFind String
	 * @param aReplace String
	 * @return String
	 */
	public static String replaceString(String aDataString, String aFind, String aReplace) {
		int iIndex = aDataString.indexOf(aFind);
		String strRtn = "";
		StringBuffer sbBuffer = new StringBuffer(aDataString.length() * 2);
		if (iIndex >= 0) {
			while (iIndex >= 0) {
				sbBuffer.append(aDataString.substring(0, iIndex)).append(aReplace);
				aDataString = aDataString.substring(iIndex + aFind.length());
				iIndex = aDataString.indexOf(aFind);
			}
			sbBuffer.append(aDataString);
			strRtn = sbBuffer.toString();
		}
		else {
			strRtn = aDataString;
		}
		return strRtn;
	}

	public static String replaceString1(String aOrigin, String aSepBegin, String aSepEnd, Map aReplaces) {
		StringBuffer sbRtn = new StringBuffer();
		BufferedReader br = new BufferedReader(new StringReader(aOrigin));
		String strRow = "";
		strRow.matches("");
		int iBL = aSepBegin.length();
		int iEL = aSepEnd.length();
		int iFlag = 0;
		try {
			while ( (strRow = br.readLine()) != null) {
				iFlag++;
				if (iFlag > 1) {
					sbRtn.append("\n");
				}
				int iF = 0;
				int iB = -1;
				int iE = -1;
				iB = strRow.indexOf(aSepBegin, iF);
				if (iB >=0) {
					iE = strRow.indexOf(aSepEnd, iB + iBL);
				}
				while (iB >=0 && iE > 0) {
					sbRtn.append(strRow.substring(iF, iB));
					iF = iE + iEL;
					String strSec = strRow.substring(iB + iBL, iE);
					sbRtn.append(ObjectUtil.objectIfNull(aReplaces.get(strSec),aSepBegin+strSec+aSepEnd).toString());
					iB = strRow.indexOf(aSepBegin, iF);
					if (iB >=0) {
						iE = strRow.indexOf(aSepEnd, iB + iBL);
					}
				}
				sbRtn.append(strRow.substring(iF));
			}
		} catch (Exception ex) {
			sbRtn = new StringBuffer();
		}
		return sbRtn.toString();
	}
	/**
	 * 将类似“aaaa,bbb,ccc,ddd,”的字符串分解为List
	 *
	 * @ param a_origin     字符串
	 * @ param a_separator  分隔符
	 */
	public static List stringToList(String a_origin, String a_separator) {
		ArrayList objList = new ArrayList();
		if (a_origin == null || a_origin.trim().equalsIgnoreCase("") || a_separator == null || a_separator.trim().equalsIgnoreCase("")) {
			return objList;
		}
		int iSep = a_origin.indexOf(a_separator);
		for (; iSep >= 0 && a_origin.length() > 0; ) {
			String strNext = a_origin.substring(0, iSep);
			a_origin = a_origin.substring(iSep + a_separator.length());
			objList.add(strNext);
			iSep = a_origin.indexOf(a_separator);
		}
		if (a_origin.length() > 0) {
			objList.add(a_origin);
		}
		return objList;
	}

	/**
	 * 以a_separator为分隔符返回Iterator对象遍历字符串
	 *
	 * @param a_origin String
	 * @param a_separator String
	 * @return Iterator
	 */
	public static Iterator stringToIterator(String a_origin, String a_separator) {
		if (a_origin == null || a_separator == null || a_origin.equals("") || a_separator.equals("")) {
			return null;
		}
		class it implements Iterator {
			String origin = "";
			String sep = ",";
			int indexSep = -1;
			public it(String a_origin, String a_sep) {
				origin = a_origin;
				sep = a_sep;
				indexSep = a_origin.indexOf(a_sep);
			}

			public boolean hasNext() {
				if (origin == null || origin.equals("")) {
					return false;
				}
				else {
					return true;
				}
			}

			public Object next() {
				String strRtn = "";
				if (indexSep < 0) {
					strRtn = origin;
					origin = "";
				}
				else {
					strRtn = origin.substring(0, indexSep);
					origin = origin.substring(indexSep + sep.length());
					indexSep = origin.indexOf(sep);
				}
				return strRtn;
			}

			public void remove() {}
		}

		return new it(a_origin, a_separator);
	}
	public static List stringToListMap(String aOrigin,String aSepRow,String aSepCol){
		List objRtn=new ArrayList();
		List objHead=new ArrayList();
		Iterator iter = stringToIterator(aOrigin,aSepRow);
		int i=0;
		while (iter.hasNext()) {
			String strData = iter.next()+"";
			if (i==0) {
				objHead=stringToList(strData,aSepCol);
			}else{
				Iterator iter1 = stringToIterator(strData,aSepCol);
				int j=0;
				Map objData=new HashMap();
				while (iter1.hasNext()) {
					String strCol = iter1.next()+"";
					objData.put(objHead.get(j)+"",strCol);
					j++;
				}
				objRtn.add(objData);
			}
			i++;
		}
		return objRtn;
	}
	public static List stringToListMap(String aOrigin, String aSepAfterKey, String aSepBtKeyValue, String aSepBtRow) {
		ArrayList objRtn = new ArrayList();
		if (aOrigin == null || aOrigin.trim().equals("")) {
			return objRtn;
		}
		if (aSepAfterKey == null || aSepAfterKey.trim().equals("")) {
			return objRtn;
		}
		if (aSepBtKeyValue == null || aSepBtKeyValue.trim().equals("")) {
			return objRtn;
		}
		if (aSepBtRow == null || aSepBtRow.trim().equals("")) {
			return objRtn;
		}
		Iterator iter = StringUtil.stringToIterator(aOrigin, aSepBtRow);
		while (iter.hasNext()) {
			String item = (String) iter.next();
			Map objMap = StringUtil.stringToMap(item, aSepAfterKey, aSepBtKeyValue);
			if (objMap != null && objMap.size() > 0) {
				objRtn.add(objMap);
			}
		}
		return objRtn;
	}

	public static Map stringToMap(String aOrigin, String aSepAfterKey, String aSepBtKeyValue) {
		Map objRtn = new HashMap();
		if (aOrigin == null || aOrigin.trim().equals("")) {
			return objRtn;
		}
		if (aSepAfterKey == null || aSepAfterKey.trim().equals("")) {
			return objRtn;
		}
		if (aSepBtKeyValue == null || aSepBtKeyValue.trim().equals("")) {
			return objRtn;
		}
		Iterator iter = StringUtil.stringToIterator(aOrigin, aSepBtKeyValue);
		while (iter.hasNext()) {
			String item = "" + iter.next();
			if (!item.equals("")) {
				List objList = StringUtil.stringToList(item, aSepAfterKey);
				String name = objList.get(0) + "";
				String value = "";
				if (objList.size() > 1) {
					value = objList.get(1) + "";
				}
				objRtn.put(name, value);
			}
		}
		return objRtn;
	}

	// 加入金额格式化代码
	// add by dengjie on 2005-12-12
	public static String CashAddComma(double Cash) throws NumberFormatException {
		String tempstr = NumberFormat.getInstance().format(Cash);
		if (tempstr.indexOf(".") == -1) {
			tempstr += ".00";
		}
		int dotpos = tempstr.indexOf(".");
		int len = tempstr.length();
		if ( (len - dotpos) == 2) {
			tempstr += "0";
		}
		return tempstr;
	}

	/**
	 * @todo 加逗号
	 * @param Cash String
	 * @throws NumberFormatException
	 * @return String
	 */
	public static String CashAddComma(String Cash) throws NumberFormatException {
		Cash=Cash.replaceAll(",","");
		double temp = Double.parseDouble(CashFormat(Cash));
		return CashAddComma(temp);
	}

	public static String CashFormat(double Cash) throws NumberFormatException {
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("###.##");
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		return df.format(Cash);
	}

	/**
	 * @todo 小数位格式化
	 * @param Cash String
	 * @throws NumberFormatException
	 * @return String
	 */
	public static String CashFormat(String Cash) throws NumberFormatException {
		double temp = Double.parseDouble(Cash.trim());
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("###.##");
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		return df.format(temp);
	}

	/**
	 * @todo 人民币小写金额转换成大写, 必须输入格式化的字符串('#########.##'), 可以包含逗号
	 * @param Cash String
	 * @return String
	 */
	public static String CashLower2Upper(String Cash) {
		String strCash = Cash;
		double CashNum;
		boolean isplus = true;
		try {
			CashNum = Double.parseDouble(strCash);
		} catch (Exception ex) {
			return "";
		}
		if (CashNum == 0.00) {
			return "人民币(大写)零元整";
		}
		else {
			strCash = CashRemoveComma(strCash);
			boolean nFlag = false; // 是否为负数
			if (strCash.indexOf("-") == 0) {
				nFlag = true;
			}
			// 校验是否为合法数字
			if (strCash.startsWith("-")) {
				strCash = replaceString(strCash, "-", "");
				isplus = false;
			}
			String strRet = ""; // 输出大写字符串变量
			boolean bFlag = false; // 改变对'零'的判断条件变量
			boolean zFlag = true;
			// 得到小数点位置
			int nDotPos = strCash.indexOf("."); // 返回小数点的开始位置
			if (nDotPos == -1) {
				nDotPos = strCash.length(); // 判断是否为整数
				// 只取小数点后二位
			}
			if (nDotPos < (strCash.length() - 3)) {
				strCash = strCash.substring(0, strCash.indexOf(".") + 3);
				// 先取小数点后的数
			}
			for (int i = nDotPos + 1; i < strCash.length(); i++) {
				if (Integer.parseInt(strCash.substring(i, i + 1)) != 0) {
					strRet = strRet + GetNumber(Integer.parseInt(strCash.substring(i, i + 1))) + GetUnit(nDotPos - i);
					zFlag = false;
				}
			}
			if (!zFlag && (strRet.indexOf("角") < 0) && (CashNum >= 1.00)) {
				strRet = "零" + strRet;
				// 最后位不为'角'
			}
			if (zFlag) {
				strRet += "整";
				// 取小数点前的数,i用作判断位数
			}
			for (int i = nDotPos - 1; i >= 0; i--) {
				if (Integer.parseInt(strCash.substring(i, i + 1)) != 0) {
					strRet = GetNumber(Integer.parseInt(strCash.substring(i, i + 1))) + GetUnit(nDotPos - i) + strRet;
					bFlag = false;
				}
				else {
					if (!bFlag && ( (nDotPos - i) != 1)) {
						strRet = "零" + strRet; // 如果以前没遇到过0则这里加“零”，否则不加，整数最后一位也不加
					}
					bFlag = true;
					if ( ( ( (nDotPos - i) == 5) && (replaceString(strCash.substring( ( ( (i - 3) > 0) ? (i - 3) : 0), (i + 1)), "0", "").length() != 0)) ||
							( ( (nDotPos - i) == 9) && (replaceString(strCash.substring(0, (i + 1)), "0",
									"").length() != 0)) || ( ( (nDotPos - i) == 13) && (replaceString(strCash.substring( ( ( (i - 3) > 0) ? (i - 3) : 0), (i + 1)), "0", "").length() != 0))) {
						strRet = GetUnit(nDotPos - i) + strRet;
					}
					else if ( ( (nDotPos - i) == 1) && (Float.parseFloat(strCash) >= 1.00)) {
						strRet = "元" + strRet;
					}
				}
			}
			if (nFlag) {
				strRet = "负" + strRet;
			}
			return "人民币(大写)" + strRet + "";
		}
	}
	/**
	 * @todo 人民币小写金额转换成大写, 必须输入格式化的字符串('#########.##'), 可以包含逗号
	 * 不包含提示文字：人民币(大写)
	 * @param Cash String
	 * @return String
	 */
	public static String CashLower2UpperWithoutInfo(String Cash) {
		String strCash = Cash;
		double CashNum;
		boolean isplus = true;
		try {
			CashNum = Double.parseDouble(strCash);
		} catch (Exception ex) {
			return "";
		}
		if (CashNum == 0.00) {
			return "零元整";
		}
		else {
			strCash = CashRemoveComma(strCash);
			boolean nFlag = false; // 是否为负数
			if (strCash.indexOf("-") == 0) {
				nFlag = true;
			}
			// 校验是否为合法数字
			if (strCash.startsWith("-")) {
				strCash = replaceString(strCash, "-", "");
				isplus = false;
			}
			String strRet = ""; // 输出大写字符串变量
			boolean bFlag = false; // 改变对'零'的判断条件变量
			boolean zFlag = true;
			// 得到小数点位置
			int nDotPos = strCash.indexOf("."); // 返回小数点的开始位置
			if (nDotPos == -1) {
				nDotPos = strCash.length(); // 判断是否为整数
				// 只取小数点后二位
			}
			if (nDotPos < (strCash.length() - 3)) {
				strCash = strCash.substring(0, strCash.indexOf(".") + 3);
				// 先取小数点后的数
			}
			for (int i = nDotPos + 1; i < strCash.length(); i++) {
				if (Integer.parseInt(strCash.substring(i, i + 1)) != 0) {
					strRet = strRet + GetNumber(Integer.parseInt(strCash.substring(i, i + 1))) + GetUnit(nDotPos - i);
					zFlag = false;
				}
			}
			if (!zFlag && (strRet.indexOf("角") < 0) && (CashNum >= 1.00)) {
				strRet = "零" + strRet;
				// 最后位不为'角'
			}
			if (zFlag) {
				strRet += "整";
				// 取小数点前的数,i用作判断位数
			}
			for (int i = nDotPos - 1; i >= 0; i--) {
				if (Integer.parseInt(strCash.substring(i, i + 1)) != 0) {
					strRet = GetNumber(Integer.parseInt(strCash.substring(i, i + 1))) + GetUnit(nDotPos - i) + strRet;
					bFlag = false;
				}
				else {
					if (!bFlag && ( (nDotPos - i) != 1)) {
						strRet = "零" + strRet; // 如果以前没遇到过0则这里加“零”，否则不加，整数最后一位也不加
					}
					bFlag = true;
					if ( ( ( (nDotPos - i) == 5) && (replaceString(strCash.substring( ( ( (i - 3) > 0) ? (i - 3) : 0), (i + 1)), "0", "").length() != 0)) ||
							( ( (nDotPos - i) == 9) && (replaceString(strCash.substring(0, (i + 1)), "0",
									"").length() != 0)) || ( ( (nDotPos - i) == 13) && (replaceString(strCash.substring( ( ( (i - 3) > 0) ? (i - 3) : 0), (i + 1)), "0", "").length() != 0))) {
						strRet = GetUnit(nDotPos - i) + strRet;
					}
					else if ( ( (nDotPos - i) == 1) && (Float.parseFloat(strCash) >= 1.00)) {
						strRet = "元" + strRet;
					}
				}
			}
			if (nFlag) {
				strRet = "负" + strRet;
			}
			return strRet + "";
		}
	}

	/**
	 * @todo 去除逗号
	 * @param Cash String
	 * @return String
	 */
	public static String CashRemoveComma(String Cash) {
		return replaceString(Cash, ",", "");
	}
	/**
	 * 日期大写
	 * @param aDate String
	 * @param aFormat String
	 * @return String
	 */

	public static String dateToUpper(String aDate,String aFormat){
		String strRtn="";
		Date dDate=stringToDate(aDate,aFormat);
		if (dDate!=null) {
			Calendar ca=Calendar.getInstance();
			ca.setTime(dDate);
			int iYear=ca.get(Calendar.YEAR);
			int iMonth=ca.get(Calendar.MONTH)+1;
			int iDay=ca.get(Calendar.DAY_OF_MONTH);
			String strYear=iYear+"";
			for (int i = 0; i < strYear.length(); i++) {
				strRtn+=GetNumber(Integer.parseInt(strYear.charAt(i)+""));
			}
			strRtn=strRtn+"年"+(iMonth>10?"":"零")+ConvertInt(iMonth+"")+"月"+(iDay>10?"":"零")+ConvertInt(iDay+"")+"日";
		}
		return strRtn;
	}
	private static String[] cstr={"零","壹","贰","叁","肆", "伍", "陆","柒","捌","玖"};
	private  static String[] wstr={"","","拾","佰","仟","万","拾","佰","仟","亿","拾","佰","仟"};
	public static String ConvertInt(String str)
	{
		int len=str.length();
		int i;
		String tmpstr,rstr;
		rstr="";
		for(i=1;i<=len;i++)
		{
			tmpstr=str.charAt(len-i)+"";
			rstr=cstr[Integer.parseInt(tmpstr)]+wstr[i]+rstr;
		}
		rstr=rstr.replaceAll("拾零","拾");
		rstr=rstr.replaceAll("零拾","零");
		rstr=rstr.replaceAll("零佰","零");
		rstr=rstr.replaceAll("零仟","零");
		rstr=rstr.replaceAll("零万","万");
		for(i=1;i<=6;i++)
			rstr=rstr.replaceAll("零零","零");
		rstr=rstr.replaceAll("零万","零");
		rstr=rstr.replaceAll("零亿","亿");
		rstr=rstr.replaceAll("零零","零");
		return rstr;
	}


	private static String GetNumber(int nNum) {
		String strRet = "";
		switch (nNum) {
		case 0:
			strRet = "零";
			break;
		case 1:
			strRet = "壹";
			break;
		case 2:
			strRet = "贰";
			break;
		case 3:
			strRet = "叁";
			break;
		case 4:
			strRet = "肆";
			break;
		case 5:
			strRet = "伍";
			break;
		case 6:
			strRet = "陆";
			break;
		case 7:
			strRet = "柒";
			break;
		case 8:
			strRet = "捌";
			break;
		case 9:
			strRet = "玖";
			break;
		}
		return strRet;
	}

	private static String GetUnit(int nUnit) {
		String strRet = "";
		switch (nUnit) {
		case -2:
			strRet = "分";
			break;
		case -1:
			strRet = "角";
			break;
		case 1:
			strRet = "元";
			break;
		case 2:
			strRet = "拾";
			break;
		case 3:
			strRet = "佰";
			break;
		case 4:
			strRet = "仟";
			break;
		case 5:
			strRet = "万";
			break;
		case 6:
			strRet = "拾";
			break;
		case 7:
			strRet = "佰";
			break;
		case 8:
			strRet = "仟";
			break;
		case 9:
			strRet = "亿";
			break;
		case 10:
			strRet = "拾";
			break;
		case 11:
			strRet = "佰";
			break;
		case 12:
			strRet = "仟";
			break;
		case 13:
			strRet = "万";
			break;
		case 14:
			strRet = "拾";
			break;
		case 15:
			strRet = "佰";
			break;
		case 16:
			strRet = "仟";
			break;
		case 17:
			strRet = "亿";
			break;
		}
		return strRet;
	}

	/**
	 * 遍历参数 aPara 获得m1();m2();asdfh;m3(asdlf,m4(m5(),asdf,sadf),asdlf,asdf)
	 * @param aPara String 例如 "m1(),m2(),asdfh,m3(asdlf,m4(m5(),asdf,sadf),asdlf,asdf)"
	 * @param aSepLeft String 例如 "("
	 * @param aSepRight String 例如 ")"
	 * @param aSepPara String 例如 ","
	 * @return Iterator
	 */
	public static Iterator toIterator(String aPara,String aSepLeft,String aSepRight,String aSepPara){
		class MethodParaIterator implements Iterator{
			public String sepPara="";
			public String sepLeft="";
			public String sepRight="";
			public String remain=null;
			public String next=null;
			public boolean hasNext(){
				if (remain==null||remain.equals("")) {
					next=null;
					return false;
				}
				int indexSepLeft=-1,indexSepRight=-1,indexSepPara=-1,iNumLeft=-1,indexLast=-1;
				indexSepLeft=remain.indexOf(sepLeft);
				indexSepRight=remain.indexOf(sepRight);
				indexSepPara=remain.indexOf(sepPara);
				//if the first seprator is sepPara
				if ((indexSepPara>0)&&(indexSepPara<indexSepLeft||indexSepLeft<0)&&(indexSepPara<indexSepRight||indexSepRight<0)){
					next=remain.substring(0,indexSepPara);
					remain=remain.substring(indexSepPara+sepPara.length());
					return true;
				}
				iNumLeft=0;
				while (indexSepLeft>0||indexSepRight>0) {
					//if the first seprator is sepLeft
					if (indexSepLeft>0&&(indexSepLeft<indexSepRight||indexSepRight<0)) {
						iNumLeft++;
						indexSepLeft=remain.indexOf(sepLeft,indexSepLeft+sepLeft.length());
						//if the first seprator is sepRight
					}else if (indexSepRight>0&&(indexSepRight<indexSepLeft||indexSepLeft<0)) {
						iNumLeft--;
						indexLast=indexSepRight+sepRight.length();
						indexSepRight=remain.indexOf(sepRight,indexSepRight+sepRight.length());
					}
					if (iNumLeft==0) {
						next=remain.substring(0,indexLast);
						remain=remain.substring(indexLast);
						if (remain.length()==0) {
							return true;
						}
						if (remain.length()>0||remain.indexOf(sepPara)==0) {
							remain=remain.substring(sepPara.length());
							return true;
						}else{
							next="";
							remain="";
							return false;
						}
					}else if(iNumLeft<0){
						next=null;
						remain="";
						return false;
					}
				}
				if (iNumLeft==0) {
					next=remain;
					remain="";
					return true;
				}else{
					next="";
					remain="";
					return false;
				}
			}
			public Object next(){
				return next;
			}
			public void remove(){
			}
		}
		MethodParaIterator objRtn=new MethodParaIterator();
		objRtn.remain=aPara;
		objRtn.sepLeft=aSepLeft;
		objRtn.sepRight=aSepRight;
		objRtn.sepPara=aSepPara;
		return objRtn;
	}
	public static String lPad(String aStr,char aChar,int aLen){
		String strRtn="";
		strRtn=objToString(aStr);
		if (strRtn.length()<=aLen) {
			strRtn=repeatString(aChar+"","",aLen-strRtn.length())+strRtn;
		}else{
			strRtn=strRtn.substring(0,aLen);
		}
		return strRtn;
	}
	public static String rPad(String aStr,char aChar,int aLen){
		String strRtn="";
		strRtn=objToString(aStr);
		if (strRtn.length()<=aLen) {
			strRtn=strRtn+repeatString(aChar+"","",aLen-strRtn.length());
		}else{
			strRtn=strRtn.substring(0,aLen);
		}
		return strRtn;
	}
	public static void toFile(String aString, String aFile,boolean aAppend) {
		PrintWriter pw = null;
		try {
			File objFile=new File(aFile);
			if (!objFile.exists()) {
				if(!objFile.getParentFile().exists()){
					objFile.getParentFile().mkdirs();
				}
				objFile.createNewFile();
			}
			pw = new PrintWriter(new BufferedWriter(new FileWriter(aFile,aAppend)));
			pw.print(aString);
			pw.flush();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally{
			pw.close();
		}
	}

	public static List getParentInnerCodes(String aInnerCode,int aSecLength){
		List objRtn=new ArrayList();
		while (aInnerCode.length()>=aSecLength) {
			objRtn.add(aInnerCode);
			aInnerCode=aInnerCode.substring(0,aInnerCode.length()-aSecLength);
		}
		return objRtn;
	}
	public static void main(String[] arg) {
		String innerCode="100100020003";
		//	    System.out.println(StringUtils.getParentInnerCodes(innerCode,4));
		//	    String aa=dateToUpper("2006-01-01","yyyy-MM-dd");
		//	    System.out.println("aa:"+aa);
		//	    aa=dateToUpper("2006-11-10","yyyy-MM-dd");
		//	    System.out.println("aa:"+aa);
		//	    aa=dateToUpper("2006-1-1","yyyy-MM-dd");
		//	    System.out.println("aa:"+aa);
		//	    String str = numberToString("-765723435.675", "#,##0.00;");
		//	    System.out.println("str:" + str);
		//	    String strTemp = StringUtils.IteratorToString(StringUtils.stringToIterator(",a,dfg,345,fdhg,vbn,asdf,fgh", ","), "||");
		//	    System.out.println("strTemp:" + strTemp);
		//	    System.getProperties().list(System.out);
		//	    System.out.println(System.getProperty("java.class.path"));
	}
}
