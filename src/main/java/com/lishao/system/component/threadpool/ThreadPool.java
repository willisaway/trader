package com.lishao.system.component.threadpool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;

import com.lishao.system.utils.ConfigUtil;
import com.lishao.system.utils.DateUtil;
import com.lishao.system.utils.NumberUtil;

/**
 * 线程池封装类，每个类对应一个线程池
 * @author lishao
 *
 */
public class ThreadPool {
	static Logger logger=Logger.getLogger(ThreadPool.class);
	
	public String code;//线程池代码
	public String name;//线程池名称
	public ThreadPoolExecutor pool = null;
	public boolean printFlag=true;//打印开关，为ture则打印线程池的信息，否则不打印
	public Date startTime = new Date(System.currentTimeMillis());//线程池创建时间
	
	/**
	 * 构造方法
	 */
	public ThreadPool(){
		pool = (ThreadPoolExecutor)Executors.newFixedThreadPool(ConfigUtil.getIntProperty("concurrentLimit"));
		startTime = new Date(System.currentTimeMillis());
	}
	
	public void addTask(ThreadExtend thread){
		pool.execute(thread);
	}
	
	public void printPoolInfo(){
		if(printFlag){
			try{
				logger.info("----------------------------------------------------------------------------------------------");
				logger.info("线程池["+name+"]中等待执行的任务数目："+pool.getQueue().size()+",正在执行的任务数:"+pool.getActiveCount()+",已执行完的任务数目："+pool.getCompletedTaskCount());
				logger.info("完成进度:"+NumberUtil.decimalFormat2(((double)pool.getCompletedTaskCount()*100)/(pool.getCompletedTaskCount()+pool.getActiveCount()+pool.getQueue().size()))+"%");
				Date curTime = new Date(System.currentTimeMillis());
				logger.info("线程池已运行:"+NumberUtil.decimalFormat2((((double)curTime.getTime()-startTime.getTime()))/1000/60)+"分钟");
				logger.info("----------------------------------------------------------------------------------------------");
            }catch(Exception ex){
            	logger.error("打印线程池信息出错");
                ex.printStackTrace();
            }
		}
	}
	//get set
	public ThreadPoolExecutor getPool() {
		return pool;
	}

	public boolean isPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(boolean printFlag) {
		this.printFlag = printFlag;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		logger.info("开始时间" + DateUtil.date2Second(startTime));
		this.startTime = startTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
