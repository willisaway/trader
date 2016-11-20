package com.lishao.system.utils;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.lishao.system.init.ContextInit;
import com.lishao.trader.data.stock.download.DownloadKLineD;

/*
 * Spring工具类
 */
public class SpringUtil {
	public static Object getBean(String paramString) {
		Object rtnObj=null;
		if (isEmpty(paramString)) {
			return null;
		}
		WebApplicationContext localWebApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		if(localWebApplicationContext!=null){//web 方式启动
			rtnObj = localWebApplicationContext.getBean(paramString);
		}else if(ContextInit.getClassPathXmlApplicationContext()!=null){//Java运行
			rtnObj = ContextInit.getClassPathXmlApplicationContext().getBean(paramString);
		}
		return rtnObj;
	}

	private static boolean isEmpty(String paramString) {
		return (paramString == null) || (paramString.trim().equals(""));
	}
}
