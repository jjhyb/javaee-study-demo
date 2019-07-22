package com.itheima.service;

import java.util.List;

import com.itheima.domain.Record;

public interface RecordService {

	List<Record> findAll(int UserId);

}
