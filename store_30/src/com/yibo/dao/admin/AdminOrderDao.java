package com.yibo.dao.admin;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import com.yibo.bean.Order;
import com.yibo.bean.Orderitem;

public interface AdminOrderDao {

	int findAll(String state) throws SQLException;

	List<Order> findAll(int curPage, String state, Integer adminProductPagesize) throws SQLException;

	List<Orderitem> findOrderitemByOid(String oid) throws SQLException, IllegalAccessException, InvocationTargetException;

	Order findOrderByOid(String oid) throws SQLException;

	void updateState(Order order) throws SQLException;



}