package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;

import com.itheima.domain.Record;

public interface RecordDao {

	void addRecord(Record record) throws SQLException ;

	List<Record> findAll(int userId) throws SQLException ;
}
