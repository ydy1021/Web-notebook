package com.duc.memorandum.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.duc.memorandum.constant.ExceptionValue;
import com.duc.memorandum.dao.UserMapper;
import com.duc.memorandum.entity.UserVO;
import com.duc.memorandum.exception.memorandumException;
import com.duc.memorandum.service.UserService;

/**
 * 用户的Service实现层
 * 
 */
@Service
public class UserServiceImpl implements UserService {
     
	@Resource
	private UserMapper userMapper;

	


	


	
	
	


	
	


	
	
	

	
	
	private boolean checkPhoneNumber(String phoneNumber) throws memorandumException {
		boolean flag = false;
		System.out.println(phoneNumber);
		if (StringUtils.isBlank(phoneNumber)) {
			throw new memorandumException("手机号码不能为空", "手机号码不能为空");
		}
		Pattern pattern = Pattern.compile(UserVO.PHONE_NUMBER_REGEX);
		Matcher matcher = pattern.matcher(phoneNumber);
		boolean correct = matcher.matches();
		if (!correct) {
			throw new memorandumException("手机号码输入有误请重新输入", "手机号码输入有误请重新输入");
		}
		int existCount = userMapper.getUserByPhoneNumber(Long.parseLong(phoneNumber));
		if (existCount > 0) {
			throw new memorandumException("该手机号已经存在，请重新输入", "该手机号已经存在，请重新输入");
		}
		flag = true;
		return flag;
	}

	
	
	
	
	
	
	/**
	 * 添加用户信息
	 */
	@Override
	public int addUser(UserVO userVO, String code, String inviteUserID,HttpServletRequest request) throws memorandumException {
		int count = 0;
		if (userVO == null) {
			throw new memorandumException(ExceptionValue.PARAM_ERROR_EXCEPTION,ExceptionValue.PARAM_ERROR_EXCEPTION_MSG);
		 }
		//设置默认用户头像
		 userVO.setHeadImgURL("http://dongxiaoyan.oss-cn-beijing.aliyuncs.com/2020/3/17/d66350e8-75fa-4b3e-8058-fe375ecdce49.jpg");
		 Long UUID = new Date().getTime();
		 /**
		 * 校验code是否有效
		 */
//		 checkCode(code, request);
		 /**
		  * 检查手机号码是否有效
		  */
//		 checkPhoneNumber(userVO.getPhoneNumber(), request);
		 
		 String userPassword=userVO.getPassword();
		 if (StringUtils.isBlank(userPassword)) {
		 throw new memorandumException("用户密码不能为空", "用户密码不能为空" );
		 }
		 
		Long userID = Long.parseLong(UUID.toString().substring(7));
		userVO.setUserID(userID);
		userVO.setPassword(userVO.getPassword());
		userVO.setUserName(userVO.getPhoneNumber());
		count = userMapper.addUser(userVO);
	
	
		return count;
	}
    
	

	

	
	
	
	
	
	/**
	 * 检查当前手机号是否输入正确 以及电话号码是否存在
	 * 
	 * @param userName
	 * @throws memorandumException
	 */
	private boolean checkPhoneNumber(String phoneNumber,
			HttpServletRequest request) throws memorandumException {
		boolean flag = false;
		if (StringUtils.isBlank(phoneNumber)) {
			throw new memorandumException("手机号码不能为空", "手机号码不能为空");
		}
		Pattern pattern = Pattern.compile(UserVO.PHONE_NUMBER_REGEX);
		Matcher matcher = pattern.matcher(phoneNumber);
		boolean correct = matcher.matches();
		if (!correct) {
			throw new memorandumException("手机号码输入有误请重新输入", "手机号码输入有误请重新输入");
		}
		int existCount = userMapper.getUserByPhoneNumber(Long.parseLong(phoneNumber));
		if (existCount > 0) {
			throw new memorandumException("该手机号已经存在，请重新输入", "该手机号已经存在，请重新输入");
		}
		HttpSession session = request.getSession();
		if (session == null) {
			throw new memorandumException("验证码失效，请重新发送", "验证码失效，请重新发送");
		}
		String phoneNumberSession = (String) session.getAttribute("phoneNumber");
		if (StringUtils.isBlank(phoneNumberSession)) {
			throw new memorandumException("验证码失效，请重新发送", "验证码失效，请重新发送");
		}
		if (!phoneNumberSession.equals(phoneNumber)) {
			throw new memorandumException("手机号码输入有误请重新输入！", "手机号码输入有误请重新输入！");
		}
		flag = true;
		return flag;
	}

	@Override
	public List<UserVO> getUserList(Map<String, Object> map)
			throws memorandumException {
		List<UserVO> list = null;
		list = userMapper.getUserList(map);
		return list;
	}

	@Override
	public int getUserListCount(Map<String, Object> map) throws memorandumException {
		int count = 0;
		count = userMapper.getUserListCount(map);
		return count;
	}

	@Override
	public int deleteUserByID(Long ID) throws memorandumException {
		int count = 0;
		if (ID == null || ID.longValue() <= 0) {
			throw new memorandumException(ExceptionValue.PARAM_ERROR_EXCEPTION,
					ExceptionValue.PARAM_ERROR_EXCEPTION_MSG);
		}
		count = userMapper.deleteUserByID(ID);

		return count;
	}

	@Override
	public int updateUser(UserVO userVO) throws memorandumException {
		int count = 0;
		if (userVO != null) {
	/*		checkUserName(userVO.getUserName());
			checkHeadImgURL(userVO.getHeadImgURL());
			checkAddress(userVO.getAddress());
			checkQR(userVO.getQrURL());*/
			count = userMapper.updateUserData(userVO);
		}
		return count;
	}

	/**
	 * 检查二维码
	 * 
	 * @param qrURL
	 */
	private boolean checkQR(String qrURL) throws memorandumException {
		boolean flag = false;
		if (StringUtils.isBlank(qrURL)) {
			throw new memorandumException("用户二维码不能为空", "用户二维码不能为空");
		}
		flag = true;
		return flag;
	}

	private boolean checkAddress(String address) throws memorandumException {
		boolean flag = false;
		if (StringUtils.isBlank(address)) {
			throw new memorandumException("用户地址不能为空", "用户地址不能为空");
		}
		flag = true;
		return flag;

	}

	/**
	 * 检查用户头像
	 * 
	 * @param headImgURL
	 */
	private boolean checkHeadImgURL(String headImgURL) throws memorandumException {
		boolean flag = false;
		if (StringUtils.isBlank(headImgURL)) {
			throw new memorandumException("用户名称不能为空", "用户名称不能为空");
		}
		flag = true;
		return flag;
	}

	/**
	 * 检查用户名称
	 * 
	 * @param userName
	 */
	private boolean checkUserName(String userName) throws memorandumException {
		boolean flag = false;
		if (StringUtils.isBlank(userName)) {
			throw new memorandumException("用户名称不能为空", "用户名称不能为空");
		}
		flag = true;
		return flag;
	}

	@Override
	public UserVO getUserDetail(Long userId) throws memorandumException {
		UserVO userVO = null;
		if (userId != null) {
			  userVO = userMapper.getUserDetail(userId);
		}
		return userVO;
	}

	/**
	 * 用户登录
	 */
	@Override
	public UserVO userLogin(String phoneNumber, String password, String code,HttpServletRequest request) throws memorandumException {
		UserVO userVO = null;
		if (StringUtils.isBlank(phoneNumber)) {
			throw new memorandumException("手机号码输入有误请重新输入!", "手机号码输入有误请重新输入!");
		}
		if (StringUtils.isBlank(password)) {
			throw new memorandumException("用户密码不能为空", "用户密码不能为空");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phoneNumber", phoneNumber);
		map.put("password", password);
		userVO = userMapper.getUserVO(map);
		if (userVO == null) {
			throw new memorandumException("用户名或密码输入有误", "用户名或密码输入有误，请重新登录");
		}
		return userVO;
	}

	
	


	

	
	
	

}