package com.lishao.trader.common.dao;

import java.util.Map;

import com.lishao.trader.common.bean.entity.TradeCalendar;

public interface TradeCalendarMapper {
    int deleteByPrimaryKey(String rowId);

    int insert(TradeCalendar record);

    int insertSelective(TradeCalendar record);

    TradeCalendar selectByPrimaryKey(String rowId);

    int updateByPrimaryKeySelective(TradeCalendar record);

    int updateByPrimaryKey(TradeCalendar record);
    
    int insertCalendar(Map paraMap);
}