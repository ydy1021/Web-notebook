package com.duc.memorandum.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.duc.memorandum.constant.GlobalValue;
import com.duc.memorandum.constant.ReturnValue;
import com.duc.memorandum.entity.LoginSessionVO;
import com.duc.memorandum.entity.UserVO;
import com.duc.memorandum.exception.memorandumException;
import com.duc.memorandum.service.UserService;
import com.duc.memorandum.util.HttpRequestUtil;
import com.duc.memorandum.util.ResultObject;

import net.sf.json.JSONObject;
/**
 * 用户的Controller类
 * 
 * 
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	

	
	/**
	 * 用户注册
	 * @param userVO
	 * @param code
	 * @param inviteUserID
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST	)
	public String userLogin(UserVO userVO,String code,String inviteUserID,HttpServletRequest request) {
		// 返回值初始化
		ResultObject resultObject = new ResultObject(ReturnValue.CODE_FAILED,
				ReturnValue.RESOURCE_SAVE_FAILED,
				ReturnValue.RESOURCE_SAVE_FAILED, null);
		try {
			int count =userService.addUser(userVO,code,inviteUserID,request);
			if (count > 0) {
				JSONObject data = new JSONObject();
				data.put("id", userVO.getId());
				resultObject = new ResultObject(ReturnValue.CODE_SUCCESSED,
						ReturnValue.RESOURCE_SAVE_SUCCESSED,
						ReturnValue.RESOURCE_SAVE_SUCCESSED, data);
			}
		} catch (memorandumException me) {
			resultObject = new ResultObject(me.getCode(), me.getMsg(),
					me.getMsg(), null, me.getErrorCode());
		} catch (Exception e) {
			resultObject = new ResultObject(ReturnValue.CODE_FAILED,
					ReturnValue.RESOURCE_SAVE_FAILED, e.getMessage(), null);
		}
		// 返回值处理
		String returnValue = ""; // 使用JSON字符串返回，而不直接使用Java对象，原因是Java对象中属性为Null时，不能被序列化，后面解决了再改回去也可以
		if (resultObject != null && JSONObject.fromObject(resultObject) != null) {
			returnValue = JSONObject.fromObject(resultObject).toString();
		}
		return returnValue;
	}
	
	
	
	

	
	

	
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	public String userLogin(String phoneNumber,String password,String code, HttpServletRequest request) {
		// 返回值初始化
		ResultObject resultObject = new ResultObject(ReturnValue.CODE_FAILED,"登录失败","登录失败", null);
		try {
			     UserVO userVO=userService.userLogin(phoneNumber, password,code,request);
				if (userVO!=null) {
					if (userVO.getPhoneNumber().equals("admin")) {
						LoginSessionVO loginSessionVO=new LoginSessionVO(userVO.getUserID(), userVO.getUserName(), userVO.getHeadImgURL(),UserVO.TYPE_ADMIN,null,userVO.getUserType());
						HttpRequestUtil.setLoginSession(request, loginSessionVO);
						resultObject = new ResultObject(ReturnValue.CODE_SUCCESSED,"登录成功","登录成功", null);	
					}else{
						LoginSessionVO loginSessionVO=new LoginSessionVO(userVO.getUserID(), userVO.getUserName(),  userVO.getHeadImgURL(),UserVO.TYPE_ORDINARY,null,userVO.getUserType());
						HttpRequestUtil.setLoginSession(request, loginSessionVO);
						resultObject = new ResultObject(ReturnValue.CODE_SUCCESSED,"登录成功","登录成功", null);		
						
					}	
				}
		} catch (memorandumException me) {
			resultObject = new ResultObject(me.getCode(), me.getMsg(),me.getMsg(), null, me.getErrorCode()); 
		 } catch (Exception e) {
				resultObject = new 	ResultObject(ReturnValue.CODE_FAILED,ReturnValue.RESOURCE_SAVE_FAILED, e.getMessage(), null);
			
		}
		// 返回值处理
		String returnValue = ""; // 使用JSON字符串返回，而不直接使用Java对象，原因是Java对象中属性为Null时，不能被序列化，后面解决了再改回去也可以
		if (resultObject != null && JSONObject.fromObject(resultObject) != null) {
			returnValue = JSONObject.fromObject(resultObject).toString();
		}
		return returnValue;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public String getUserInfoList(String userType,String gradeType, String isopen ,String sort, String name,String inviteUserName, Integer pageSize, Integer pageNumber, HttpServletRequest request) {

		// 返回值初始化
		ResultObject resultObject = new ResultObject(ReturnValue.CODE_FAILED, ReturnValue.RESOURCE_GET_LIST_FAILED, ReturnValue.RESOURCE_GET_LIST_FAILED, null);
		try {
//		    List<String> loginUserTypeList = new ArrayList<String>();
//			loginUserTypeList.add(UserVO.TYPE_ADMIN);
//			loginUserTypeList.add(UserVO.TYPE_ORDINARY);
//			HttpRequestUtil.checkLoginSession(request, loginUserTypeList);
			// 初始化查询参数
			Map<String, Object> searchMap = new HashMap<String, Object>();
		
			if (StringUtils.isNotBlank(userType)) { // 
				searchMap.put("userType", userType);
			}
			if (StringUtils.isNotBlank(gradeType)) {
				searchMap.put("gradeType", gradeType);
			}
			if (StringUtils.isNotBlank(name)) { 
				searchMap.put("searchString", name.trim());
			}
			if (pageSize == null || pageSize <= 0) {
				pageSize = GlobalValue.PAGE_SIZE;
			}
			if (pageNumber == null || pageNumber <= 0) {
				pageNumber = 1;
			}
			// 获取符合查询条件的所有记录的数量
			int resourceCount = userService.getUserListCount(searchMap);
			// 计算总页数
			int pageCount = (int) Math.ceil((double) resourceCount / pageSize);

			// 由于前台请求的页码，有可能超出实际存在的总页数，所以需要对页码进行判断与必要的重置
//			pageNumber = Math.min(pageCount, pageNumber);

			if (pageNumber <= 0) {
				pageNumber = 1;
			}
			searchMap.put("pageNumber", pageNumber);
			searchMap.put("pageSize", pageSize);
			searchMap.put("startIndex", (pageNumber - 1) * pageSize);

			List<UserVO> userList = userService.getUserList(searchMap);
			if (CollectionUtils.isEmpty(userList)) {
		    userList = new ArrayList<UserVO>();
			}
			JSONObject data = new JSONObject();
			data.put("pageCount", pageCount);
			data.put("pageNumber", pageNumber);
			data.put("pageSize", pageSize);
			data.put("resourceCount", resourceCount);
			data.put("userList", userList);
			
			resultObject = new ResultObject(ReturnValue.CODE_SUCCESSED, ReturnValue.RESOURCE_GET_LIST_SUCCESSED, ReturnValue.RESOURCE_GET_LIST_SUCCESSED, data);
		
		} catch (memorandumException me) {
			resultObject = new ResultObject(me.getCode(), me.getMsg(), me.getMsg(), null, me.getErrorCode());
		} catch (Exception e) {
			resultObject = new ResultObject(ReturnValue.CODE_FAILED, ReturnValue.RESOURCE_GET_LIST_FAILED, e.getMessage(), null);
		}
		
		// 返回值处理
		String returnValue = ""; // 使用JSON字符串返回，而不直接使用Java对象，原因是Java对象中属性为Null时，不能被序列化，后面解决了再改回去也可以
		if (resultObject != null && JSONObject.fromObject(resultObject) != null) {
			returnValue = JSONObject.fromObject(resultObject).toString();
		}
		return returnValue;
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteUser(Long id, HttpServletRequest request) {
		// 返回值初始化
		ResultObject resultObject = new ResultObject(ReturnValue.CODE_FAILED,
				ReturnValue.RESOURCE_DELETE_FAILED,
				ReturnValue.RESOURCE_DELETE_FAILED, null);
		try {
			List<String> loginUserTypeList = new ArrayList<String>();
			loginUserTypeList.add(UserVO.TYPE_ADMIN);
			loginUserTypeList.add(UserVO.TYPE_ORDINARY);
			HttpRequestUtil.checkLoginSession(request, loginUserTypeList);
			int count =userService.deleteUserByID(id);
			if (count > 0) {
				resultObject = new ResultObject(ReturnValue.CODE_SUCCESSED,
						ReturnValue.RESOURCE_DELETE_SUCCESSED,
						ReturnValue.RESOURCE_DELETE_SUCCESSED, null);
			}

		} catch (memorandumException me) {
			resultObject = new ResultObject(me.getCode(), me.getMsg(),
					me.getMsg(), null, me.getErrorCode());

		} catch (Exception e) {
			resultObject = new ResultObject(ReturnValue.CODE_FAILED,
					ReturnValue.RESOURCE_DELETE_FAILED, e.getMessage(), null);
		}

		// 返回值处理
		String returnValue = ""; // 使用JSON字符串返回，而不直接使用Java对象，原因是Java对象中属性为Null时，不能被序列化，后面解决了再改回去也可以
		if (resultObject != null && JSONObject.fromObject(resultObject) != null) {
			returnValue = JSONObject.fromObject(resultObject).toString();
		}
		return returnValue;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateUser(UserVO userVO, HttpServletRequest request) {
		ResultObject resultObject = new ResultObject(ReturnValue.CODE_FAILED,
				ReturnValue.MSG_FAILED_DEFAULT, ReturnValue.MSG_FAILED_DEFAULT,null);
		try {
			List<String> loginUserTypeList = new ArrayList<String>();
			loginUserTypeList.add(UserVO.TYPE_ADMIN);
			loginUserTypeList.add(UserVO.TYPE_ORDINARY);
			HttpRequestUtil.checkLoginSession(request, loginUserTypeList);
            userVO.setUserID(HttpRequestUtil.getLoginSessionUserID(request));
			int count=userService.updateUser(userVO);
			if (count > 0) {
				JSONObject data = new JSONObject();
				data.put("id", userVO.getId());
				resultObject = new ResultObject(ReturnValue.CODE_SUCCESSED,
						ReturnValue.MSG_SUCCESSED_DEFAULT,
						ReturnValue.MSG_SUCCESSED_DEFAULT, data);
			}
		} catch (memorandumException me) {
			resultObject = new ResultObject(me.getCode(), me.getMsg(),
					me.getMsg(), null, me.getErrorCode());
		} catch (Exception e) {
			resultObject = new ResultObject(ReturnValue.CODE_FAILED,
					ReturnValue.RESOURCE_SAVE_FAILED, e.getMessage(), null);
		}

		// 返回值处理
		String returnValue = ""; // 使用JSON字符串返回，而不直接使用Java对象，原因是Java对象中属性为Null时，不能被序列化，后面解决了再改回去也可以
		if (resultObject != null && JSONObject.fromObject(resultObject) != null) {
			returnValue = JSONObject.fromObject(resultObject).toString();
		}
		return returnValue;
	}
	
	


	
	
	
	@ResponseBody
	@RequestMapping(value = "/getDetail", method = RequestMethod.GET)
	public String getUserDetail(HttpServletRequest request) {
		// 返回值初始化
		ResultObject resultObject = new ResultObject(ReturnValue.CODE_FAILED,
				ReturnValue.RESOURCE_GET_DETAIL_FAILED,
				ReturnValue.RESOURCE_GET_DETAIL_FAILED, null);
		try {
		    List<String> loginUserTypeList = new ArrayList<String>();
			loginUserTypeList.add(UserVO.TYPE_ORDINARY);
			loginUserTypeList.add(UserVO.TYPE_ADMIN);
			HttpRequestUtil.checkLoginSession(request, loginUserTypeList);
			Long userID=HttpRequestUtil.getLoginSessionUserID(request);
			
			if (userID!=null&&userID.longValue()>=0) {
			   UserVO userVO=userService.getUserDetail(userID);
				if (userVO!=null) {
					JSONObject data = new JSONObject();
					data.put("userVO", userVO);
					resultObject = new ResultObject(ReturnValue.CODE_SUCCESSED,
							ReturnValue.RESOURCE_GET_DETAIL_SUCCESSED,
							ReturnValue.RESOURCE_GET_DETAIL_SUCCESSED, data);
				}
	      	}
		} catch (memorandumException me) {
			resultObject = new ResultObject(me.getCode(), me.getMsg(),
					me.getMsg(), null, me.getErrorCode());	
		} catch (Exception e) {
			resultObject = new ResultObject(ReturnValue.CODE_FAILED,
					ReturnValue.RESOURCE_GET_DETAIL_FAILED, e.getMessage(), null);
		}
		// 返回值处理
		String returnValue = ""; // 使用JSON字符串返回，而不直接使用Java对象，原因是Java对象中属性为Null时，不能被序列化，后面解决了再改回去也可以
		if (resultObject != null && JSONObject.fromObject(resultObject) != null) {
			returnValue = JSONObject.fromObject(resultObject).toString();
		}
		return returnValue;
	}
	
	

	
	
	
}
