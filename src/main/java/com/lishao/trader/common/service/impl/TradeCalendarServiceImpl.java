package com.lishao.trader.common.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lishao.trader.common.dao.TradeCalendarMapper;
import com.lishao.trader.common.service.TradeCalendarService;

@Service
public class TradeCalendarServiceImpl implements TradeCalendarService{
	@Resource
	TradeCalendarMapper tradeCalendarMapper;
	
	/**
	 * Map{marketId-市场ID,classifyCode-用哪个指数的日线来产生日历}
	 */
	public int insertCalendar(Map paraMap){
		return tradeCalendarMapper.insertCalendar(paraMap);
	}
	
}
