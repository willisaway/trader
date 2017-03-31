package com.lishao.trader.data.stock.download;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lishao.system.utils.ModuleReturn;
import com.lishao.trader.data.stock.origin.OriginDataService;
import com.lishao.trader.data.stock.origin.sina.OriginDataServiceImpl;
import com.lishao.system.component.threadpool.ThreadExtend;

@Component
@Scope("prototype")
public class DownloadTradeDetailThread extends ThreadExtend{
//	GpStockKlineDService stockKlineDService;
	String type;
	String objectCode;
	String objectCodeFull;
	String periodCode;
	Logger logger=Logger.getLogger(DownloadTradeDetailThread.class);
	public void runCore(){
		OriginDataService dataLoader = new OriginDataServiceImpl();
		ModuleReturn objRtn = dataLoader.getTradeDetail(type, objectCodeFull, periodCode);
		//保存结果
		if(objRtn.getReturnValue()>0){
			Map saveMap = objRtn.getReturnPara();
			saveMap.put("periodCode", periodCode);
			saveMap.put("objectCode", objectCode);
//			int iResult = stockKlineDService.updateTradeDetailCount(saveMap);
//			if(iResult>0){
//				objRtn.setReturnValue(1);
//			}else{
//				objRtn.setReturnValue(-1,"保存结果数据失败");
//				logger.error("保存结果数据失败:"+saveMap.toString());
//			}
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
	public String getPeriodCode() {
		return periodCode;
	}
	public void setPeriodCode(String periodCode) {
		this.periodCode = periodCode;
	}
	public String getObjectCodeFull() {
		return objectCodeFull;
	}
	public void setObjectCodeFull(String objectCodeFull) {
		this.objectCodeFull = objectCodeFull;
	}
}
