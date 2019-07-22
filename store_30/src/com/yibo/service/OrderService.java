package com.yibo.service;

import com.yibo.bean.Car;
import com.yibo.bean.Order;
import com.yibo.bean.PageBean;
import com.yibo.bean.User;

public interface OrderService {

	/**
	 * 根据用户和购物车生成订单
	 * @param user
	 * @param car
	 * @return
	 */
	Order saveOrder(User user, Car car);

	/**
	 * 根据用户信息进行用户订单分页显示
	 * @param curPage
	 * @param user
	 * @return
	 */
	PageBean<Order> findOrderPage(int curPage, User user);

	/**
	 * 根据订单oid和用户uid查询用户订单
	 * @param oid
	 * @param user
	 * @return
	 */
	Order findByOid(String oid, User user);

	/**
	 * 根据订单更新订单详情
	 * @param order
	 */
	void updateOrder(Order order);

	/**
	 * 根据oid查询订单信息
	 * @param r6_Order
	 * @return
	 */
	Order findOrderByOid(String r6_Order);
}