package com.lishao.trader.stock.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.lishao.trader.stock.dao.ProcedureMapper;
import com.lishao.trader.stock.service.ProcedureService;

@Service
public class ProcedureServiceImpl implements ProcedureService {
	Logger log = Logger.getLogger(ProcedureServiceImpl.class);
	@Resource
	ProcedureMapper procedureMapper;
	
	public void prcStockKlineDDownload(Map paraMap){
		try {
			procedureMapper.prcStockKlineDDownload(paraMap);
		} catch (Exception e) {
			log.error("日线数据保存异常:"+paraMap.toString());
			e.printStackTrace();
		}
	}
	
	public void prcStockKlineDDownload(List<Map> paraMapList){
		for(int i=0;i<paraMapList.size();i++){
			Map paraMap = paraMapList.get(i);
			prcStockKlineDDownload(paraMap);
		}
	}
	
	public void prcStockKlineWDownload(Map paraMap){
		try {
			procedureMapper.prcStockKlineWDownload(paraMap);
		} catch (Exception e) {
			log.error("周线数据保存异常:"+paraMap.toString());
			e.printStackTrace();
		}
	}
	
	public void prcStockKlineWDownload(List<Map> paraMapList){
		for(int i=0;i<paraMapList.size();i++){
			Map paraMap = paraMapList.get(i);
			prcStockKlineWDownload(paraMap);
		}
	}
	
	public void prcStockKlineMDownload(Map paraMap){
		try {
			procedureMapper.prcStockKlineMDownload(paraMap);
		} catch (Exception e) {
			log.error("月线数据保存异常:"+paraMap.toString());
			e.printStackTrace();
		}
	}
	
	public void prcStockKlineMDownload(List<Map> paraMapList){
		for(int i=0;i<paraMapList.size();i++){
			Map paraMap = paraMapList.get(i);
			prcStockKlineMDownload(paraMap);
		}
	}
	
	public void prcStockKline15FDownload(Map paraMap){
		try {
			procedureMapper.prcStockKline15FDownload(paraMap);
		} catch (Exception e) {
			log.error("15分钟线数据保存异常:"+paraMap.toString());
			e.printStackTrace();
		}
	}
	
	public void prcStockKline15FDownload(List<Map> paraMapList){
		for(int i=0;i<paraMapList.size();i++){
			Map paraMap = paraMapList.get(i);
			prcStockKline15FDownload(paraMap);
		}
	}
	public void prcStockKline30FDownload(Map paraMap){
		try {
			procedureMapper.prcStockKline30FDownload(paraMap);
		} catch (Exception e) {
			log.error("30分钟线数据保存异常:"+paraMap.toString());
			e.printStackTrace();
		}
	}
	
	public void prcStockKline30FDownload(List<Map> paraMapList){
		for(int i=0;i<paraMapList.size();i++){
			Map paraMap = paraMapList.get(i);
			prcStockKline30FDownload(paraMap);
		}
	}
	
	public void prcStockKline60FDownload(Map paraMap){
		try {
			procedureMapper.prcStockKline60FDownload(paraMap);
		} catch (Exception e) {
			log.error("60分钟线数据保存异常:"+paraMap.toString());
			e.printStackTrace();
		}
	}
	
	public void prcStockKline60FDownload(List<Map> paraMapList){
		for(int i=0;i<paraMapList.size();i++){
			Map paraMap = paraMapList.get(i);
			prcStockKline60FDownload(paraMap);
		}
	}
	public void prcClassKlineDDownload(Map paraMap){
		procedureMapper.prcClassKlineDDownload(paraMap);
	}
	
	public void prcClassKlineDDownload(List<Map> paraMapList){
		for(int i=0;i<paraMapList.size();i++){
			Map paraMap = paraMapList.get(i);
			prcClassKlineDDownload(paraMap);
		}
	}
	public void prcClassKlineWDownload(Map paraMap){
		procedureMapper.prcClassKlineWDownload(paraMap);
	}
	
	public void prcClassKlineWDownload(List<Map> paraMapList){
		for(int i=0;i<paraMapList.size();i++){
			Map paraMap = paraMapList.get(i);
			prcClassKlineWDownload(paraMap);
		}
	}
	public void prcClassKlineMDownload(Map paraMap){
		procedureMapper.prcClassKlineMDownload(paraMap);
	}
	
	public void prcClassKlineMDownload(List<Map> paraMapList){
		for(int i=0;i<paraMapList.size();i++){
			Map paraMap = paraMapList.get(i);
			prcClassKlineMDownload(paraMap);
		}
	}
	public void prcClassKline15FDownload(Map paraMap){
		procedureMapper.prcClassKline15FDownload(paraMap);
	}
	
	public void prcClassKline15FDownload(List<Map> paraMapList){
		for(int i=0;i<paraMapList.size();i++){
			Map paraMap = paraMapList.get(i);
			prcClassKline15FDownload(paraMap);
		}
	}
	public void prcClassKline30FDownload(Map paraMap){
		procedureMapper.prcClassKline30FDownload(paraMap);
	}
	
	public void prcClassKline30FDownload(List<Map> paraMapList){
		for(int i=0;i<paraMapList.size();i++){
			Map paraMap = paraMapList.get(i);
			prcClassKline30FDownload(paraMap);
		}
	}
	public void prcClassKline60FDownload(Map paraMap){
		procedureMapper.prcClassKline60FDownload(paraMap);
	}
	
	public void prcClassKline60FDownload(List<Map> paraMapList){
		for(int i=0;i<paraMapList.size();i++){
			Map paraMap = paraMapList.get(i);
			prcClassKline60FDownload(paraMap);
		}
	}
}
