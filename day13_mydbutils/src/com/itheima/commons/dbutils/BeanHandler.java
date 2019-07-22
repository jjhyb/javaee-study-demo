package com.itheima.commons.dbutils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class BeanHandler<T> implements ResultSetHandler<T> {
	private Class<T> clazz;
	public BeanHandler(Class clazz){
		this.clazz = clazz;
	}
	@Override
	public T handler(ResultSet rs) throws SQLException {
		try {
			T obj = clazz.newInstance();
			if(rs.next()){
				
				ResultSetMetaData md = rs.getMetaData();
				int columnCount = md.getColumnCount();
				for (int i = 0; i < columnCount; i++) {
					String columnName = md.getColumnName(i+1);
					Field field = clazz.getDeclaredField(columnName);
					field.setAccessible(true);
					field.set(obj, rs.getObject(i+1));
				}
			}
			return obj;
		} catch (Exception e) {
			throw new SQLException(e.getMessage());
		}
	}

}
