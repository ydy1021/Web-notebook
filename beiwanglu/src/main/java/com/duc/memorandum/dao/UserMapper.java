package com.duc.memorandum.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.duc.memorandum.entity.UserVO;
/**
 * 用户的DAO类
 * 
 * 
 */
@Repository
public interface UserMapper {
	/**
	 * 添加一个用户基本信息
	 * 
	 * @param userVO
	 *            用户基本信息对象
	 * @return 数据库操作影响的行数
	 */
	int addUser(UserVO userVO);
	/**
	 * 检查电话号码是否存在
	 */
    int getUserByPhoneNumber(Long phoneNumber);
    /**
     * 检查用户登录是否正确
     */
    UserVO getUserVO(Map<String, Object> map);
 
	/**
	 * 获取用户列表
	 */
    List<UserVO> getUserList(Map<String, Object> map);
	/**
	 * 获取用户列表的数量
	 */
    int getUserListCount(Map<String, Object> map);
    /**
     * 删除用户
     */
    int deleteUserByID(Long ID);
    /**
     * 获取用户详情
     */
    UserVO getUserDetail(Long userID);
 
    /**
     * 修改用户类型
     */
    int updateUserType(Map<String, Object> map);

    /**
     * 修改用户
     */
    int updateUserData(UserVO userVO);
    /**
     * 检查该用户名是否存在
     */
    int getUserbyUserName(String userName);

 
    
    
}
