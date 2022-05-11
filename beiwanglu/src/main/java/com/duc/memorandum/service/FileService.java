package com.duc.memorandum.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.duc.memorandum.entity.FileVO;
import com.duc.memorandum.exception.memorandumException;

public interface FileService {
	/**
	 * 新增文件
	 */
	FileVO addFile(MultipartHttpServletRequest request,String type, HttpServletResponse response)throws Exception ;
	/**
	 * 新增阿里云文件上传
	 */
	FileVO addAliyunFile(MultipartFile upfile)throws Exception ;
	/**
	 * 新增阿里云文件上传
	 */
	FileVO addAliyun(String share)throws Exception ;
	
	/**
	 * 新增阿里云断点文件上传
	 */
	FileVO addAliyunFenpian(MultipartFile file)throws Exception ;
	
}
