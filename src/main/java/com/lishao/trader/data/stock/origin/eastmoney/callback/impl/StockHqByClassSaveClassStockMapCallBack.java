package com.lishao.trader.data.stock.origin.eastmoney.callback.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lishao.system.utils.ModuleReturn;
import com.lishao.trader.data.stock.origin.eastmoney.callback.StockHqByClassCallBack;
import com.lishao.trader.stock.bean.entity.GpStockClassMap;
import com.lishao.trader.stock.service.GpStockClassMapService;

@Component
@Scope("prototype")
public class StockHqByClassSaveClassStockMapCallBack extends StockHqByClassCallBack {
	@Resource
	GpStockClassMapService gpStockClassMapService;
	@Override
	public void runCore(){
		ModuleReturn objRtn = new ModuleReturn(1);
		try {
			//将以前的数据设成逻辑删除
			gpStockClassMapService.deleteByClassifyCode(classifyCode);
			//
			//List<GpStockClassMap> stockClassList = new ArrayList<GpStockClassMap>();
			for(String stockHq:stockHqList){
				String[] stockCols = stockHq.split(",");
				GpStockClassMap addBean = new GpStockClassMap();
				addBean.setClassifyCode(classifyCode);
				addBean.setStockCode(stockCols[1]);
				GpStockClassMap updateBean = gpStockClassMapService.selectByPrimaryKey(classifyCode, stockCols[1]);
				if(updateBean!=null){
					updateBean.setDeletedFlag("0");updateBean.setLastUpdDate(new Date());
					gpStockClassMapService.updateByPrimaryKeySelective(updateBean);
				}else{
					addBean.setIncluDate(new Date());
					addBean.setCreatedDate(new Date());
					gpStockClassMapService.insertSelective(addBean);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
