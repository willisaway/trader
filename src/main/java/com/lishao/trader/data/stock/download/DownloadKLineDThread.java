package com.lishao.trader.data.stock.download;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lishao.system.utils.ComparatorStockKlineDAsc;
import com.lishao.system.utils.DateUtil;
import com.lishao.system.utils.NumberUtil;
import com.lishao.system.utils.SpringUtil;
import com.lishao.trader.common.ConstantUtil;
//import com.lishao.trader.market.service.TradeCalendarService;
import com.lishao.trader.data.stock.origin.OriginDataService;
import com.lishao.trader.data.stock.origin.sina.OriginDataServiceImpl;
import com.lishao.trader.stock.service.ProcedureService;
import com.lishao.system.component.threadpool.ThreadExtend;

@Component
@Scope("prototype")
public class DownloadKLineDThread extends ThreadExtend{
	@Resource
	ProcedureService procedureService;
	String type;
	String objectCode;//000909
	String objectCodeFull;//sz000909
	Date startDate;
	Logger logger=Logger.getLogger(DownloadKLineDThread.class);
	
	public void runCore(){
		try {
//			EntityBean kLineDBean=null;
//			if(ConstantUtil.objectTypeStock.equals(type)){
//				kLineDBean=stockKlineDService.selectLastStockKlineDByStockCode(objectCode);
//			}else if(ConstantUtil.objectTypeStockClass.equals(type)){
//				kLineDBean=classKlineDService.selectLastClassKlineDByClassifyCode(objectCode);
//			}
			//计算下载日线的起始日期
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			Calendar startCalendar = Calendar.getInstance();
//			if(kLineDBean!=null){
//				startDate = sdf.parse((String)PropertyUtils.getProperty(kLineDBean, "periodCode"));
//				startCalendar.setTime(startDate);
//				startCalendar.add(Calendar.DATE, 1);
//			}else if(startDate!=null){
//				startCalendar.setTime(startDate);
//			}else{
//				startCalendar.setTime(new java.util.Date());
//				startCalendar.add(Calendar.YEAR, -5);//默认下载5年的
//			}
			//开始去新浪下载
			OriginDataService dataLoader = new OriginDataServiceImpl();
			List<Map> resMapList = dataLoader.getKlineD(type,objectCode, startCalendar);
			//对结果进行排序
			resMapList=sort(resMapList);
			//保存下载结果
			if (resMapList!=null&&resMapList.size()>0) {
				save(resMapList);
			}
			//downloadTradeDetail(resMapList);
			afterDownload();
		} catch (Exception e) {
			logger.info(objectCode+"下载失败");
			e.printStackTrace();
		}
	}
	
	public void downloadTradeDetail(List<Map> resMapList){
		//线程池方式统计交易明细数据
		DownloadTradeDetail downloadTradeDetail = (DownloadTradeDetail)SpringUtil.getBean("downloadTradeDetail");
		for(Map map:resMapList){
			downloadTradeDetail.notify(type,objectCode,objectCodeFull,map.get("periodCode").toString());
		}
		//直接触发执行明细交易数据
//		for(Map map:resMapList){
//			DownloadTradeDetailThread downloadTradeDetailThread = (DownloadTradeDetailThread)SpringUtil.getBean("downloadTradeDetailThread");
//			downloadTradeDetailThread.setType(type);
//			downloadTradeDetailThread.setObjectCode(objectCode);
//			downloadTradeDetailThread.setObjectCodeFull(objectCodeFull);
//			downloadTradeDetailThread.setPeriodCode(map.get("periodCode").toString());
//			downloadTradeDetailThread.run();
//		}
	}
	public void afterDownload(){
		//更新交易日历
		if(ConstantUtil.objectTypeStockClass.equals(type)&&"000001".equals(objectCode)){
//			TradeCalendarService tradeCalendarService=(TradeCalendarService)SpringUtil.getBean("tradeCalendarService");
//			Map paraMap=new HashMap();
//			paraMap.put("marketId", "100");
//			paraMap.put("classifyCode", "000001");
//			tradeCalendarService.insertCalendar(paraMap);
		}
	}
	//排序
	public List<Map> sort(List<Map> resMapList){
		//产生排序列
		for(Map tmpMap:resMapList){
			tmpMap.put("sortColumn", DateUtil.formatPeriodCodeD(tmpMap.get("periodCode").toString()));
		}
		Collections.sort(resMapList, new ComparatorStockKlineDAsc());
		return resMapList;
	}
	//保存
	public void save(List<Map> resMapList){
		if(ConstantUtil.objectTypeStock.equals(type)){
			procedureService.prcStockKlineDDownload(resMapList);
		}else if(ConstantUtil.objectTypeFund.equals(type)){
			
		}else if(ConstantUtil.objectTypeStockClass.equals(type)){
			procedureService.prcClassKlineDDownload(resMapList);
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
}
