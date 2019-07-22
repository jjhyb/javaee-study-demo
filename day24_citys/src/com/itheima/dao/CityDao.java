package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;

import com.itheima.domain.City;
import com.itheima.domain.Province;

public interface CityDao {

	List<Province> findPro() throws SQLException ;

	List<City> findCity(String pid) throws SQLException ;

}
