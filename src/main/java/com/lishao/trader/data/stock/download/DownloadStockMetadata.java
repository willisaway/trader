package com.lishao.trader.data.stock.download;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.lishao.system.utils.ModuleReturn;
import com.lishao.system.utils.SpringUtil;
import com.lishao.trader.data.stock.origin.OriginDataService;
import com.lishao.trader.data.stock.origin.StockHqService;
import com.lishao.trader.data.stock.origin.StockMetadataDetailService;
import com.lishao.trader.data.stock.origin.eastmoney.callback.StockHqByClassCallBack;
import com.lishao.trader.data.stock.origin.eastmoney.callback.impl.StockHqByClassSaveClassStockMapCallBack;
import com.lishao.trader.data.stock.origin.sina.bean.StockAll;
import com.lishao.trader.data.stock.origin.sina.callback.StockAllCallBack;
import com.lishao.trader.data.stock.origin.sina.callback.impl.StockAllSaveMetadataCallBack;
import com.lishao.trader.stock.bean.entity.GpClassMetadata;
import com.lishao.trader.stock.bean.entity.GpStockClassMap;
import com.lishao.trader.stock.bean.entity.GpStockMetadata;
import com.lishao.trader.stock.service.GpClassMetadataService;
import com.lishao.trader.stock.service.GpStockMetadataService;

/**
 * 下载元数据
 * @author ibm
 *
 */
@Component
public class DownloadStockMetadata {
	@Resource
	OriginDataService originDataService;
	@Resource
	StockHqService stockHqService;
	@Resource
	GpClassMetadataService gpClassMetadataService;
	@Resource
	GpStockMetadataService gpStockMetadataService;
	@Resource
	StockMetadataDetailService stockMetadataDetailService;
	Logger logger=Logger.getLogger(DownloadStockMetadata.class);
	/**
	 * 下载所有股票主数据入口方法
	 */
	public ModuleReturn download(){
		ModuleReturn objRtn = new ModuleReturn(1);
		StockAllCallBack callback = (StockAllCallBack)SpringUtil.getBean("stockAllSaveMetadataCallBack");
		callback.setPoolCode("saveStockMetadata");
		callback.setPoolName("保存元数据");
		callback.setDesc("保存元数据");
		StockAll stockAll = originDataService.getStockHqAll(callback);
		if(stockAll!=null&&stockAll.getCount()==stockAll.getItems().size()){
			objRtn.setReturnValue(1);
		}else{
			objRtn.setReturnValue(-1);
		}
		return objRtn;
	}
	public ModuleReturn updateStockListingDate(){
		ModuleReturn objRtn = new ModuleReturn(1);
		List<GpStockMetadata> stockList = gpStockMetadataService.selectAll();
		for(GpStockMetadata stockMetadata:stockList){
			try {
				//如果上市日期是空，才更新上市日期
				if(stockMetadata.getListingDate()==null){
					Date listingDate = stockMetadataDetailService.getListingDate(stockMetadata.getStockCodeFull());
					if(listingDate!=null){
						stockMetadata.setListingDate(listingDate);
						gpStockMetadataService.updateByPrimaryKeySelective(stockMetadata);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return objRtn;
	}
	/**
	 * 下载指数、股票对应关系
	 */
	public ModuleReturn downloadStockClassMap(){
		ModuleReturn objRtn = new ModuleReturn(1);
		List<GpClassMetadata> classList = gpClassMetadataService.selectAll();
		for(GpClassMetadata classMetadata:classList){
			StockHqByClassCallBack callback = (StockHqByClassCallBack)SpringUtil.getBean("stockHqByClassSaveClassStockMapCallBack");
			callback.setClassifyCode(classMetadata.getClassifyCode());
			callback.setPoolCode("saveStockClassMap");
			callback.setPoolName("保存指数股票对应数据");
			callback.setDesc("保存指数股票对应数据");
			List<String> resultList =stockHqService.getStockHqListByClassifyCode(classMetadata.getClassifyCode(), callback);
		}
		return objRtn;
	}
}
