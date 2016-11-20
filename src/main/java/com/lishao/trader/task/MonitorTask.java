package com.lishao.trader.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lishao.system.component.threadpool.ThreadPoolManager;

public class MonitorTask {
	public void printPoolInfo(){
		ThreadPoolManager.printPoolInfo();
	}
}
