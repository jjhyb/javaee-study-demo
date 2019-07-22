package com.yibo.constant;

public class Constant {
	//设置int类型常量，用于用户激活状态
	public static final int UNACTIVE = 1;
	public static final int ACTIVED = 0;
	//设置boolean类型的常量，用于执行请求转发或者重定向之前的判断
	public static final boolean REDIRECT = true;
	public static final boolean FORWARD = false;
	
	//异步校验用户名和密码的boolean判断
	public static final boolean AJAX = true;
	
	//设置一个字符串常量，用于将数据库中获取的分类信息缓冲到redis中
	public static final String STORE_CATEGORY_ALL = "store_category_all";
	
	//设置一个字符串常量，用于将数据库中获取的热门商品信息缓存到redis中
	public static final String STORE_PRODUCT_HOT = "store_product_hot";
	
	//设置一个字符串常量，用于将数据库中获取的热门商品信息缓存到redis中
	public static final String STORE_PRODUCT_LATEST = "store_product_latest";
	
	//定义2个Integer类型常量，1表示热门，0表示非热门
	public static final Integer HOT = 1;
	public static final Integer UNHOT = 0;
	
	//定义2个Integer类型常量，1表示下架，0表示上架
	public static final Integer DOWNJIA = 1;
	public static final Integer UPJIA = 0;
	
	//定义一个int型常量，用于分页显示数据条数
	public static final Integer PRODUCT_PAGESIZE = 12;
	
	//定义一个int型常量，用于后台商品分页显示数据条数
	public static final Integer ADMIN_PRODUCT_PAGESIZE = 18;
	
	//定义一个常量，1表示用户订单未付款
	public static final int UNPAY = 1;
	//定义一个常量，2表示用户订单已付款未发货
	public static final Integer PAYED = 2;
	
	//定义一个常量，用于用户订单分页显示
	public static final int ORDER_PAGE = 3;
}
