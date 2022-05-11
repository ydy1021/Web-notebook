package com.duc.memorandum.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duc.memorandum.constant.GlobalValue;
import com.duc.memorandum.constant.ReturnValue;
import com.duc.memorandum.dao.TypeMapper;
import com.duc.memorandum.entity.TypeVO;
import com.duc.memorandum.entity.UserVO;
import com.duc.memorandum.exception.memorandumException;
import com.duc.memorandum.util.HttpRequestUtil;
import com.duc.memorandum.util.ResultObject;

import net.sf.json.JSONObject;

/**
 * 备忘录类型
 *
 */
@Controller
@RequestMapping("/type")
public class TypeController {
	
	@Resource
	private TypeMapper typeMapper;
	
	
	/**
	 * 备忘录类型添加
	 * @param dynamicTypeVO
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addDynamic(TypeVO TypeVO,HttpServletRequest request) {
		// 返回值初始化
		ResultObject resultObject = new ResultObject(ReturnValue.CODE_FAILED,
				ReturnValue.RESOURCE_SAVE_FAILED,
				ReturnValue.RESOURCE_SAVE_FAILED, null);
		try {  
			
			int count =typeMapper.add(TypeVO);
			if (count > 0) {
				JSONObject data = new JSONObject();
				data.put("id", TypeVO.getId());
				resultObject = new ResultObject(ReturnValue.CODE_SUCCESSED,
						ReturnValue.RESOURCE_SAVE_SUCCESSED,
						ReturnValue.RESOURCE_SAVE_SUCCESSED, data);
			}

		} catch (Exception e) {
			resultObject = new ResultObject(ReturnValue.CODE_FAILED,
					ReturnValue.RESOURCE_SAVE_FAILED, e.getMessage(), null);
		}
		// 返回值处理
		String returnValue = ""; // 使用JSON字符串返回，而不直接使用Java对象，原因是Java对象中属性为Null时，不能被序列化，后面解决了再改回去也可以
		if (resultObject != null && JSONObject.fromObject(resultObject) != null) {
			returnValue = JSONObject.fromObject(resultObject).toString();
		}
		return returnValue;
	}
     
	
	/**
	 * 备忘录类型获取列表
	 * @param fileText
	 * @param pageSize
	 * @param pageNumber
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public String getDynamicVOList(String fileText, Integer pageSize, Integer pageNumber, HttpServletRequest request) {
		// 返回值初始化
		ResultObject resultObject = new ResultObject(ReturnValue.CODE_FAILED,
				ReturnValue.RESOURCE_GET_LIST_FAILED,
				ReturnValue.RESOURCE_GET_LIST_FAILED, null);
		try {

			
			// 初始化查询参数
			Map<String, Object> searchMap = new HashMap<String, Object>();
			searchMap.put("fileText", fileText);
			Long userID=HttpRequestUtil.getLoginSessionUserID(request);
			
			
			if (userID!=null&&userID.longValue()>=0) {
				searchMap.put("localUserID", userID);
			}
			 
			if (pageSize == null || pageSize <= 0) {
				pageSize = GlobalValue.PAGE_SIZE;
			}
			if (pageNumber == null || pageNumber <= 0) {
				pageNumber = 1;
			}
			// 获取符合查询条件的所有记录的数量
			int resourceCount =typeMapper.getListCount(searchMap);
			
			// 计算总页数
			int pageCount = (int) Math.ceil((double) resourceCount /pageSize);
//			// 由于前台请求的页码，有可能超出实际存在的总页数，所以需要对页码进行判断与必要的重置
//			pageNumber = Math.min(pageCount, pageNumber);

			if (pageNumber <= 0) {
				pageNumber = 1;
			}
			searchMap.put("pageNumber", pageNumber);
			searchMap.put("pageSize", pageSize);
			searchMap.put("startIndex", (pageNumber - 1) * pageSize);
			List<TypeVO> list =typeMapper.getList(searchMap);
			if (CollectionUtils.isEmpty(list)) {
				list = new ArrayList<TypeVO>();
			}
			JSONObject data = new JSONObject();
			data.put("pageCount", pageCount);
			data.put("pageNumber", pageNumber);
			data.put("pageSize", pageSize);
			data.put("resourceCount", resourceCount);
			data.put("list", list);
			resultObject = new ResultObject(ReturnValue.CODE_SUCCESSED,
					ReturnValue.RESOURCE_GET_LIST_SUCCESSED,
					ReturnValue.RESOURCE_GET_LIST_SUCCESSED, data);

		} catch (Exception e) {
			resultObject = new ResultObject(ReturnValue.CODE_FAILED,
					ReturnValue.RESOURCE_GET_LIST_FAILED, e.getMessage(), null);
		}

		// 返回值处理
		String returnValue = ""; // 使用JSON字符串返回，而不直接使用Java对象，原因是Java对象中属性为Null时，不能被序列化，后面解决了再改回去也可以
		if (resultObject != null && JSONObject.fromObject(resultObject) != null) {
			returnValue = JSONObject.fromObject(resultObject).toString();
		}
		return returnValue;
	}
	
	

	/**
	 * 备忘录类型删除
	 * @param fileText
	 * @param pageSize
	 * @param pageNumber
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteworkDetail(Long id, HttpServletRequest request) {
		// 返回值初始化
		ResultObject resultObject = new ResultObject(ReturnValue.CODE_FAILED,
				ReturnValue.RESOURCE_DELETE_FAILED,
				ReturnValue.RESOURCE_DELETE_FAILED, null);
		try {
			List<String> loginUserTypeList = new ArrayList<String>();
			loginUserTypeList.add(UserVO.TYPE_ORDINARY);
			loginUserTypeList.add(UserVO.TYPE_ADMIN);
			HttpRequestUtil.checkLoginSession(request, loginUserTypeList);
			int count =typeMapper.delete(id);
			if (count > 0) {
				resultObject = new ResultObject(ReturnValue.CODE_SUCCESSED,
						ReturnValue.RESOURCE_DELETE_SUCCESSED,
						ReturnValue.RESOURCE_DELETE_SUCCESSED, null);
			}

		} catch (memorandumException me) {
			resultObject = new ResultObject(me.getCode(), me.getMsg(),
					me.getMsg(), null, me.getErrorCode());

		} catch (Exception e) {
			resultObject = new ResultObject(ReturnValue.CODE_FAILED,
					ReturnValue.RESOURCE_DELETE_FAILED, e.getMessage(), null);
		}

		// 返回值处理
		String returnValue = ""; // 使用JSON字符串返回，而不直接使用Java对象，原因是Java对象中属性为Null时，不能被序列化，后面解决了再改回去也可以
		if (resultObject != null && JSONObject.fromObject(resultObject) != null) {
			returnValue = JSONObject.fromObject(resultObject).toString();
		}
		return returnValue;
	}

	
	
	/**
	 * 备忘录获取详情
	 * @param fileText
	 * @param pageSize
	 * @param pageNumber
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getDetail", method = RequestMethod.POST)
	public String getDetail(Long id,HttpServletRequest request) {
		// 返回值初始化
		ResultObject resultObject = new ResultObject(ReturnValue.CODE_FAILED,
				ReturnValue.RESOURCE_GET_DETAIL_FAILED,
				ReturnValue.RESOURCE_GET_DETAIL_FAILED, null);
		try {
			if (id!=null&&id.longValue()>=0) {
		     	TypeVO dynamicTypeVO=typeMapper.getDetail(id);
				if (dynamicTypeVO!=null) {
					
					JSONObject data = new JSONObject();
					data.put("dynamicTypeVO", dynamicTypeVO);
					resultObject = new ResultObject(ReturnValue.CODE_SUCCESSED,
							ReturnValue.RESOURCE_GET_DETAIL_SUCCESSED,
							ReturnValue.RESOURCE_GET_DETAIL_SUCCESSED, data);
				}
	      	}

		} catch (Exception e) {
			resultObject = new ResultObject(ReturnValue.CODE_FAILED,
					ReturnValue.RESOURCE_GET_DETAIL_FAILED, e.getMessage(), null);
		}
		// 返回值处理
		String returnValue = ""; // 使用JSON字符串返回，而不直接使用Java对象，原因是Java对象中属性为Null时，不能被序列化，后面解决了再改回去也可以
		if (resultObject != null && JSONObject.fromObject(resultObject) != null) {
			returnValue = JSONObject.fromObject(resultObject).toString();
		}
		return returnValue;
	}
	
	
	
	
	
	
}
