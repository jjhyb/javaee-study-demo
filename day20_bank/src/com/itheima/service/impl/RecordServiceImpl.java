package com.itheima.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itheima.dao.RecordDao;
import com.itheima.dao.impl.RecordDaoImpl;
import com.itheima.domain.Record;
import com.itheima.service.RecordService;
import com.itheima.utils.ManagerThreadLocal;

public class RecordServiceImpl implements RecordService {

	@Override
	public List<Record> findAll(int userId) {
		List<Record> list = new ArrayList<>();
		try {
			//开启事物
			ManagerThreadLocal.startTransaction();
			RecordDao dao = new RecordDaoImpl();
			list = dao.findAll(userId);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			ManagerThreadLocal.rollback();
		}
		return list;
	}

}
