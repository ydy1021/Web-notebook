package com.duc.memorandum.entity;

import org.codehaus.jackson.map.annotate.JsonSerialize;
/**
 * 登录Session信息的VO
 * 
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class LoginSessionVO {

	/**
	 * 登录用户的用户ID
	 */
	private Long userID = new Long(0);
	/**
	 * 登录用户的用户名
	 */
	private String userName = "";
	/**
	 * 登录用户的类型，
	 */
	private String type = "";
	
	/**
	 * 用户头像URL
	 */
    private String headImgURL="";
	/**
	 * 登录的唯一标识
	 */
	private String uniqueNumber = "";
	/**
	 */
    private String dealerName="";
    /**
     * 
     * 用户类型
     */
    private String userType;
    

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public LoginSessionVO(Long userID, String userName,String headImgURL,String type,String dealerName,String userType) {
		this.userID = userID;
		this.userName = userName;
		this.headImgURL=headImgURL;
		this.type=type;
		this.dealerName=dealerName;
		this.userType=userType;
		
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHeadImgURL() {
		return headImgURL;
	}

	public void setHeadImgURL(String headImgURL) {
		this.headImgURL = headImgURL;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUniqueNumber() {
		return uniqueNumber;
	}

	public void setUniqueNumber(String uniqueNumber) {
		this.uniqueNumber = uniqueNumber;
	}

}
