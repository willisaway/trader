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
public class DownloadKLineM {
	@Resource
	GpStockMetadataService stockMetadataService;
	@Resource
	GpClassMetadataService stockClassMetadataService;
	Logger logger=Logger.getLogger(DownloadKLineM.class);
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
				DownloadKLineMThread downloadKLineMThread = (DownloadKLineMThread)SpringUtil.getBean("downloadKLineMThread");
				downloadKLineMThread.setType(ConstantUtil.objectTypeStock);
				downloadKLineMThread.setObjectCode(stockList.get(i).getStockCode());
				downloadKLineMThread.setObjectCodeFull(stockList.get(i).getStockCodeFull());
				downloadKLineMThread.setDesc("股票"+stockList.get(i).getStockCode()+"月线数据下载");
				downloadKLineMThread.setPoolCode("月线数据盘后");downloadKLineMThread.setPoolName("月线数据盘后");
				ThreadPoolManager.addTask(downloadKLineMThread);
			}
			for(int i=0;i<classList.size();i++){
				DownloadKLineMThread downloadKLineMThread = (DownloadKLineMThread)SpringUtil.getBean("downloadKLineMThread");
				downloadKLineMThread.setType(ConstantUtil.objectTypeStockClass);
				downloadKLineMThread.setObjectCode(classList.get(i).getClassifyCode());
				downloadKLineMThread.setObjectCodeFull(classList.get(i).getClassifyCodeFull());
				downloadKLineMThread.setDesc("指数"+stockList.get(i).getStockCode()+"周线数据下载");
				downloadKLineMThread.setPoolCode("月线数据盘后");downloadKLineMThread.setPoolName("月线数据盘后");
				ThreadPoolManager.addTask(downloadKLineMThread);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ContextInit.init();
		DownloadKLineM downloadKLineM = (DownloadKLineM) SpringUtil.getBean("downloadKLineM"); 
		downloadKLineM.download();
	}
}
