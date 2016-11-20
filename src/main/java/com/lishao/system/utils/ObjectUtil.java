package com.lishao.system.utils;

public class ObjectUtil {
	public static Object objectIfNull(Object aOrigin,Object aDefault){
	    return aOrigin==null?aDefault:aOrigin;
	}
}
