package com.duc.memorandum.constant;

/**
 * 与异常相关的常量
 * 
 * 
 */
public class ExceptionValue {
	/**
	 * 全局异常之 参数有误："paramErrorException"
	 */
	public static final String PARAM_ERROR_EXCEPTION = "paramErrorException";
	/**
	 * 全局异常之 参数有误："paramErrorException"
	 */
	public static final String PARAM_ERROR_EXCEPTION_MSG = "参数有误！";
	/**
	 * 全局异常之 名称不符合规范："nameErrorException"
	 */
	public static final String NAME_ERROR_EXCEPTION = "nameErrorException";

	/**
	 * 全局异常提示信息之 名称不符合规范："名称不符合规范！"
	 */
	public static final String NAME_ERROR_EXCEPTION_MSG = "名称不符合规范！";
	/**
	 * 全局异常之 用户没有登录，或者登录已失效："noLoginUserErrorException"
	 */
	public static final String NO_LOGIN_USER_ERROR_EXCEPTION = "noLoginUserErrorException";

	/**
	 * 全局异常提示信息之 用户没有登录，或者登录已失效："用户没有登录，或者登录已失效！"
	 */
	public static final String NO_LOGIN_USER_ERROR_EXCEPTION_MSG = "用户没有登录，或者登录已失效！";
	/**
	 * 全局异常信息之用户名不存在
	 */
	public static final String LOGIN_USER_NAME_IS_NOT_EXIST_ERROR_EXCEPTION="LoginUserNameIsNotExistErrorException";
	/**
	 * 全局异常信息提示之用户名不存在
	 */
	public static final String LOGIN_USER_NAME_IS_NOT_EXIST_ERROR_EXCEPTION_MSG="用户名不存在";
	/**
	 * 全局异常提示信息之 用户名不能为空
	 */
	public static final String LOGIN_USER_NAME__ERROR_EXCEPTION="LoginUserNameIsNotExistErrorException";
	/**
	 * 全局异常提示信息之 用户名不能为空
	 */
	public static final String LOGIN_USER_NAME__ERROR_EXCEPTION_MSG="用户名不能为空";
	/**
	 * 全局异常之 登录用户必须是XXX："loginUserErrorException"
	 */
	public static final String LOGIN_USER_ERROR_EXCEPTION = "loginUserErrorException";

	/**
	 * 全局异常提示信息之 登录用户必须是XXX："登录用户必须是："，后面请自行添加后缀，例如“管理员”
	 */
	public static final String LOGIN_USER_ERROR_EXCEPTION_MSG = "登录用户必须是：";
	/**
	 * 全局异常之 文件大小超出限制："fileLargerException"
	 */
	public static final String FILE_LARGER_EXCEPTION = "fileLargerException";

	/**
	 * 全局异常提示信息之 图片文件超出大小："图片文件超出大小，最大只支持5M！"
	 */
	public static final String IMAGE_LARGER_EXCEPTION_MSG = "图片文件超出大小，最大只支持10M！";

	/**
	 * 全局异常提示信息之 ZIP包文件超出大小："ZIP包文件超出大小，最大只支持10M！"
	 */
	public static final String ZIP_LARGER_EXCEPTION_MSG = "ZIP包文件超出大小，最大只支持10M！";

	/**
	 * 全局异常提示信息之 视频文件超出大小："视频文件超出大小，最大只支持10M！"
	 */
	public static final String VIDEO_LARGER_EXCEPTION_MSG = "视频文件超出大小，最大只支持10M！";

	/**
	 * 全局异常提示信息之 上传了不支持的文件格式："不支持的文件格式，请联系管理员！"
	 */
	public static final String FILE_UNACCEPTED_EXCEPTION_MSG = "不支持的文件格式，请联系管理员！";
	/**
	 * 用户异常之 用户登录失败："userLoginFailedException"
	 */
	public static final String USER_LOGIN_FAILED_EXCEPTION = "userLoginFailedException";

	/**
	 * 用户异常提示信息之 用户登录失败："登录失败，请检查用户名与密码！"
	 */
	public static final String USER_LOGIN_FAILED_EXCEPTION_MSG = "登录失败，请检查用户名与密码！";
    //创建文件异常
	/**
	 * 文件创建异常信息提示之 文件名称不能为空
	 */
	public static final String FLODER_NAME_ERROR_EXCEPTION="FloderNameErrorException"; 
	/**
	 * 文件创建异常信息提示之 文件名称不能为空
	 */
	public static final String FLODER_NAME_ERROR_EXCEPTION_MSG="文件名称不能为空"; 
    /**
     * 文件创建异常信息提示之文件名称已经存在
     */
	public static final String FLODER_NAME_EXIST_EXCEPTION="FloderNameExistException"; 
	/**
	 * 文件创建异常信息提示之文件名已经存在
	 */
	public static final String FLODER_NAME_EXIST_EXCEPTION_MSG="该文件名系统已经存在，请重新输入！";
	/**
	 * 文件创建异常信息提示之文件为默认文件
	 */
	public static final String FLODER_DEAFULT_EXCEPTION="FloderDeafultException"; 
	/**
	 * 文件创建异常信息提示之该文件为默认文件
	 */
	public static final String FLODER_DEAFULT_EXCEPTION_MSG="该文件为默认文件不能更改或删除"; 
	//创建作品
	/**
	 * 作品创建异常信息提示之 文件名称不能为空
	 */
	public static final String WORKS_NAME_ERROR_EXCEPTION="FloderNameErrorException"; 
	/**
	 *  作品创建异常信息提示之 文件名称不能为空
	 */
	public static final String WORKS_NAME_ERROR_EXCEPTION_MSG="文件名称不能为空"; 
	

}
