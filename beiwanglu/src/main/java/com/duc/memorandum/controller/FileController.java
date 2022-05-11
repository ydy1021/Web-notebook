package com.duc.memorandum.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.duc.memorandum.constant.ReturnValue;
import com.duc.memorandum.entity.FileVO;
import com.duc.memorandum.entity.UserVO;
import com.duc.memorandum.exception.memorandumException;
import com.duc.memorandum.service.FileService;
import com.duc.memorandum.util.HttpRequestUtil;
import com.duc.memorandum.util.ResultObject;

import net.sf.json.JSONObject;

/**
 * 文件的Controller类
 * 
 * 
 */
@Controller
@RequestMapping("/file")
public class FileController {
	@Resource
	private FileService fileService;

	@ResponseBody
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFile(MultipartHttpServletRequest request, String type,
			HttpServletResponse response) {
		// 返回值初始化
		ResultObject resultObject = new ResultObject(ReturnValue.CODE_FAILED,
				ReturnValue.FILE_UPLOAD_FAILED, ReturnValue.FILE_UPLOAD_FAILED,null);
		try {
			List<String> loginUserTypeList = new ArrayList<String>();
			loginUserTypeList.add(UserVO.TYPE_ADMIN);
			loginUserTypeList.add(UserVO.TYPE_DEALER);
			loginUserTypeList.add(UserVO.TYPE_ORDINARY);
			HttpRequestUtil.checkLoginSession(request, loginUserTypeList);
			FileVO file = fileService.addFile(request, type, response);
			if (file != null) {
				JSONObject data = JSONObject.fromObject(file);
				resultObject = new ResultObject(ReturnValue.CODE_SUCCESSED,
						ReturnValue.FILE_UPLOAD_SUCCESSED,
						ReturnValue.FILE_UPLOAD_SUCCESSED, data);
			}
		} catch (memorandumException me) {
			resultObject = new ResultObject(me.getCode(), me.getMsg(),
					me.getMsg(), null, me.getErrorCode());
		} catch (Exception e) {
			resultObject = new ResultObject(ReturnValue.CODE_FAILED,
					ReturnValue.FILE_UPLOAD_FAILED, e.getMessage(), null);
		}

		// 返回值处理
		String returnValue = ""; // 使用JSON字符串返回，而不直接使用Java对象，原因是Java对象中属性为Null时，不能被序列化，后面解决了再改回去也可以
		if (resultObject != null && JSONObject.fromObject(resultObject) != null) {
			returnValue = JSONObject.fromObject(resultObject).toString();
		}
		return returnValue;
	}

	/**
	 * 阿里云图片上传存储器
	 * 
	 * @param request
	 * @param type
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/aliyunUploadFile", method = RequestMethod.POST)
	public String aliyunUploadFile(MultipartFile upfile,MultipartHttpServletRequest request) {
		// 返回值初始化
		ResultObject resultObject = new ResultObject(ReturnValue.CODE_FAILED,
				ReturnValue.FILE_UPLOAD_FAILED, ReturnValue.FILE_UPLOAD_FAILED,
				null);
		try {
			 Iterator<String> itr = request.getFileNames();
			// 创建上传文件对象
		     MultipartFile multipartFile = request.getFile(itr.next());
			FileVO file = fileService.addAliyunFile(multipartFile);
			if (file != null) {
				JSONObject data = JSONObject.fromObject(file);
				resultObject = new ResultObject(ReturnValue.CODE_SUCCESSED,
						ReturnValue.FILE_UPLOAD_SUCCESSED,
						ReturnValue.FILE_UPLOAD_SUCCESSED, data);
			}
		} catch (memorandumException me) {
			resultObject = new ResultObject(me.getCode(), me.getMsg(),
					me.getMsg(), null, me.getErrorCode());
		} catch (Exception e) {
			resultObject = new ResultObject(ReturnValue.CODE_FAILED,
					ReturnValue.FILE_UPLOAD_FAILED, e.getMessage(), null);
		}
		// 返回值处理
		String returnValue = ""; // 使用JSON字符串返回，而不直接使用Java对象，原因是Java对象中属性为Null时，不能被序列化，后面解决了再改回去也可以
		if (resultObject != null && JSONObject.fromObject(resultObject) != null) {
			returnValue = JSONObject.fromObject(resultObject).toString();
		}
		return returnValue;
	}

	/**
	 * 阿里云图片上传存储器
	 * 
	 * @param request
	 * @param type
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/aliyunUpload", method = RequestMethod.POST)
	public String aliyunUpload(String image) {
		// 返回值初始化
		ResultObject resultObject = new ResultObject(ReturnValue.CODE_FAILED,
				ReturnValue.FILE_UPLOAD_FAILED, ReturnValue.FILE_UPLOAD_FAILED,
				null);
		try {
			FileVO file = fileService.addAliyun(image);
			System.out.println(image);
			if (file != null) {
				JSONObject data = JSONObject.fromObject(file);
				resultObject = new ResultObject(ReturnValue.CODE_SUCCESSED,
						ReturnValue.FILE_UPLOAD_SUCCESSED,
						ReturnValue.FILE_UPLOAD_SUCCESSED, data);
			}
		} catch (memorandumException me) {
			resultObject = new ResultObject(me.getCode(), me.getMsg(),
					me.getMsg(), null, me.getErrorCode());
		} catch (Exception e) {
			resultObject = new ResultObject(ReturnValue.CODE_FAILED,
					ReturnValue.FILE_UPLOAD_FAILED, e.getMessage(), null);
		}

		// 返回值处理
		String returnValue = ""; // 使用JSON字符串返回，而不直接使用Java对象，原因是Java对象中属性为Null时，不能被序列化，后面解决了再改回去也可以
		if (resultObject != null && JSONObject.fromObject(resultObject) != null) {
			returnValue = JSONObject.fromObject(resultObject).toString();
		}
		return returnValue;
	}
	
	/**
	 * 阿里云分片上传
	 * @param request
	 * @param type
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/aliyunfpUpload", method = RequestMethod.POST)
	public String aliyunFpUpload(MultipartFile file) throws Exception {
		// 返回值初始化
		ResultObject resultObject = new ResultObject(ReturnValue.CODE_FAILED,ReturnValue.FILE_UPLOAD_FAILED, ReturnValue.FILE_UPLOAD_FAILED, null);
		
		FileVO fileVO = fileService.addAliyunFenpian(file);
		if (fileVO != null) {
			JSONObject data = JSONObject.fromObject(fileVO);
			resultObject = new ResultObject(ReturnValue.CODE_SUCCESSED,
					ReturnValue.FILE_UPLOAD_SUCCESSED,
					ReturnValue.FILE_UPLOAD_SUCCESSED, data);
		}
		// 返回值处理
		String returnValue = ""; // 使用JSON字符串返回，而不直接使用Java对象，原因是Java对象中属性为Null时，不能被序列化，后面解决了再改回去也可以
		if (resultObject != null && JSONObject.fromObject(resultObject) != null) {
			returnValue = JSONObject.fromObject(resultObject).toString();
		}	
		return returnValue;
	}
	
	/**
	 * 百度富文本编辑器图片上传
	 * @param request
	 * @param type
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bdaliyunUploadFile", method = RequestMethod.POST)
	public Map<String, String> bdaliyunUploadFile(MultipartFile upfile) {
		Map<String, String> ret=new HashMap<String, String>();
		try {
			FileVO file = fileService.addAliyunFile(upfile);
			if (file != null) {
			ret.put("url", file.getURL());
			ret.put("state", "SUCCESS");
			}
		} catch (memorandumException me) {
			ret.put("state", "上传失败");
		} catch (Exception e) {
			ret.put("state", "上传失败");
		}
		return ret;
	}
}
