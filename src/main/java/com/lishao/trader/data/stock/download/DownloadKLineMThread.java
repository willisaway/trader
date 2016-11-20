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
import com.lishao.trader.stock.service.GpClassKlineMService;
import com.lishao.trader.stock.service.GpStockKlineMService;
import com.lishao.trader.stock.service.ProcedureService;

@Component
@Scope("prototype")
public class DownloadKLineMThread extends ThreadExtend{
	@Resource
	GpStockKlineMService stockKlineMService;
	@Resource
	GpClassKlineMService classKlineMService;
	@Resource
	ProcedureService procedureService;
	String type;
	String objectCode;//000909
	String objectCodeFull;//sz000909
	String startPeriod;
	Logger logger = Logger.getLogger(DownloadKLineMThread.class);
	public void runCore(){
		try {
			EntityBean kLineDBean=null;
			if(ConstantUtil.objectTypeStock.equals(type)){
				kLineDBean=stockKlineMService.selectLastStockKlineMByStockCode(objectCode);
			}else if(ConstantUtil.objectTypeStockClass.equals(type)){
				kLineDBean=classKlineMService.selectLastClassKlineMByClassifyCode(objectCode);
			}
			if(kLineDBean!=null){
				startPeriod=(String)PropertyUtils.getProperty(kLineDBean, "periodCode");//从最后一个有效日期开始下载
			}else{
				startPeriod="--";//所有的都下载
			}
			StockKlineService stockKlineService = (StockKlineService)SpringUtil.getBean("stockKlineServiceBySina");
			List<Map> resMapList = stockKlineService.getKLineM(objectCode,objectCodeFull,startPeriod);
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
			procedureService.prcStockKlineMDownload(resMapList);
		}else if(ConstantUtil.objectTypeFund.equals(type)){
			
		}else if(ConstantUtil.objectTypeStockClass.equals(type)){
			procedureService.prcClassKlineMDownload(resMapList);
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
