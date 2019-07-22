package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;

import com.itheima.domain.Words;

public interface SearchDao {

	List<Words> searchWords(String msg) throws SQLException ;

}
