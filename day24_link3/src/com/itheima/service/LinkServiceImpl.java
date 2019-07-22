package com.itheima.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itheima.dao.LinkDao;
import com.itheima.dao.impl.LinkDaoImpl;
import com.itheima.domain.Bean;
import com.itheima.domain.City;
import com.itheima.domain.District;
import com.itheima.domain.Province;

import net.sf.json.JSONArray;

public class LinkServiceImpl implements LinkService {

	@Override
	public String searchPro() {
		//调用dao层查找
		LinkDao dao = new LinkDaoImpl();
		List<Province> list = new ArrayList<>();
		try {
			list = dao.searchPro();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSONArray jsonarray = JSONArray.fromObject(list);
		return jsonarray.toString();
	}

	@Override
	public String searchCity(String pid) {
		//调用dao层查找
		LinkDao dao = new LinkDaoImpl();
		List<City> list = new ArrayList<>();
		try {
			list = dao.searchCity(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();
	}

	@Override
	public String searchDis(String cid) {
		//调用dao层查找
		LinkDao dao = new LinkDaoImpl();
		List<District> list = new ArrayList<>();
		try {
			list = dao.searchDis(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();
	}

	@Override
	public String search(String name) {
		//调用dao层查找
		LinkDao dao = new LinkDaoImpl();
		List<Object[]> list = new ArrayList<>();
		try {
			list = dao.search(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(list);
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();
	}

}
