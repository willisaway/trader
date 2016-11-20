package com.lishao.trader.task;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lishao.system.init.ContextInit;
import com.lishao.system.utils.SpringUtil;
import com.lishao.trader.data.stock.download.DownloadKLineD;

/***
 * 每日下载任务
 * @author liyougen
 *
 */
public class DailyDownloadTask {
	@Resource
	DownloadKLineD downloadKLineD;
	
	public void execute(){
		downloadKLineD.download();
	}
	
	public static void main(String []args){
		ContextInit.init();
		DailyDownloadTask task = (DailyDownloadTask)SpringUtil.getBean("dailyDownloadTask");;
		task.execute();
	}
}
