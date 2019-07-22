package com.itheima.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itheima.dao.CityDao;
import com.itheima.dao.impl.CityDaoImpl;
import com.itheima.domain.City;
import com.itheima.domain.Province;
import com.itheima.service.CityService;

import net.sf.json.JSONArray;

public class CityServiceImpl implements CityService {

	@Override
	public String searchPro() {
		CityDao dao = new CityDaoImpl();
		List<Province> list = new ArrayList<>();
		try {
			list = dao.findPro();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();
	}

	@Override
	public String searchCity(String pid) {
		CityDao dao = new CityDaoImpl();
		List<City> list = new ArrayList<>();
		try {
			list = dao.findCity(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JSONArray jsonArray = JSONArray.fromObject(list);
		
		return jsonArray.toString();
	}

}
