package com.lishao.trader.data.stock.download;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.lishao.system.component.threadpool.ThreadPoolManager;
import com.lishao.system.utils.SpringUtil;
import com.lishao.trader.common.ConstantUtil;
import com.lishao.trader.stock.service.GpStockKlineDService;

/**
 * 下载交易数据的线程池管理
 * 下载股票盘后的交易数据，统计流入流出
 * @author lishao
 *新浪的链接为http://market.finance.sina.com.cn/downxls.php?date=2016-01-25&symbol=sz002661
 *返回格式
 */
@Component
public class DownloadTradeDetail {
	Logger logger = Logger.getLogger(DownloadTradeDetail.class);
	@Resource
	GpStockKlineDService stockKlineDService;
	/**
	 * 下载股票交易数据，通知模式
	 * @param objectCode
	 */
	public void notify(String type,String objectCode,String objectCodeFull,String periodCode){
		if(!ConstantUtil.objectTypeStock.equals(type)){//目前只有股票的数据
			return;
		}
		//判断是否已经统计过
		if(ConstantUtil.objectTypeStock.equals(type)&&stockKlineDService.checkHasSumTradeDetail(objectCode, periodCode)){
			return;
		}
		DownloadTradeDetailThread downloadTradeDetailThread = (DownloadTradeDetailThread)SpringUtil.getBean("downloadTradeDetailThread");
		downloadTradeDetailThread.setType(type);
		downloadTradeDetailThread.setObjectCode(objectCode);
		downloadTradeDetailThread.setObjectCodeFull(objectCodeFull);
		downloadTradeDetailThread.setPeriodCode(periodCode);
		downloadTradeDetailThread.setDesc("代码"+periodCode+",日期"+periodCode+",下载交易明细数据");
		downloadTradeDetailThread.setPoolCode("交易明细下载");downloadTradeDetailThread.setPoolName("交易明细下载");
		ThreadPoolManager.addTask(downloadTradeDetailThread);
	}
}
