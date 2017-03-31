package com.lishao.system.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class ModuleReturn implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long returnValue = -1;// 方法返回值//提示信息编码
	private String returnCode = "";// 错误编码
	private String returnMessage = "";// 错误详细信息
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
		returnMessage = aMessage;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public void setReturnPara(String aKey, Object aValue) {
		returnParaMap.put(aKey, aValue);
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
	
	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
}
