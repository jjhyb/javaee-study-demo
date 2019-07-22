package com.itheima.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.dao.RecordDao;
import com.itheima.domain.Record;
import com.itheima.utils.ManagerThreadLocal;

public class RecordDaoImpl implements RecordDao {

	@Override
	public void addRecord(Record record) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(), "insert into record values(?,?,?,?,?,?)", record.getId(),record.getUserId(),record.getType(),record.getMoney(),record.getTargetUserId(),record.getTradeDate());
	}

	@Override
	public List<Record> findAll(int userId) throws SQLException {
		QueryRunner qr = new QueryRunner();
		List<Record> list = qr.query(ManagerThreadLocal.getConnection(), "select * from record where userid=?", new BeanListHandler<>(Record.class), userId);
		return list;
	}

}
