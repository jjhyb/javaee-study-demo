package com.yibo.constant;

public class Constant {
	//设置int类型常量，用于用户激活状态
	public static final int UNACTIVE = 0;
	public static final int ACTIVED = 0;
	//设置boolean类型的常量，用于执行请求转发或者重定向之前的判断
	public static final boolean REDIRECT = true;
	public static final boolean FORWARD = false;
	
	//异步校验用户名和密码的boolean判断
	public static final boolean AJAX = true;
	
	//设置一个字符串常量，用于将数据库中获取的分类信息缓冲到redis中
	public static final String STORE_CATEGORY_ALL = "store_category_all";
}
