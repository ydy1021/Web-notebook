package com.duc.memorandum.entity;


/**
 * 备忘录类型
 * @author Administrator
 *
 */
public class TypeVO {
    
	private Long id;
	
	
	/**
	 * 类型名称
	 */
	private String name;
	
	/**
	 * 类型图片
	 */
	private String url;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	

	
	
	
}
