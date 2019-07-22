package com.itheima.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.dao.CityDao;
import com.itheima.domain.City;
import com.itheima.domain.Province;
import com.itheima.utils.ManagerThreadLocal;

public class CityDaoImpl implements CityDao {

	@Override
	public List<Province> findPro() throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(),"select * from province", new BeanListHandler<>(Province.class));
	}

	@Override
	public List<City> findCity(String pid) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(),"select * from city where pid=?", new BeanListHandler<>(City.class), pid);
	}
	
}
