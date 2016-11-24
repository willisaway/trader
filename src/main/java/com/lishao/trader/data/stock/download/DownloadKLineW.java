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
public class DownloadKLineW {
	@Resource
	GpStockMetadataService stockMetadataService;
	@Resource
	GpClassMetadataService stockClassMetadataService;
	Logger logger=Logger.getLogger(DownloadKLineW.class);
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
				DownloadKLineWThread downloadKLineWThread = (DownloadKLineWThread)SpringUtil.getBean("downloadKLineWThread");
				downloadKLineWThread.setType(ConstantUtil.objectTypeStock);
				downloadKLineWThread.setObjectCode(stockList.get(i).getStockCode());
				downloadKLineWThread.setObjectCodeFull(stockList.get(i).getStockCodeFull());
				downloadKLineWThread.setDesc("股票"+stockList.get(i).getStockCode()+"周线数据下载");
				downloadKLineWThread.setPoolCode("周线数据盘后");downloadKLineWThread.setPoolName("周线数据盘后");
				ThreadPoolManager.addTask(downloadKLineWThread);
			}
			for(int i=0;i<classList.size();i++){
				DownloadKLineWThread downloadKLineWThread = (DownloadKLineWThread)SpringUtil.getBean("downloadKLineWThread");
				downloadKLineWThread.setType(ConstantUtil.objectTypeStockClass);
				downloadKLineWThread.setObjectCode(classList.get(i).getClassifyCode());
				downloadKLineWThread.setObjectCodeFull(classList.get(i).getClassifyCodeFull());
				downloadKLineWThread.setDesc("指数"+stockList.get(i).getStockCode()+"周线数据下载");
				downloadKLineWThread.setPoolCode("周线数据盘后");downloadKLineWThread.setPoolName("周线数据盘后");
				ThreadPoolManager.addTask(downloadKLineWThread);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ContextInit.init();
		DownloadKLineW downloadKLineW = (DownloadKLineW) SpringUtil.getBean("downloadKLineW"); 
		downloadKLineW.download();
	}
}
