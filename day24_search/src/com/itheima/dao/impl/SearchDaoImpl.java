package com.itheima.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.dao.SearchDao;
import com.itheima.domain.Words;
import com.itheima.utils.ManagerThreadLocal;

public class SearchDaoImpl implements SearchDao {

	@Override
	public List<Words> searchWords(String msg) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(),"select * from words where word like ?", new BeanListHandler<>(Words.class), msg+"%");
	}

}
