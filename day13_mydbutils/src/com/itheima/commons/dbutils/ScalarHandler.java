package com.itheima.commons.dbutils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScalarHandler implements ResultSetHandler<Object> {

	@Override
	public Object handler(ResultSet rs) throws SQLException {
		if(rs.next()){
			return rs.getObject(1);
		}
		return null;
	}

}
