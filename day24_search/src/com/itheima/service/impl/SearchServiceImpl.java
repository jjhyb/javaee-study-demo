package com.itheima.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itheima.dao.SearchDao;
import com.itheima.dao.impl.SearchDaoImpl;
import com.itheima.domain.Words;
import com.itheima.service.SearchService;

import net.sf.json.JSONArray;

public class SearchServiceImpl implements SearchService {

	@Override
	public String search(String msg) {
		SearchDao dao = new SearchDaoImpl();
		List<Words> words = new ArrayList<>();
		try {
			 words = dao.searchWords(msg);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 JSONArray jsonarray = JSONArray.fromObject(words);
		 return jsonarray.toString();
	}

}
