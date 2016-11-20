package com.lishao.system.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

public class HttpUtil {
	static Logger logger = Logger.getLogger(HttpUtil.class);
	
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
			httpCon.setReadTimeout(600000);// 等待5s
			if(httpCon.getResponseCode() == HttpURLConnection.HTTP_OK){
				input = httpCon.getInputStream();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return input;
	}
}
