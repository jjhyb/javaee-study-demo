package com.itheima.commons.dbutils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BeanListHandler<T> implements ResultSetHandler<List<T>> {
	private Class<T> clazz;
	public BeanListHandler(Class clazz){
		this.clazz = clazz;
	}
	@Override
	public List<T> handler(ResultSet rs) throws SQLException {
		List<T> list = new ArrayList<>();
		try {
			while(rs.next()){
				T obj = clazz.newInstance();
				ResultSetMetaData md = rs.getMetaData();
				int columnCount = md.getColumnCount();
				for (int i = 0; i < columnCount; i++) {
					String columnName = md.getColumnName(i+1);
					Field field = clazz.getDeclaredField(columnName);
					field.setAccessible(true);
					field.set(obj, rs.getObject(i+1));
				}
				list.add(obj);
			}
			return list;
		} catch (Exception e) {
			throw new SQLException(e.getMessage());
		} 
	}

}
