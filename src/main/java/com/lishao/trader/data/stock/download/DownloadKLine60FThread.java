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
import com.lishao.trader.stock.service.GpClassKline60FService;
import com.lishao.trader.stock.service.GpStockKline60FService;
import com.lishao.trader.stock.service.ProcedureService;

@Component
@Scope("prototype")
public class DownloadKLine60FThread extends ThreadExtend{
	@Resource
	GpStockKline60FService stockKline60FService;
	@Resource
	GpClassKline60FService classKline60FService;
	@Resource
	ProcedureService procedureService;
	String type;
	String objectCode;//000909
	String objectCodeFull;//sz000909
	String startPeriod;
	Logger logger = Logger.getLogger(DownloadKLine60FThread.class);
	public void runCore(){
		try {
			EntityBean kLineDBean=null;
			if(ConstantUtil.objectTypeStock.equals(type)){
				kLineDBean=stockKline60FService.selectLastStockKline60FByStockCode(objectCode);
			}else if(ConstantUtil.objectTypeStockClass.equals(type)){
				kLineDBean=classKline60FService.selectLastClassKline60FByClassifyCode(objectCode);
			}
			if(kLineDBean!=null){
				startPeriod=(String)PropertyUtils.getProperty(kLineDBean, "periodCode");//从最后一个有效日期开始下载
			}else{
				startPeriod="--";//所有的都下载
			}
			StockKlineService stockKlineService = (StockKlineService)SpringUtil.getBean("stockKlineServiceBySina");
			List<Map> resMapList = stockKlineService.getKLine60F(objectCode,objectCodeFull,startPeriod);
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
			procedureService.prcStockKline60FDownload(resMapList);
		}else if(ConstantUtil.objectTypeFund.equals(type)){
			
		}else if(ConstantUtil.objectTypeStockClass.equals(type)){
			procedureService.prcClassKline60FDownload(resMapList);
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
