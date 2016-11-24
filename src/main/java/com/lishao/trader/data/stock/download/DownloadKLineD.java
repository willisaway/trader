package com.lishao.trader.data.stock.download;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.lishao.system.component.threadpool.ThreadPoolManager;
import com.lishao.system.init.ContextInit;
import com.lishao.system.utils.ConfigUtil;
import com.lishao.system.utils.NumberUtil;
import com.lishao.system.utils.SpringUtil;
import com.lishao.trader.common.ConstantUtil;
import com.lishao.trader.stock.bean.entity.GpClassMetadata;
import com.lishao.trader.stock.bean.entity.GpStockMetadata;
import com.lishao.trader.stock.service.GpClassMetadataService;
import com.lishao.trader.stock.service.GpStockMetadataService;

/**下载股票日线数据入口类
 * 【注意事项】
            新浪财经网等网站，都有rate limit 限制，如果你大量访问新浪网，会被新浪认为是黑客攻击
	所以，稳妥的办法是不要高频率访问新浪
	2300只股票，只取一个季度的数据需要访问 2300次
	如果需要取4个季度的数据的话，那么需要访问 2300 * 4 = 9200次，近10000次，新浪可能会认为你的黑客攻击它
		
	新浪数据复权交易数据比较特别，一个页面只有一个季度的数据，所以，第一次运行最好抽取最近4个季度的数据，这样才能计算 ma120 
	season = 4;//最近4个季度的数据
*/
@Component
public class DownloadKLineD {
	@Resource
	GpStockMetadataService stockMetadataService;
	@Resource
	GpClassMetadataService stockClassMetadataService;
	Logger logger=Logger.getLogger(DownloadKLineD.class);
	/**
	 * 入口方法
	 */
	public void download(){
		Date startTime = new Date(System.currentTimeMillis());
		try {
			//获取需要下载的股票
			List<GpStockMetadata> stockList = stockMetadataService.selectAll();
			//获取需要下载的指数
			List<GpClassMetadata> classList = stockClassMetadataService.selectAll();
			logger.info("准备抓取" + stockList.size() + "个股票的数据;" + classList.size() + "个指数的数据");
			//添加到线程池用来下载日线数据
			for(int i=0;i<stockList.size();i++){
				DownloadKLineDThread downloadKLineDThread = (DownloadKLineDThread)SpringUtil.getBean("downloadKLineDThread");
				downloadKLineDThread.setType(ConstantUtil.objectTypeStock);
				downloadKLineDThread.setObjectCode(stockList.get(i).getStockCode());
				downloadKLineDThread.setObjectCodeFull(stockList.get(i).getStockCodeFull());
				downloadKLineDThread.setStartDate(stockList.get(i).getListingDate());
				downloadKLineDThread.setDesc("股票"+stockList.get(i).getStockCode()+"日线数据下载");
				downloadKLineDThread.setPoolCode("日线数据盘后");downloadKLineDThread.setPoolName("日线数据盘后");
				ThreadPoolManager.addTask(downloadKLineDThread);
			}
			for(int i=0;i<classList.size();i++){
				DownloadKLineDThread downloadKLineDThread = (DownloadKLineDThread)SpringUtil.getBean("downloadKLineDThread");
				downloadKLineDThread.setType(ConstantUtil.objectTypeStockClass);
				downloadKLineDThread.setObjectCode(classList.get(i).getClassifyCode());
				downloadKLineDThread.setDesc("指数"+stockList.get(i).getStockCode()+"日线数据下载");
				downloadKLineDThread.setPoolCode("日线数据盘后");downloadKLineDThread.setPoolName("日线数据盘后");
				ThreadPoolManager.addTask(downloadKLineDThread);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ContextInit.init();
		DownloadKLineD downloadKLineD = (DownloadKLineD) SpringUtil.getBean("downloadKLineD"); 
		downloadKLineD.download();
	}
}
