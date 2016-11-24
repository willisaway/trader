package com.lishao.system.utils;

import static com.lishao.system.utils.ConfigUtil.getProperty;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

public class HttpUtil {
	static Logger logger = Logger.getLogger(HttpUtil.class);
	/** 重试次数 默认10 */
	public static int RETRY_COUNT = Integer.parseInt(getProperty("retryCount"));
	/** 重试等待时间 默认3s */
	public static int RETRY_DELAY = Integer.parseInt(getProperty("retryDelay"));
	
	public static String getInputHtmlWithRetry(String url){
		return getInputHtmlWithRetry(url,"UTF-8");
	}
	public static String getInputHtmlWithRetry(String url,String encode){
		String htmlString = null;
		for (int i = 0; i < RETRY_COUNT; i++) {/** 重试次数 默认10 */
			try {
				htmlString = HttpUtil.getInputHtml(url,encode);
				if (i > 0) {
					logger.warn("第" +  (i+1) + "次读取url:" + url);
					Thread.sleep(RETRY_DELAY);// 控制频率
				}
				if (htmlString !=null && htmlString.length() > 0) {
					break;
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return htmlString;
	}
	
	public static String getInputHtml(String url) throws Exception {
		return getInputHtml(url,"UTF-8");
	}
	/**
	 * 读取html内容
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String getInputHtml(String url,String encode) throws Exception {
		//===================================================
		// 拷nodeList = myParser.parse(lastFilter);	
		// 
		// Exception in thread "main" org.htmlparser.util.ParserException: 
		// problem reading a character at position 18965;java.io.EOFException
		//===================================================
		InputStream input = null;
		BufferedReader in = null;
		String newLine = null;
		StringBuffer html = new StringBuffer();
		try {
			input = getInputStream(url);
			if(input!=null){
				in = new BufferedReader(new InputStreamReader(input, encode));
				while ((newLine = in.readLine()) != null) {
					html.append(newLine);
				}			
			}
		} catch (Exception ex) {
			html = new StringBuffer();//清空
			ex.printStackTrace();
			logger.info("读取html超时：message=" + ex.getLocalizedMessage() + ", url=" + url);
		} finally {
			if(in != null)
				in.close();
			if(input !=null)
				input.close();				
		}	 

		return html.toString();
	}
	public static InputStream getInputStream(String url) throws Exception{
		URL MyURL = null;
		HttpURLConnection httpCon = null;
		InputStream input = null;
		try {
			MyURL = new URL(url);
			httpCon = (HttpURLConnection)MyURL.openConnection();
			httpCon.setReadTimeout(10000);// 等待10s
			if(httpCon.getResponseCode() == HttpURLConnection.HTTP_OK){
				input = httpCon.getInputStream();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return input;
	}
}
