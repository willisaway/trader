package com.lishao.system.utils;

import java.util.HashMap;
import java.util.Map;

public class ModuleReturn {
	private long returnValue = -1;// 方法返回值//提示信息编码
	private String errorCode = "";// 错误编码
	private String detailMessage = "";// 错误详细信息
	private Map errorParaMap = new HashMap();// 错误信息map
	private Map returnParaMap = new HashMap();// 返回信息map

	public ModuleReturn() {
	}

	public ModuleReturn(long aInitValue) {
		setReturnValue(aInitValue);
	}

	public long getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(long aReturnValue) {
		this.returnValue = aReturnValue;
	}

	public void setReturnValue(long aReturnValue, String aMessage) {
		this.returnValue = aReturnValue;
		detailMessage = aMessage;
	}

	public String getErrorDetailMessage() {
		return detailMessage;
	}

	public void setErrorDetailMessage(String returnDetailMessage) {
		this.detailMessage = returnDetailMessage;
	}

	public void setReturnPara(String aKey, Object aValue) {
		returnParaMap.put(aKey, aValue);
	}

	public void setErrorPara(String aKey, Object aValue) {
		errorParaMap.put(aKey, aValue);
	}

	public Object getReturnPara(String aKey, Object aDefault) {
		Object objRtn = returnParaMap.get(aKey);
		if (objRtn == null) {
			objRtn = aDefault;
		}
		return objRtn;
	}

	public Object getReturnPara(String aKey) {
		return getReturnPara(aKey, null);
	}

	public Map getReturnPara(){
		return returnParaMap;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
