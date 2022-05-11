package com.duc.memorandum.constant;

/**
 * 接口返回时的信息常量
 * 
 * 
 */
public class ReturnValue {

	/**
	 * 
	 */
	public static final int CODE_FAILED = -1;
	/**
	 * 
	 */
	public static final int CODE_SUCCESSED = 200;

	
	/**
	 * 错误返回值中的Code之：-1002 <br>
	 * 表示用户没有登录 ，或者登录已失效
	 * 
	 */
	public static final int ERROR_CODE_NO_LOGIN = -1002;
	/**
	 * 返回值中的说明文本之：数据处理失败，请重试！<br>
	 * <font color= '#ff0000'>此值仅用于缺省值，严禁轻意使用。</font><br>
	 * 具体的数据处理失败信息，应该在程序中根据情况具体提供，例如“该卖场不存在，或者已被删除，无法修改该卖场的信息 ！ ”
	 */
	public static final String MSG_FAILED_DEFAULT = "数据处理失败，请重试！";
	/**
	 * 返回值中的说明文本之：数据处理成功！<br>
	 */
	public static final String MSG_SUCCESSED_DEFAULT = "数据处理成功！";
	/**
	 * 资源保存失败时的提示信息："保存失败，请重试！"
	 */
	public static final String RESOURCE_SAVE_FAILED = "保存失败，请重试！";
	/**
	 * 资源保存成功时的提示信息："保存成功！"
	 */
	public static final String RESOURCE_SAVE_SUCCESSED = "保存成功！";

	/**
	 * 文件上传失败时的提示信息："文件上传失败，请重试！"
	 */
	public static final String FILE_UPLOAD_FAILED = "文件上传失败，请重试！";
	/**
	 * 文件上传成功时的提示信息："文件上传成功！"
	 */
	public static final String FILE_UPLOAD_SUCCESSED = "文件上传成功！";
	/**
	 * 资源删除失败时的提示信息："删除失败，请重试！"
	 */
	public static final String RESOURCE_DELETE_FAILED = "删除失败，请重试！";

	/**
	 * 资源删除成功时的提示信息："删除成功！"
	 */
	public static final String RESOURCE_DELETE_SUCCESSED = "删除成功！";

	/**
	 * 资源详情获取失败时的提示信息："详情获取失败，请重试！"
	 */
	public static final String RESOURCE_GET_DETAIL_FAILED = "详情获取失败，请重试！";

	/**
	 * 资源详情获取成功时的提示信息："详情获取成功！"
	 */
	public static final String RESOURCE_GET_DETAIL_SUCCESSED = "详情获取成功！";

	/**
	 * 资源列表获取失败时的提示信息："列表获取失败，请重试！"
	 */
	public static final String RESOURCE_GET_LIST_FAILED = "列表获取失败，请重试！";

	/**
	 * 资源列表获取成功时的提示信息："列表获取成功！"
	 */
	public static final String RESOURCE_GET_LIST_SUCCESSED = "列表获取成功！";

	/**
	 * 用户登录失败时的提示信息："登录失败，请检查用户名与密码！"
	 */
	public static final String USER_LOGIN_FAILED = "登录失败，请检查用户名与密码！";

	/**
	 * 用户登录成功时的提示信息："登录成功！"
	 */
	public static final String USER_LOGIN_SUCCESSED = "登录成功！";

	/**
	 * 用户注销失败时的提示信息："注销失败，请重试！"
	 */
	public static final String USER_LOGOFF_FAILED = "注销失败，请重试！";

	/**
	 * 用户注销按成功时的提示信息："注销成功！"
	 */
	public static final String USER_LOGOFF_SUCCESSED = "注销成功！";
	


}
