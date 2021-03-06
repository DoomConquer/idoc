package com.idoc.constant;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.idoc.model.LoginUserInfo;

public class ThreadLocalConstant {
	
	
	public static final ThreadLocal<List<String>> ERROR_MESSAGE_TL = new ThreadLocal<List<String>>(); // 保存错误信息的threadlocal
	public static final ThreadLocal<HttpServletRequest> requestTL = new ThreadLocal<HttpServletRequest>(); // 保存request的threadlocal
	public static final ThreadLocal<HttpServletResponse> responseTL = new ThreadLocal<HttpServletResponse>(); // 保存response的threadlocal
	public static final ThreadLocal<LoginUserInfo> sessionTL = new ThreadLocal<LoginUserInfo>(); // 保存session的threadlocal
	
	public static final String ERROR_MESSAGE_REQUEST_KEY = "errorMessages";// 错误提示信息在request-attribute中的key

}