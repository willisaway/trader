package com.lishao.trader.market.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lishao.system.core.util.WebUtil;
import com.lishao.system.utils.ModuleReturn;
import com.lishao.trader.market.model.TradeMarket;
import com.lishao.trader.market.service.TradeMarketService;

@Controller
@RequestMapping(value = "tradeMarket")
public class TradeMarketController {
	@Resource
	TradeMarketService tradeMarketService;
	
	@RequestMapping("/query")
	public ModuleReturn query(HttpServletRequest request){
		ModuleReturn objRtn = new ModuleReturn(1);
		Map<String, Object> params = WebUtil.getParameterMap(request);
		List<TradeMarket> marketList = tradeMarketService.queryAll(params);
		System.out.println("");
		return null;
	}
}
