package com.lishao.system.core;

/**
 * 常量表
 * 
 * @author ShenHuaJie
 * @version $Id: Constants.java, v 0.1 2014-2-28 上午11:18:28 ShenHuaJie Exp $
 */
public interface Constants {
    /**
     * 异常信息统一头信息<br>
     * 非常遗憾的通知您,程序发生了异常
     */
    public static final String Exception_Head = "OH,MY GOD! SOME ERRORS OCCURED! AS FOLLOWS :";
    /** 客户端语言 */
    public static final String USERLANGUAGE = "userLanguage";
    /** 客户端主题 */
    public static final String WEBTHEME = "webTheme";
    /** 当前用户 ID*/
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";
    /** 当前用户 */
    public static final String CURRENT_USER = "CURRENT_USER";
    /** 当前卖家 */
    public static final String SELLER_COMPANY = "SELLER_COMPANY";
    /** 当前买家,买家登录时就是当前人所属公司 */
    public static final String BUYER_COMPANY = "BUYER_COMPANY";
    /** 当前用户所在公司 */
    public static final String CURRENT_USER_COMPANY = "CURRENT_USER_COMPANY";
    /** 在线用户数量 */
    public static final String ALLUSER_NUMBER = "ALLUSER_NUMBER";
    /** 登录用户数量 */
    public static final String USER_NUMBER = "USER_NUMBER";
    /** 上次请求地址 */
    public static final String PREREQUEST = "PREREQUEST";
    /** 上次请求时间 */
    public static final String PREREQUEST_TIME = "PREREQUEST_TIME";
    /** 非法请求次数 */
    public static final String MALICIOUS_REQUEST_TIMES = "MALICIOUS_REQUEST_TIMES";
    /** 缓存命名空间 */
    public static final String CACHE_NAMESPACE = "iBase4J:";

    /** 日志表状态 */
    public interface JOBSTATE {
        /**
         * 日志表状态，初始状态，插入
         */
        public static final String INIT_STATS = "I";
        /**
         * 日志表状态，成功
         */
        public static final String SUCCESS_STATS = "S";
        /**
         * 日志表状态，失败
         */
        public static final String ERROR_STATS = "E";
        /**
         * 日志表状态，未执行
         */
        public static final String UN_STATS = "N";
    }
}
