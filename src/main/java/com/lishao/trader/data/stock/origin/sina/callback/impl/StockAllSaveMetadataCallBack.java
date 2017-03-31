package com.lishao.trader.data.stock.origin.sina.callback.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lishao.trader.data.stock.origin.sina.bean.StockAllItem;
import com.lishao.trader.data.stock.origin.sina.callback.StockAllCallBack;

@Component
@Scope("prototype")
public class StockAllSaveMetadataCallBack extends StockAllCallBack {
//	@Resource
//	GpStockMetadataService stockMetadataService;
	@Override
	public void runCore() {
//		List<GpStockMetadata> stockList=new ArrayList<GpStockMetadata>();
//		for(int i=0;i<stockAll.getItems().size();i++){
//			List<String> fields = stockAll.getFields();
//			List colList = stockAll.getItems().get(i);
//			GpStockMetadata stockMetadata= new GpStockMetadata();
//			for(int j=0;j<fields.size();j++){
//				String field = fields.get(j);
//				if("symbol".equals(field)){
//					stockMetadata.setStockCodeFull(colList.get(j).toString());
//				}
//				if("code".equals(field)){
//					stockMetadata.setStockCode(colList.get(j).toString());
//				}
//				if("name".equals(field)){
//					stockMetadata.setStockName(colList.get(j).toString());
//				}
//				if("mktcap".equals(field)){
//					Number number = (Number)colList.get(j);
//					stockMetadata.setMarketValue(BigDecimal.valueOf(number.doubleValue()).multiply(BigDecimal.valueOf(10000)));
//				}
//				if("nmc".equals(field)){
//					Number number = (Number)colList.get(j);
//					stockMetadata.setTradableMarketValue(BigDecimal.valueOf(number.doubleValue()).multiply(BigDecimal.valueOf(10000)));
//				}
//			}
//			stockList.add(stockMetadata);
//		}
//		stockMetadataService.saveAll(stockList);
	}
}
