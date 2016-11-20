package com.lishao.trader.data.stock.download;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lishao.system.component.threadpool.ThreadExtend;
import com.lishao.system.utils.SpringUtil;
import com.lishao.trader.base.bean.EntityBean;
import com.lishao.trader.common.ConstantUtil;
import com.lishao.trader.data.stock.origin.StockKlineService;
import com.lishao.trader.stock.service.GpClassKline30FService;
import com.lishao.trader.stock.service.GpStockKline30FService;
import com.lishao.trader.stock.service.ProcedureService;

@Component
@Scope("prototype")
public class DownloadKLine30FThread extends ThreadExtend{
	@Resource
	GpStockKline30FService stockKline30FService;
	@Resource
	GpClassKline30FService classKline30FService;
	@Resource
	ProcedureService procedureService;
	String type;
	String objectCode;//000909
	String objectCodeFull;//sz000909
	String startPeriod;
	Logger logger = Logger.getLogger(DownloadKLine30FThread.class);
	public void runCore(){
		try {
			EntityBean kLineDBean=null;
			if(ConstantUtil.objectTypeStock.equals(type)){
				kLineDBean=stockKline30FService.selectLastStockKline30FByStockCode(objectCode);
			}else if(ConstantUtil.objectTypeStockClass.equals(type)){
				kLineDBean=classKline30FService.selectLastClassKline30FByClassifyCode(objectCode);
			}
			if(kLineDBean!=null){
				startPeriod=(String)PropertyUtils.getProperty(kLineDBean, "periodCode");//从最后一个有效日期开始下载
			}else{
				startPeriod="--";//所有的都下载
			}
			StockKlineService stockKlineService = (StockKlineService)SpringUtil.getBean("stockKlineServiceBySina");
			List<Map> resMapList = stockKlineService.getKLine30F(objectCode,objectCodeFull,startPeriod);
			//保存下载结果
			if (resMapList!=null&&resMapList.size()>0) {
				save(resMapList);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.info(objectCode+"下载失败");
		}
	}
	
	public void save(List<Map> resMapList){
		if(ConstantUtil.objectTypeStock.equals(type)){
			procedureService.prcStockKline30FDownload(resMapList);
		}else if(ConstantUtil.objectTypeFund.equals(type)){
			
		}else if(ConstantUtil.objectTypeStockClass.equals(type)){
			procedureService.prcClassKline30FDownload(resMapList);
		}
	}
	//get set

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getObjectCode() {
		return objectCode;
	}

	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}

	public String getObjectCodeFull() {
		return objectCodeFull;
	}

	public void setObjectCodeFull(String objectCodeFull) {
		this.objectCodeFull = objectCodeFull;
	}

	public String getStartPeriod() {
		return startPeriod;
	}

	public void setStartPeriod(String startPeriod) {
		this.startPeriod = startPeriod;
	}
	
}
