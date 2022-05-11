package com.duc.memorandum.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.duc.memorandum.entity.beiwangluVO;


@Repository
public interface beiwangluMapper {
	/**
	 * 添加
	 */
	int add(beiwangluVO dynamicVO);
	
	/**
	 * 批量新增
	 */
	int insertForeach(List<beiwangluVO> list);
	
	/**
	 * 获取详情
	 */
	beiwangluVO getDetail(Long ID);
	/**
	 * 修改
	 */
	int update(beiwangluVO dynamicVO);
	/**
	 * 获取列表
	 */
	List<beiwangluVO> getList(Map<String, Object> map);
	/**
	 * 获取数量
	 */
	int getCount(Map<String, Object> map);
	/**
	 * 修改阅读数量
	 */
	int updateRedCount(Long id);
	/**
	 * 删除
	 */
	int delete(Long id);
}
 