package com.duc.memorandum.entity;

import java.sql.Timestamp;

import org.codehaus.jackson.map.annotate.JsonSerialize;
/**
 * 备忘录实体类
 * @author Administrator
 *
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class beiwangluVO{
	
    private Long id;

    private Long userID;
    
    private Long readCount;
    
    private String text;
    
    private String userName;
    
    private String headImgURL;
    
    
    private Long dynamicTypeID;
    
    private String url;
   
    private String type="0";
    
    
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getDynamicTypeID() {
		return dynamicTypeID;
	}

	public void setDynamicTypeID(Long dynamicTypeID) {
		this.dynamicTypeID = dynamicTypeID;
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

	private String description;
    
    
    private Timestamp createDate;
    
    
    private Long createDateTime;
   

	public Long getReadCount() {
		return readCount;
	}

	public void setReadCount(Long readCount) {
		this.readCount = readCount;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		if (createDate!=null) {
		this.createDateTime=createDate.getTime();
		}
		this.createDate = createDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Long createDateTime) {
		this.createDateTime = createDateTime;
	}


    
    
    
}
