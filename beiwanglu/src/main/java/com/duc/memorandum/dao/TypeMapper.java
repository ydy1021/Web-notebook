package com.duc.memorandum.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.duc.memorandum.entity.TypeVO;


@Repository
public interface TypeMapper {
   
	/**
	 * 新增类型
	 */
 int add(TypeVO dynamicTypeVO);
    /**
	 * 修改类型
	 */
 int update(TypeVO dynamicTypeVO);
 /**
  * 获取类型列表
  */
 List<TypeVO> getList(Map<String, Object> map);
 /**
  * 获取类型数量
  * @return
  */
 int getListCount(Map<String, Object> map);
	
 int delete(Long id);
 
 TypeVO getDetail(Long id);
}
