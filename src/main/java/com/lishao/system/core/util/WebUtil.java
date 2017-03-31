package com.lishao.system.core.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import com.lishao.system.core.Constants;
import org.springframework.web.util.WebUtils;

/**
 * Web层辅助类
 * 
 * @author ShenHuaJie
 * @version 2016年4月2日 下午4:19:28
 */
public final class WebUtil {
	private WebUtil() {
	}
    private static Logger logger = LogManager.getLogger();

	/**
	 * 获取指定Cookie的值
	 * 
	 * @param cookies cookie集合
	 * @param cookieName cookie名字
	 * @param defaultValue 缺省值
	 * @return
	 */
	public static final String getCookieValue(HttpServletRequest request, String cookieName, String defaultValue) {
		Cookie cookie = WebUtils.getCookie(request, cookieName);
		if (cookie == null) {
			return defaultValue;
		}
		return cookie.getValue();
	}

	/** 保存当前用户Id */
	public static final void saveCurrentUserId(Object userId) {
		setSession(Constants.CURRENT_USER_ID, userId);
	}
	/** 保存当前用户 */
	public static final void saveCurrentUser(Object user) {
		setSession(Constants.CURRENT_USER, user);
	}
	/** 保存当前用户所在公司 */
	public static final void saveCurrentUserCompany(Object company) {
		setSession(Constants.CURRENT_USER_COMPANY, company);
	}
	/** 保存当前卖家 */
	public static final void saveSellerCompany(Object sellerCompany) {
		setSession(Constants.SELLER_COMPANY, sellerCompany);
	}
	/** 保存当前买家 */
	public static final void saveBuyerCompany(Object buyerCompany) {
		setSession(Constants.BUYER_COMPANY, buyerCompany);
	}
	/** 获取当前用户 */
	public static final Long getCurrentUserId() {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			try {
                Session session = currentUser.getSession();
                if (null != session) {
                	return (Long) session.getAttribute(Constants.CURRENT_USER_ID);
                }
            } catch (InvalidSessionException e) {
                logger.error(e);
            }
		}
		return null;
	}
	/** 获取当前用户 */
	public static final Object getCurrentUser() {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			try {
                Session session = currentUser.getSession();
                if (null != session) {
                	return (Object) session.getAttribute(Constants.CURRENT_USER);
                }
            } catch (InvalidSessionException e) {
                logger.error(e);
            }
		}
		return null;
	}
	/** 获取当前用户所在公司 */
	public static final Object getCurrentUserCompany() {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			try {
                Session session = currentUser.getSession();
                if (null != session) {
                	return (Object) session.getAttribute(Constants.CURRENT_USER_COMPANY);
                }
            } catch (InvalidSessionException e) {
                logger.error(e);
            }
		}
		return null;
	}
	/** 获取当前厂家 */
	public static final Object getSellerCompany() {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			try {
                Session session = currentUser.getSession();
                if (null != session) {
                	return (Object) session.getAttribute(Constants.SELLER_COMPANY);
                }
            } catch (InvalidSessionException e) {
                logger.error(e);
            }
		}
		return null;
	}
	/** 获取当前厂家 */
	public static final Object getBuyerCompany() {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			try {
                Session session = currentUser.getSession();
                if (null != session) {
                	return (Object) session.getAttribute(Constants.BUYER_COMPANY);
                }
            } catch (InvalidSessionException e) {
                logger.error(e);
            }
		}
		return null;
	}
	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 * 
	 * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
	 */
	public static final void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}

	/** 移除当前用户 */
	public static final void removeCurrentUser(HttpServletRequest request) {
		request.getSession().removeAttribute(Constants.CURRENT_USER_ID);
	}

	/**
	 * 获得国际化信息
	 * 
	 * @param key 键
	 * @param request
	 * @return
	 */
	public static final String getApplicationResource(String key, HttpServletRequest request) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources", request.getLocale());
		return resourceBundle.getString(key);
	}

	/**
	 * 获得参数Map
	 * 
	 * @param request
	 * @return
	 */
	public static final Map<String, Object> getParameterMap(HttpServletRequest request) {
		return WebUtils.getParametersStartingWith(request, null);
	}

	/** 获取客户端IP */
	public static final String getHost(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if ("127.0.0.1".equals(ip)) {
			InetAddress inet = null;
			try { // 根据网卡取本机配置的IP
				inet = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			ip = inet.getHostAddress();
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ip != null && ip.length() > 15) {
			if (ip.indexOf(",") > 0) {
				ip = ip.substring(0, ip.indexOf(","));
			}
		}
		return ip;
	}
}
