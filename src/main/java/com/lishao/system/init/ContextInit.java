package com.lishao.system.init;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextInit {
	public static ClassPathXmlApplicationContext classPathXmlApplicationContext;
	
	public static void init(){
		//Spring初始化
		classPathXmlApplicationContext = new ClassPathXmlApplicationContext("/spring/**/spring-*.xml");
		classPathXmlApplicationContext.start();
		//Log4J
		PropertyConfigurator.configure(ContextInit.class.getClass().getResource("/").getPath()+"trader/log4j.properties");
	}

	public static void main(String[] args) {
		ContextInit.init();
		Logger logger=Logger.getLogger(ContextInit.class);
		logger.error("Log4J test");
	}
	
	//get set
	public static ClassPathXmlApplicationContext getClassPathXmlApplicationContext() {
		return classPathXmlApplicationContext;
	}

	public static void setClassPathXmlApplicationContext(ClassPathXmlApplicationContext classPathXmlApplicationContext) {
		ContextInit.classPathXmlApplicationContext = classPathXmlApplicationContext;
	}
	
}
