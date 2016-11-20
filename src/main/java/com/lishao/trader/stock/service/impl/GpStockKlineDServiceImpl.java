package com.lishao.trader.stock.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lishao.trader.stock.bean.entity.GpStockKlineD;
import com.lishao.trader.stock.dao.GpStockKlineDMapper;
import com.lishao.trader.stock.service.GpStockKlineDService;

@Service
public class GpStockKlineDServiceImpl implements GpStockKlineDService {
	@Resource
	GpStockKlineDMapper stockKlineDMapper;

	//获取最后一根K线
	public GpStockKlineD selectLastStockKlineDByStockCode(String stockCode){
		return stockKlineDMapper.selectLastStockKlineDByStockCode(stockCode);
	}
	//获取某股某日的K线
	public GpStockKlineD selectOneKLineD(String stockCode,String periodCode){
		Map paraMap = new HashMap();
		paraMap.put("stockCode", stockCode);
		paraMap.put("periodCode", periodCode);
		return stockKlineDMapper.selectByPrimaryKey(paraMap);
	}
	//更新股票日线上的交易明细统计数据
	public int updateTradeDetailCount(Map paraMap){
		return stockKlineDMapper.updateTradeDetailCount(paraMap);
	}
	public boolean checkHasSumTradeDetail(String stockCode,String periodCode){
		boolean bResult=false;
		try{
			GpStockKlineD stockKlineD = selectOneKLineD(stockCode, periodCode);
			if(stockKlineD!=null&&BigDecimal.ZERO.compareTo(stockKlineD.getAmountFlow())==0){
				bResult=true;
			}
		}catch(Exception e){
			
		}
		return bResult;
	}
}
