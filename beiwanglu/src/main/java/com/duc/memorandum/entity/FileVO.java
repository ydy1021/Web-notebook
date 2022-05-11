package com.duc.memorandum.entity;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baomidou.mybatisplus.annotations.TableName;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)

@TableName("tmh_file")
public class FileVO {
	/**
	 * 文件的自增列ID
	 */
	private Long id;
	/**
	 * 文件的名称
	 */
	private String name;
	/**
	 * 
	 *用户ID
	 */
	private Long userID;
	
	/**
	 * 文件的后缀
	 */
	private String suffix;
	/**
	 * 文件的原始名称
	 */
	private String oldName;
	/**
	 * 文件类型
	 */
	private String type;
	/**
	 * 文件的创建时间
	 */
	private Date createDate;
	/**
	 * 用户类型
	 * @return
	 */
	private String userType;

	
	private String URL;
	
	
	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}