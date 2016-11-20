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
public class DownloadKLine15F {
	@Resource
	GpStockMetadataService stockMetadataService;
	@Resource
	GpClassMetadataService stockClassMetadataService;
	Logger logger=Logger.getLogger(DownloadKLine15F.class);
	/**
	 * 入口方法
	 */
	public void download(){
		Date startTime = new Date(System.currentTimeMillis());
		try {
			//获取需要下载的股票
			List<GpStockMetadata> stockList = stockMetadataService.selectAll();
			//获取需要下载的指数
			List<GpClassMetadata> classList = stockClassMetadataService.selectByDimensionCode("SYZS");
			logger.info("准备抓取" + stockList.size() + "个股票的数据;" + classList.size() + "个指数的数据");
			//添加到线程池用来下载日线数据
			for(int i=0;i<stockList.size();i++){
				DownloadKLine15FThread downloadKLine15FThread = (DownloadKLine15FThread)SpringUtil.getBean("downloadKLine15FThread");
				downloadKLine15FThread.setType(ConstantUtil.objectTypeStock);
				downloadKLine15FThread.setObjectCode(stockList.get(i).getStockCode());
				downloadKLine15FThread.setObjectCodeFull(stockList.get(i).getStockCodeFull());
				downloadKLine15FThread.setDesc("股票"+stockList.get(i).getStockCode()+"15F线数据下载");
				downloadKLine15FThread.setPoolCode("15F线数据盘后");downloadKLine15FThread.setPoolName("15F线数据盘后");
				ThreadPoolManager.addTask(downloadKLine15FThread);
			}
			for(int i=0;i<classList.size();i++){
				DownloadKLine15FThread downloadKLine15FThread = (DownloadKLine15FThread)SpringUtil.getBean("downloadKLine15FThread");
				downloadKLine15FThread.setType(ConstantUtil.objectTypeStockClass);
				downloadKLine15FThread.setObjectCode(classList.get(i).getClassifyCode());
				downloadKLine15FThread.setObjectCodeFull(classList.get(i).getClassifyCodeFull());
				downloadKLine15FThread.setDesc("指数"+stockList.get(i).getStockCode()+"15F线数据下载");
				downloadKLine15FThread.setPoolCode("15F线数据盘后");downloadKLine15FThread.setPoolName("15F线数据盘后");
				ThreadPoolManager.addTask(downloadKLine15FThread);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ContextInit.init();
		DownloadKLine15F downloadKLine15F = (DownloadKLine15F) SpringUtil.getBean("downloadKLine15F"); 
		downloadKLine15F.download();
	}
}
