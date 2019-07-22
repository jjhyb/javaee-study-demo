package com.yibo.mytest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.lang.model.element.Element;

//定义该注解用在方法上,ElementType的枚举值
@Target(value={ElementType.METHOD})
//保证运行时这个注解还在
@Retention(value=RetentionPolicy.RUNTIME)
//注解类
public @interface MyTest {

}
