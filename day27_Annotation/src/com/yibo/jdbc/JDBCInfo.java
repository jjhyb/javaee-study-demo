package com.yibo.jdbc;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value=RetentionPolicy.RUNTIME)
public @interface JDBCInfo {
	String driverClassName() default "com.mysql.jdbc.Driver";
	String url();
	String username() default "root";
	String password();
}
