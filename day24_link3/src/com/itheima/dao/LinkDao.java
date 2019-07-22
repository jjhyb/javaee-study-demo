package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;

import com.itheima.domain.Bean;
import com.itheima.domain.City;
import com.itheima.domain.District;
import com.itheima.domain.Province;

public interface LinkDao {

	List<Province> searchPro() throws SQLException;

	List<City> searchCity(String pid) throws SQLException;

	List<District> searchDis(String cid)throws SQLException;

	List<Object[]> search(String name)throws SQLException;

}
