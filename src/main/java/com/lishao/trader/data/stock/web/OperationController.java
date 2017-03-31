package com.lishao.trader.data.stock.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lishao.system.utils.ModuleReturn;
import com.lishao.trader.data.stock.download.DownloadKLineD;
import com.lishao.trader.data.stock.download.DownloadStockMetadata;

@Controller
@RequestMapping("/data/stock/operator") 
public class OperationController {
	@Resource
	DownloadKLineD downloadKLineD;
	@Resource
	DownloadStockMetadata downloadStockMetadata;
	
	@RequestMapping(value = "/page")
    public ModelAndView page() {
        ModelAndView mv = new ModelAndView("trader/data/stock/StockDataOperator");
        return mv;
    }
	/**
	 * 更新K线数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/downloadKLineD")
	public ModuleReturn downloadKLineD(HttpServletRequest request){
		downloadKLineD.download();
		return null;
	}
//	@RequestMapping("/downloadKLineW")
//	public ModuleReturn downloadKLineW(HttpServletRequest request){
//		downloadKLineW.download();
//		return null;
//	}
//	@RequestMapping("/downloadKLineM")
//	public ModuleReturn downloadKLineM(HttpServletRequest request){
//		downloadKLineM.download();
//		return null;
//	}
//	@RequestMapping("/downloadKLine15F")
//	public ModuleReturn downloadKLine15F(HttpServletRequest request){
//		downloadKLine15F.download();
//		return null;
//	}
//	@RequestMapping("/downloadKLine30F")
//	public ModuleReturn downloadKLine30F(HttpServletRequest request){
//		downloadKLine30F.download();
//		return null;
//	}
//	@RequestMapping("/downloadKLine60F")
//	public ModuleReturn downloadKLine60F(HttpServletRequest request){
//		downloadKLine60F.download();
//		return null;
//	}
	/**
	 * 更新股票元数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/downloadStockMetadata")
	public ModuleReturn downloadStockMetadata(HttpServletRequest request){
		ModuleReturn objRtn = downloadStockMetadata.download();
		return objRtn;
	}
	
	/**
	 * 更新指数股票关系
	 * @param request
	 * @return
	 */
	@RequestMapping("/downloadStockClassMap")
	public ModuleReturn downloadStockClassMap(HttpServletRequest request){
		ModuleReturn objRtn = downloadStockMetadata.downloadStockClassMap();
		return objRtn;
	}
	
	/**
	 * 更新股票的上市日期
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateStockListingDate")
	public ModuleReturn updateStockListingDate(HttpServletRequest request){
		ModuleReturn objRtn = downloadStockMetadata.updateStockListingDate();
		return objRtn;
	}
}
