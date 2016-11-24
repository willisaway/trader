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
public class DownloadKLine60F {
	@Resource
	GpStockMetadataService stockMetadataService;
	@Resource
	GpClassMetadataService stockClassMetadataService;
	Logger logger=Logger.getLogger(DownloadKLine60F.class);
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
				DownloadKLine60FThread downloadKLine60FThread = (DownloadKLine60FThread)SpringUtil.getBean("downloadKLine60FThread");
				downloadKLine60FThread.setType(ConstantUtil.objectTypeStock);
				downloadKLine60FThread.setObjectCode(stockList.get(i).getStockCode());
				downloadKLine60FThread.setObjectCodeFull(stockList.get(i).getStockCodeFull());
				downloadKLine60FThread.setDesc("股票"+stockList.get(i).getStockCode()+"60F线数据下载");
				downloadKLine60FThread.setPoolCode("60F线数据盘后");downloadKLine60FThread.setPoolName("60F线数据盘后");
				ThreadPoolManager.addTask(downloadKLine60FThread);
			}
			for(int i=0;i<classList.size();i++){
				DownloadKLine60FThread downloadKLine60FThread = (DownloadKLine60FThread)SpringUtil.getBean("downloadKLine60FThread");
				downloadKLine60FThread.setType(ConstantUtil.objectTypeStockClass);
				downloadKLine60FThread.setObjectCode(classList.get(i).getClassifyCode());
				downloadKLine60FThread.setObjectCodeFull(classList.get(i).getClassifyCodeFull());
				downloadKLine60FThread.setDesc("指数"+stockList.get(i).getStockCode()+"60F线数据下载");
				downloadKLine60FThread.setPoolCode("60F线数据盘后");downloadKLine60FThread.setPoolName("60F线数据盘后");
				ThreadPoolManager.addTask(downloadKLine60FThread);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ContextInit.init();
		DownloadKLine60F downloadKLine60F = (DownloadKLine60F) SpringUtil.getBean("downloadKLine60F"); 
		downloadKLine60F.download();
	}
}
