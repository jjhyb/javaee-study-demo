package com.yibo.service.admin;

import com.yibo.bean.Order;
import com.yibo.bean.PageBean;

public interface AdminOrderService {

	PageBean<Order> page(int curPage, String state);

	String findOrderitemByOid(String oid);

	Order updateState(String oid);

}
