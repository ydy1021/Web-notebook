package com.duc.memorandum.entity;

import java.sql.Timestamp;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 用户VO类<br>
 * 
 * <font color="#FF0000">本类为用户的基本信息类，是父类，真正的用户是供应商、经销商、导购员</font>
 * 
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserVO {
	/**
	 * ID自增列<br>
	 * <font color="#FF0000">不是用户ID（userID）</font>
	 */
	private Long id;
	/**
	 * 用户ID
	 */
	private Long userID;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 用户账号
	 */
	private Long account;
	/**
	 * 
	 */
	private String type;


	/**
	 * 创建时间
	 */
	private Timestamp createDate;

	/**
	 * 创建时间
	 */
	private Long createDateTime;

	/**
	 * 用户的权限
	 */
	private String permission;
	/**
	 * 联系电话<br>
	 */
	private String phoneNumber;

	/**
	 * 用户的头像URL
	 */
	private String headImgURL;
	/**
	 * 用户是否已删除
	 */
	private String isDeleted;

	/**
	/**
	 * 用戶類型
	 * 
	 * @return
	 */
	private String userType;



	/**
	 * 用户的地址
	 * 
	 * @return
	 */
	private String address;
	
	/**
	 * 职业
	 * 
	 * @return
	 */
	private String work;

	private String password;
	
	
    
    
    
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}



	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	/**
	 * 邀请人的用户ID
	 * 
	 * @return
	 */
	private Long inviteUserID;
	/**
	 * 是否为激活状态
	 * 
	 * @return
	 */
	private String isActivation;
	/**
	 * 用户名称
	 * 
	 * @return
	 */
	private String nickName;
	/**
	 * 用户二维码
	 * 
	 * @return
	 */
	private String qrURL;
	/**
	 * 经销商的手机号码
	 * 
	 * @return
	 */
	private String dealerPhone;
	/**
	 * 经销上的二维码
	 * 
	 * @return
	 */
	private String dealerQRURL;
	/**
	 * 经销商地址
	 * 
	 * @return
	 */
	private String dealerAddress;
	/**
	 * 经销商的logo
	 */
	private String logoURL;

	/**
	 * 用户类型之 经销商："1"
	 */
	public static final String TYPE_DEALER = "1";
	/**
	 * 用户类型之 普通用户："0"
	 */
	public static final String TYPE_ORDINARY = "0";
	/**
	 * 用户类型之 管理员："A"
	 */
	public static final String TYPE_ADMIN = "A";
	/**
	 * 用户名匹配的正则表达式："[A-Za-z]\\w{5,15}"，表示 6-16位由英文字母、数字、下划线组成的以英文字母开头的字符串
	 */
	public static final String USER_NAME_REGEX = "[A-Za-z]\\w{5,15}";

	/**
	 * 密码的正则表达式："\\w{6,16}"，表示6-16位由英文字母、数字、下划线组成的字符串
	 */
	public static final String PASSWORD_REGEX = "\\w{6,16}";

	/**
	 * 用户昵称匹配的正则表达式："[\\w\\u4e00-\\u9fa5]{1,20}"，表示1-20位由英文字母、数字、下划线、汉字组成的字符串
	 */
	public static final String NICK_NAME_REGEX = "[\\w\\u4e00-\\u9fa5]{1,20}";

	/**
	 * 联系电话匹配的正则表达式："\\d{1,20}"，表示1-20位数字，由于电话号码号段一直在更新，所以这里不使用太严格的限制
	 */
	public static final String PHONE_NUMBER_REGEX = "\\d{1,20}";



	public String getLogoURL() {
		return logoURL;
	}

	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}



	public String getDealerAddress() {
		return dealerAddress;
	}

	public void setDealerAddress(String dealerAddress) {
		this.dealerAddress = dealerAddress;
	}

	public String getDealerQRURL() {
		return dealerQRURL;
	}

	public void setDealerQRURL(String dealerQRURL) {
		this.dealerQRURL = dealerQRURL;
	}

	public String getDealerPhone() {
		return dealerPhone;
	}

	public void setDealerPhone(String dealerPhone) {
		this.dealerPhone = dealerPhone;
	}

	public String getQrURL() {
		return qrURL;
	}

	public void setQrURL(String qrURL) {
		this.qrURL = qrURL;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Long getInviteUserID() {
		return inviteUserID;
	}

	public String getIsActivation() {
		return isActivation;
	}

	public void setIsActivation(String isActivation) {
		this.isActivation = isActivation;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		if (createDate != null) {
			this.createDateTime = createDate.getTime();
		}
		this.createDate = createDate;
	}

	public Long getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Long createDateTime) {
		this.createDateTime = createDateTime;
	}

	public void setInviteUserID(Long inviteUserID) {
		this.inviteUserID = inviteUserID;
	}

	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}



	public String getHeadImgURL() {
		return headImgURL;
	}

	public void setHeadImgURL(String headImgURL) {
		this.headImgURL = headImgURL;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	
	public Long getAccount() {
		return account;
	}

	public void setAccount(Long account) {
		this.account = account;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

}
