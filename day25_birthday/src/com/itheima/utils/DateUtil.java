package com.itheima.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	
	/**
	 * 通过Calendar获取设置时间的Date返回值
	 * @return
	 */
	public static Date getDate(){
		//获得当前时间对象
		Calendar calendar = Calendar.getInstance();
		//获得当前天数，向后延后一天
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		//设置时分秒都为0
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 通过Calendar获取当前月份和日
	 * @return
	 */
	public static String getMonthAndDay(){
		//获得当前时间对象
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH)+1;//老外是从0到11月
		String mon = "";
		if(month<10){
			mon = "0"+month;
		}else {
			mon = month+"";
		}
		int mday = calendar.get(Calendar.DAY_OF_MONTH);
		String day = "";
		if(mday<10){
			day = "0"+mday;
		}else {
			day = mday+"";
		}
		return mon+"-"+day;
	}
	
}
