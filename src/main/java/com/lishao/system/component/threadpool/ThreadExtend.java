package com.lishao.system.component.threadpool;

import java.util.Date;

import org.apache.log4j.Logger;

import com.lishao.system.utils.DateUtil;
import com.lishao.system.utils.NumberUtil;

/**
 * 系统中线程的父类，提供监控功能
 * @author lishao
 *
 */
public abstract class ThreadExtend extends Thread {
	public static Logger logger=Logger.getLogger(Thread.class);
	public String poolCode="default";//当前线程执行的线程池代码,如果创建线程时没有指定代码，将放入默认线程池中执行
	public String poolName="default";//线程池名称
	public String desc;//线程描述
	public Date startTime;//线程开始执行时间
	@Override
	public final void run() {
		beforeRun();
		runCore();
		afterRun();
	}

	public abstract void runCore();
	
	public void beforeRun(){
		startTime = new Date(System.currentTimeMillis());
		logger.info(desc+",开始时间" + DateUtil.date2Second(startTime));
	}
	public void afterRun(){
		Date endTime = new Date(System.currentTimeMillis());
		logger.info(desc+",结束时间" + DateUtil.date2Second(endTime));
		logger.info(desc+"耗时:"+NumberUtil.decimalFormat2((((double)endTime.getTime()-startTime.getTime()))/1000)+"秒");
		ThreadPoolManager.printPoolInfo(poolCode);
	}
	//get set
	public String getPoolCode() {
		return poolCode;
	}
	public void setPoolCode(String poolCode) {
		this.poolCode = poolCode;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPoolName() {
		return poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}
}
