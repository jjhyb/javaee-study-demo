package com.itheima.commons.dbutils;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetHandler<T> {
	T handler(ResultSet rs) throws SQLException;
}
