package com.yibo.service.admin.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yibo.bean.Order;
import com.yibo.bean.Orderitem;
import com.yibo.bean.PageBean;
import com.yibo.constant.Constant;
import com.yibo.dao.admin.AdminOrderDao;
import com.yibo.dao.admin.impl.AdminOrderDaoImpl;
import com.yibo.service.admin.AdminOrderService;
import com.yibo.utils.JsonUtil;

public class AdminOrderServiceImpl implements AdminOrderService {

	@Override
	public PageBean<Order> page(int curPage, String state) {
		//创建PageBean对象用于保存路径
		PageBean<Order> pb = new PageBean<>();
		//先查询出订单数量
		AdminOrderDao dao = new AdminOrderDaoImpl();
		try {
			int count = dao.findAll(state);
			//计算总页数,用三元表达式更简单
			int totalPage = count%Constant.ADMIN_PRODUCT_PAGESIZE==0?count/Constant.ADMIN_PRODUCT_PAGESIZE:count/Constant.ADMIN_PRODUCT_PAGESIZE+1;
			//根据每页显示数据条数和当前页查询出当前页显示的数据
			List<Order> list = dao.findAll(curPage,state,Constant.ADMIN_PRODUCT_PAGESIZE);
			//给PageBean设置数据
			//设置总数据条数
			pb.setTotalSize(count);
			//设置当前页
			pb.setCurPage(curPage);
			//设置每页数据条数
			pb.setPageSize(Constant.ADMIN_PRODUCT_PAGESIZE);
			//设置总页数
			pb.setTotalPage(totalPage);
			//将订单集合封装到PageBean
			pb.setList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pb;
	}

	@Override
	public String findOrderitemByOid(String oid) {
		//调用dao层方法通过oid获取订单项信息
		AdminOrderDao dao = new AdminOrderDaoImpl();
		List<Orderitem> list = new ArrayList<>();
		String json = "";
		try {
			list = dao.findOrderitemByOid(oid);
			//将集合数据转成json数据
			json = JsonUtil.list2json(list);
		} catch (IllegalAccessException | InvocationTargetException | SQLException e) {
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public Order updateState(String oid) {
		//调用dao层方法通过oid获取订单项信息
		AdminOrderDao dao = new AdminOrderDaoImpl();
		
		Order order = null;
		try {
			//查询出订单数据，并封装到order对象中
			order = dao.findOrderByOid(oid);
			//如果订单不为null
			if(order != null){
				//如果订单状态大于0并且小于4
				if(order.getState()>0 && order.getState()<4){
					//那么将订单状态取出来自增一次，在设置回去
					Integer state = order.getState();
					order.setState(++state);
				}
			}
			//调用dao层方法将订单状态更新回去
			dao.updateState(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//将状态修改后的订单返回
		return order;
	}

}
