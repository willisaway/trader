package com.lishao.trader.data.stock.download;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.lishao.system.component.threadpool.ThreadPoolManager;
import com.lishao.system.init.ContextInit;
import com.lishao.system.utils.SpringUtil;
import com.lishao.trader.common.ConstantUtil;
import com.lishao.trader.stock.bean.entity.GpClassMetadata;
import com.lishao.trader.stock.bean.entity.GpStockMetadata;
import com.lishao.trader.stock.service.GpClassMetadataService;
import com.lishao.trader.stock.service.GpStockMetadataService;

@Component
public class DownloadKLine30F {
	@Resource
	GpStockMetadataService stockMetadataService;
	@Resource
	GpClassMetadataService stockClassMetadataService;
	Logger logger=Logger.getLogger(DownloadKLine30F.class);
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
				DownloadKLine30FThread downloadKLine30FThread = (DownloadKLine30FThread)SpringUtil.getBean("downloadKLine30FThread");
				downloadKLine30FThread.setType(ConstantUtil.objectTypeStock);
				downloadKLine30FThread.setObjectCode(stockList.get(i).getStockCode());
				downloadKLine30FThread.setObjectCodeFull(stockList.get(i).getStockCodeFull());
				downloadKLine30FThread.setDesc("股票"+stockList.get(i).getStockCode()+"30F线数据下载");
				downloadKLine30FThread.setPoolCode("30F线数据盘后");downloadKLine30FThread.setPoolName("30F线数据盘后");
				ThreadPoolManager.addTask(downloadKLine30FThread);
			}
			for(int i=0;i<classList.size();i++){
				DownloadKLine30FThread downloadKLine30FThread = (DownloadKLine30FThread)SpringUtil.getBean("downloadKLine30FThread");
				downloadKLine30FThread.setType(ConstantUtil.objectTypeStockClass);
				downloadKLine30FThread.setObjectCode(classList.get(i).getClassifyCode());
				downloadKLine30FThread.setObjectCodeFull(classList.get(i).getClassifyCodeFull());
				downloadKLine30FThread.setDesc("指数"+stockList.get(i).getStockCode()+"30F线数据下载");
				downloadKLine30FThread.setPoolCode("30F线数据盘后");downloadKLine30FThread.setPoolName("30F线数据盘后");
				ThreadPoolManager.addTask(downloadKLine30FThread);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ContextInit.init();
		DownloadKLine30F downloadKLine30F = (DownloadKLine30F) SpringUtil.getBean("downloadKLine30F"); 
		downloadKLine30F.download();
	}
}
