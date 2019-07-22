package com.yibo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	/**
	 * 获的当前日期字符串表现形式
	 * @return
	 */
	public static String getCurrtentTime(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	/**
	 * 获取当前时间戳
	 * @return
	 */
	public static String getCurrentTimeStamp(){
		Date date = new Date();
		long timeStamp = date.getTime();
		return timeStamp+"";
	}
	
	/**
	 * 将时间戳字符串转换成时间
	 * @param timeStamp
	 * @return
	 */
	public static String getCurrentTimeStamp2(String timeStamp){
		long time = Long.parseLong(timeStamp);
		Date date = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
}
