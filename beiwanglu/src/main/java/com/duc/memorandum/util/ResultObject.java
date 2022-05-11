package com.duc.memorandum.util;

import net.sf.json.JSONObject;

import com.duc.memorandum.constant.ReturnValue;

/**
 * 
 */
public class ResultObject {

	/**
	 * 数据处理成功与否的状态code，默认为数据处理失败
	 */
	private int code = ReturnValue.CODE_FAILED;

	/**
	 * 数据处理成功与否的状态code，默认为数据处理失败
	 */
	private int errorCode = ReturnValue.CODE_FAILED;
	/**
	 * 数据处理后的提示信息，默认为数据处理失败<br>
	 * <font color= '#ff0000'>严禁轻意使用此缺省值</font><br>
	 * 具体的数据处理失败信息，应该在程序中根据情况具体提供，例如“该卖场不存在，或者已被删除，无法修改该卖场的信息 ！”
	 */
	private String msg = ReturnValue.MSG_FAILED_DEFAULT;
	/**
	 * 数据处理后的提示信息，默认为数据处理失败<br>
	 * <font color= '#ff0000'>严禁轻意使用此缺省值</font><br>
	 * 具体的数据处理失败信息，应该在程序中根据情况具体提供，例如“该卖场不存在，或者已被删除，无法修改该卖场的信息 ！”
	 */
	private String debugMsg = ReturnValue.MSG_FAILED_DEFAULT;

	/**
	 * 返回值中的具体数据对象
	 */
	private JSONObject data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getDebugMsg() {
		return debugMsg;
	}

	public void setDebugMsg(String debugMsg) {
		this.debugMsg = debugMsg;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

	public ResultObject(int code, String errorMsg, String debugMsg, JSONObject jsonObject, int errorCode) {
		this.setCode(code);
		this.setMsg(errorMsg);
		this.setDebugMsg(debugMsg);
		this.setData(jsonObject);
		this.setErrorCode(errorCode);
	}

	public ResultObject(int code, String errorMsg, String debugMsg, JSONObject jsonObject) {
		this.setCode(code);
		this.setMsg(errorMsg);
		this.setDebugMsg(debugMsg);
		this.setData(jsonObject);
		this.setErrorCode(ReturnValue.CODE_FAILED);
	}

}
