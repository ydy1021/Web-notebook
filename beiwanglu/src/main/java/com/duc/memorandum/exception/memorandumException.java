package com.duc.memorandum.exception;

import com.duc.memorandum.constant.ReturnValue;

public class memorandumException extends Exception {

	private static final long serialVersionUID = 5207139239291145035L;
	private int code = ReturnValue.CODE_FAILED;
	private int errorCode = ReturnValue.CODE_FAILED;
	private String type = "";
	private String msg;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public memorandumException(String type, String msg) {
		this.type = type;
		this.msg = msg;
	}

	public memorandumException(String type, String msg, int code, int errorCode) {
		this.type = type;
		this.code = code;
		this.msg = msg;
		this.errorCode = errorCode;
	}
}
