package com.duc.memorandum.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import com.duc.memorandum.constant.ExceptionValue;
import com.duc.memorandum.constant.ReturnValue;
import com.duc.memorandum.entity.LoginSessionVO;
import com.duc.memorandum.entity.UserVO;
import com.duc.memorandum.exception.memorandumException;

public class HttpRequestUtil {

	public static String getHttpServiceData(String requestURL, Map<String, Object> requestMap, String method) {

		if (StringUtils.isBlank(requestURL)) {
			return null;
		}

		if (StringUtils.isNotBlank(method) && StringUtils.isNotBlank(method.trim()) && method.trim().toUpperCase().equals("GET")) {
			method = "GET";
		} else {
			method = "POST";
		}

		String result = "";
		URL url = null;
		HttpURLConnection httpurlconnection = null;
		InputStream in = null;
		BufferedReader reader = null;

		try {

			String requestParams = "";
			if (MapUtils.isNotEmpty(requestMap)) {
				for (Map.Entry<String, Object> entry : requestMap.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();

					String param = key + "=" + value.toString();
					requestParams += requestParams == "" ? param : ("&" + param);
				}
			}

			if (method.equals("GET")) {
				url = new URL(requestURL + "?" + requestParams);
				httpurlconnection = (HttpURLConnection) url.openConnection();
				httpurlconnection.setRequestMethod(method);
			} else if (method.equals("POST")) {
				url = new URL(requestURL);
				httpurlconnection = (HttpURLConnection) url.openConnection();
				httpurlconnection.setDoOutput(true);
				httpurlconnection.setRequestMethod(method);

				httpurlconnection.getOutputStream().write(requestParams.getBytes());
				httpurlconnection.getOutputStream().flush();
				httpurlconnection.getOutputStream().close();
			}

			if (httpurlconnection != null) {
				// // 获取响应代码
				// int code = httpurlconnection.getResponseCode();
				// 获取页面内容
				in = httpurlconnection.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in, "utf8"));
				String str = reader.readLine();
				while (str != null) {
					result += str;
					str = reader.readLine();
				}
			}

		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
			if (httpurlconnection != null) {
				httpurlconnection.disconnect();
			}
		}

		return result;

	}
    
	
	
	
	/**
	 * 设置登录 前的URL
	 * 
	 * @param request
	 * @param loginSessionVO
	 */
	public static void setLoginURLSession(HttpServletRequest request, String loginURL) {
		if (request != null) {
			HttpSession session = request.getSession();
			if (StringUtils.isNotBlank(loginURL)) {
				session.setAttribute("loginURL", loginURL);
			} else {
				session.removeAttribute("loginURL");
			}
		}
	}
	
	

	/**
	 * 设置当前登录类型
	 * 
	 * @param request
	 * @param loginSessionVO
	 */
	public static void setLogintypeSession(HttpServletRequest request, String type) {
		if (request != null) {
			HttpSession session = request.getSession();
			if (StringUtils.isNotBlank(type)) {
				session.setAttribute("type", type);
			} else {
				session.removeAttribute("type");
			}
		}
	}
	
	
	
	
	/**
	 * 获取登录 类型
	 * 
	 * @param request
	 * @return
	 */
	public static String getLogintypeSession(HttpServletRequest request) {
		String type = null;

		if (request != null) {
			HttpSession session = request.getSession();
			type = (String) session.getAttribute("type");
		}

		return type;
	}
	
	
	

	/**
	 * 获取登录 Session
	 * 
	 * @param request
	 * @return
	 */
	public static String getLoginURLSession(HttpServletRequest request) {
		String loginURL = null;

		if (request != null) {
			HttpSession session = request.getSession();
			loginURL = (String) session.getAttribute("loginURL");
		}

		return loginURL;
	}
	
	
	
	
	
	
	/**
	 * 设置登录 前的邀请用户ID
	 * 
	 * @param request
	 * @param loginSessionVO
	 */
	public static void setLoginInviteUserIDSession(HttpServletRequest request, Long inviteUserID) {
		if (request != null) {
			HttpSession session = request.getSession();
			if (inviteUserID!=null&&inviteUserID.longValue()>0) {
				session.setAttribute("inviteUserID", inviteUserID);
			} else {
				session.removeAttribute("inviteUserID");
			}
		}
	}

	/**
	 * 获取登录 Session
	 * 
	 * @param request
	 * @return
	 */
	public static Long getLoginInviteUserIDSession(HttpServletRequest request) {
		Long inviteUserID = null;

		if (request != null) {
			HttpSession session = request.getSession();
			inviteUserID = (Long) session.getAttribute("inviteUserID");
		}

		return inviteUserID;
	}
	
	
	
	
	
	
	
	
	/**
	 * 设置登录 Session
	 * 
	 * @param request
	 * @param loginSessionVO
	 */
	public static void setLoginSession(HttpServletRequest request, LoginSessionVO loginSessionVO) {
		if (request != null) {
			HttpSession session = request.getSession();
			if (loginSessionVO != null) {
				session.setAttribute("loginSessionVO", loginSessionVO);
				
			} else {
				session.removeAttribute("loginSessionVO");
			}
		}
	}

	/**
	 * 获取登录 Session
	 * 
	 * @param request
	 * @return
	 */
	public static LoginSessionVO getLoginSession(HttpServletRequest request) {
		LoginSessionVO loginSessionVO = null;

		if (request != null) {
			HttpSession session = request.getSession();
			loginSessionVO = (LoginSessionVO) session.getAttribute("loginSessionVO");
		}

		return loginSessionVO;
	}

	/**
	 * 获取登录 Session的用户ID
	 * 
	 * @param request
	 * @return
	 */
	public static Long getLoginSessionUserID(HttpServletRequest request) {
		Long userID = null;
		LoginSessionVO loginSessionVO = HttpRequestUtil.getLoginSession(request);
		if (loginSessionVO != null && loginSessionVO.getUserID() != null && loginSessionVO.getUserID().longValue() > 0) {
			userID = loginSessionVO.getUserID();
		}
		return userID;
	}

	/**
	 * 根据要求检查登录用户是否正确
	 * 
	 * @param request
	 * @param loginUserTypeList
	 *            登录用户要求类型的集合
	 * @return
	 * @throws memorandumException
	 */
	public static boolean checkLoginSession(HttpServletRequest request, List<String> loginUserTypeList) throws memorandumException {
		boolean flag = false;
		LoginSessionVO loginSessionVO = HttpRequestUtil.getLoginSession(request);
		if (loginSessionVO == null) {
			throw new memorandumException(ExceptionValue.NO_LOGIN_USER_ERROR_EXCEPTION, ExceptionValue.NO_LOGIN_USER_ERROR_EXCEPTION_MSG, ReturnValue.CODE_FAILED, ReturnValue.ERROR_CODE_NO_LOGIN);
		}
		
		flag = true;
		String type = loginSessionVO.getType();
		if (CollectionUtils.isNotEmpty(loginUserTypeList)) {
			if (!loginUserTypeList.contains(type)){
				String userTypeString = "";
				for (String userType : loginUserTypeList) {
					if (userType.equals(UserVO.TYPE_ADMIN)) {
						userTypeString += StringUtils.isBlank(userTypeString) ? "管理员"
								: ("，或者" + "管理员");
					} else if (userType.equals(UserVO.TYPE_DEALER)) {
						userTypeString += StringUtils.isBlank(userTypeString) ? "经销商"
								: ("，或者" + "经销商");
					} else if (userType.equals(UserVO.TYPE_ORDINARY)) {
						userTypeString += StringUtils.isBlank(userTypeString) ? "普通用户"
								: ("，或者" + "普通用户");
					}
				}
				throw new memorandumException(ExceptionValue.LOGIN_USER_ERROR_EXCEPTION,ExceptionValue.LOGIN_USER_ERROR_EXCEPTION_MSG	+ userTypeString + "！");
			}
			}
		return flag;
	}
	
	

}
