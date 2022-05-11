package com.duc.memorandum.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duc.memorandum.entity.UserVO;
import com.duc.memorandum.exception.memorandumException;


/**
 * 用户的Service接口层
 * 
 * @author dongmin
 * 
 */
public interface UserService {

    /**
     *用户注册
     */
	int addUser(UserVO userVO,String code,String inviteUserID,HttpServletRequest request)throws memorandumException;


    /**
     * 获取用户数量
     */
	int getUserListCount(Map<String, Object> map)throws memorandumException;
	/**
	 * 获取用户列表
	 */
	List<UserVO> getUserList(Map<String, Object> map)throws memorandumException;
	/**
	 * 删除用户
	 */
	int deleteUserByID(Long ID)throws memorandumException;
	/**
	 * 修改用户信息
	 */
	int updateUser(UserVO userVO)throws memorandumException;
	/**
	 * 获取用户详情
	 */
	UserVO getUserDetail(Long userId)throws memorandumException;
    /**
     * 用户登录
     */
    UserVO userLogin(String userName,String password,String code,HttpServletRequest request)throws memorandumException;
    
}
