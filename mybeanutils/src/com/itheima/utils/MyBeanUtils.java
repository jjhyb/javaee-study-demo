package com.itheima.utils;


import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

public class MyBeanUtils {
	
	//从map集合开始用内省封装数据
	public static void populate1(Object bean,Map<String,String[]> map) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		//迭代Map集合
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			//通过key获取到value
			String[] values = map.get(key);
			
			//因为map的key和bean的属性一一对应，所以创建PropertyDescriptor对象获得其属性描述器
			PropertyDescriptor descriptor = new PropertyDescriptor(key, bean.getClass());
			//通过属性描述器可以获得其属性的set和get方法
			Method writeMethod = descriptor.getWriteMethod();
			writeMethod.invoke(bean, values[0]);
		}
	}
	
	//
	public static void populate2(Object bean,Map<String,String[]> map) throws IntrospectionException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		//获取bean的字节码文件对象
		Class<? extends Object> clazz = bean.getClass();
		
		BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
		//通过Beaninfo获取其全部的属性描述器
		PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor descriptor : descriptors) {
			//获取属性名
			String name = descriptor.getName();
			//通过属性名获取Map集合对应的value
			String[] values = map.get(name);
			if(values!=null){
				//通过属性描述器，获取setter方法
				Method writeMethod = descriptor.getWriteMethod();
				writeMethod.invoke(bean, values[0]);
			}
		}
	}
}
