package com.lishao.system.component.threadpool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 线程池管理类
 * @author lishao
 *
 */
public class ThreadPoolManager {
	static Logger logger = Logger.getLogger(ThreadPoolManager.class);
	private static Map<String,ThreadPool> pools = new <String,ThreadPool> HashMap();//所有的线程池
	/**
	 * 将任务放置到所属线程池
	 * @param poolCode
	 * @param thread
	 */
	public static void addTask(ThreadExtend thread){
		ThreadPool pool = pools.get(thread.getPoolCode());
		if(pool==null){
			pool=new ThreadPool();
			pool.setCode(thread.getPoolCode());pool.setName(thread.getPoolName());
			pools.put(thread.getPoolCode(), pool);
		}
		pool.addTask(thread);
	}
	
	/**
	 * 打印线程池信息
	 * @param poolCode
	 */
	public static void printPoolInfo(String poolCode){
		ThreadPool pool = pools.get(poolCode);
		printPoolInfo(pool);
	}
	public static void printPoolInfo(ThreadPool pool){
		if(pool!=null){
			pool.printPoolInfo();
		}
	}
	public static void printPoolInfo(){
		Iterator it = pools.entrySet().iterator();
		logger.info("打印线程池信息:");
		logger.info("**********************************************************************************************");
		while(it.hasNext()){
			Map.Entry<String,ThreadPool> mapEntry=(Map.Entry<String,ThreadPool>)it.next();
			printPoolInfo(mapEntry.getValue());
		}
		logger.info("**********************************************************************************************");
	}
}
