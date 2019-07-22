package com.itheima.utils;

public class StringUtil {
	/**
	 * 判断字符串是否为null或空字符串,是返回true,不是返回false
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str)){	//注意这里是写||不是写&&
			//如果str为null或者str为空字符串，返回true
			return true;
		}
		return false;
	}
}
