package com.lishao.system.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * 读取配置文件的工具
 * @author LYG
 *
 */
public class ConfigUtil {
	static Properties properties = null;
	static String fileName = "trader/config.properties";
	
	private static void load(){
		try {
			java.io.InputStream is = ConfigUtil.class.getClassLoader().getResourceAsStream(fileName);
			if(is!=null){
				properties = new Properties();
				properties.load(is);
			}else{
				if ( ! fileName.startsWith("/") ) {
					fileName = "/" + fileName;
					load();
				} else {
					throw new IOException("Properties could not be loaded.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key){
		if(properties==null){
			load();
		}
		return properties.getProperty(key);
	}
	
	public static int getIntProperty(String key){
		return Integer.parseInt(getProperty(key));
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(ConfigUtil.getProperty("commission"));
	}
}
