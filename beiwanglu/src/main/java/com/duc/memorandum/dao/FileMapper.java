package com.duc.memorandum.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.duc.memorandum.entity.FileVO;

@Repository
public interface FileMapper extends BaseMapper<FileVO>  {
/**
 * 
 * 添加文件
 */
	int addFile(FileVO fileVO);
	/**
	 * 
	 * @param opendID
	 * @return
	 */
	FileVO getQrByUserID(Map<String, Object> map);
	
	
	

}
