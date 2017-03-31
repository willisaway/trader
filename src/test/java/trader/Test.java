package trader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lishao.system.init.ContextInit;
import com.lishao.system.utils.SpringUtil;
//import com.lishao.trader.market.service.TradeCalendarService;
import com.lishao.trader.data.stock.origin.OriginDataService;
import com.lishao.trader.data.stock.origin.StockHqService;

public class Test {

	public static void main(String[] args) throws Exception {
		ContextInit.init();
//		Logger logger=Logger.getLogger(Test.class);
//		logger.debug("Log4J test");
//		StockKlineDService service = (StockKlineDService)SpringUtil.getBean("stockKlineDService");
//		StockKlineD tempD = service.selectOneKLineD("000002", "2010-10-13");
//		OriginDataService originDataService = (OriginDataService)SpringUtil.getBean("originDataServiceImpl");
//		originDataService.getStockClassMap("000001");
		StockHqService stockHqService = (StockHqService)SpringUtil.getBean("stockHqServiceImpl");
//		stockHqService.getStockClassMap("399998");
		System.out.println(1);
		//测试更新交易日历
//		TradeCalendarService tradeCalendarService=(TradeCalendarService)SpringUtil.getBean("tradeCalendarService");
//		Map paraMap=new HashMap();
//		paraMap.put("marketId", "100");
//		paraMap.put("classifyCode", "000001");
//		tradeCalendarService.insertCalendar(paraMap);
	}

}
