package com.yibo.service.impl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.yibo.bean.Car;
import com.yibo.bean.Item;
import com.yibo.bean.Order;
import com.yibo.bean.Orderitem;
import com.yibo.bean.PageBean;
import com.yibo.bean.Product;
import com.yibo.bean.User;
import com.yibo.constant.Constant;
import com.yibo.dao.OrderDao;
import com.yibo.dao.impl.OrderDaoImpl;
import com.yibo.service.OrderService;
import com.yibo.utils.DateUtil;
import com.yibo.utils.ManagerThreadLocal;
import com.yibo.utils.UUIDUtils;

public class OrderServiceImpl implements OrderService {

	@Override
	public Order saveOrder(User user, Car car) {
		//创建dao层对象
		OrderDao dao = new OrderDaoImpl();
		
		//将购物车car中的信息保存到Orders对象中
		Order order = new Order();
		//设置oid
		order.setOid(UUIDUtils.getId());
		//设置订单产生时间
		order.setOrdertime(DateUtil.getCurrentTimeStamp());
		//设置订单总金额
		order.setTotal(car.getTotal_price());
		//设置订单对应的用户
		order.setUser(user);
		//设置订单状态(比如：1代表用户未付款)
		order.setState(Constant.UNPAY);
		
		//创建一个订单项集合用于保存所以订单项
//		List<Orderitem> list = new ArrayList<>();
		Map<String,Orderitem> orderMap = new HashMap<>();
		
		//将购物车里的购物项取出并转化成订单项里面
		Map<String, Item> map = car.getMap();
		for (Entry<String, Item> en : map.entrySet()) {
			//或购物项的商品pid
			String pid = en.getKey();
			//获取购物项
			Item item = en.getValue();
			//创建一个订单项对象，用于保存每一个订单项
			Orderitem orderitem = new Orderitem();
			//通过商品pid获取商品信息
			Product pro = null;
			try {
				pro = dao.findProductById(pid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//将商品信息设置到订单项里面
			orderitem.setPro(pro);
			//设置每个订单项数量
			orderitem.setCount(item.getCount());
			//设置每个订单项的价格小计
			orderitem.setSubtotal(item.getTotal());
			//将订单信息设置到订单项里面
			orderitem.setOrder(order);
			//设置订单项id
			orderitem.setItemid(UUIDUtils.getId());
			//将每一个设置好的订单项添加到订单项map集合里面
//			list.add(orderitem);
			orderMap.put(pid, orderitem);
		}
		
		//将订单项集合放入到订单对象里面
		order.setMap(orderMap);
		
		//将订单和订单项保存到数据库中
		//开启事务
		ManagerThreadLocal.startTransaction();
		try {
			//调用dao层方法保存订单
			dao.saveOrder(order);
			//调用dao层方法保存订单项
			//先从订单里面获取到订单项
			for (Entry<String, Orderitem> en : order.getMap().entrySet()) {
				//获取每一个订单项
				Orderitem orderitem = en.getValue();
				dao.saveOrderitem(orderitem);
			}
			//如果程序没有错误，那么代表订单以及订单项持久化存盘成功
			//提交事务
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			//如果出错，则回滚事务
			ManagerThreadLocal.rollback();
		}
		//返回订单
		return order;
	}

	@Override
	public PageBean<Order> findOrderPage(int curPage, User user) {
		
		OrderDao dao = new OrderDaoImpl();
		//创建一个pageBean用于封装订单分页数据
		PageBean<Order> pb = new PageBean<>();
		try {
			//调用业务层方法查询全部订单数量
			int totalSize = dao.findcountOrder(user.getUid());
			//计算总页数
			int totalPage = totalSize%Constant.ORDER_PAGE==0 ? totalSize/Constant.ORDER_PAGE : totalSize/Constant.ORDER_PAGE+1;
			//查询每一页要显示的订单详细数据信息
			List<Order> orders = dao.findOrder(user,curPage,Constant.ORDER_PAGE);
			//设置当前页
			pb.setCurPage(curPage);
			//设置每页显示数据条数
			pb.setPageSize(Constant.ORDER_PAGE);
			//设置总页数
			pb.setTotalPage(totalPage);
			//设置总数据条数
			pb.setTotalSize(totalSize);
			//设置订单集合数据
			pb.setList(orders);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return pb;
	}

	@Override
	public Order findByOid(String oid, User user) {
		//调用dao层方法通过订单oid获取订单对象
		OrderDao dao = new OrderDaoImpl();
		Order order = null;
		try {
			order = dao.findByOid(user,oid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public void updateOrder(Order order) {
		//调用dao层方法更新订单数据
		OrderDao dao = new OrderDaoImpl();
		try {
			dao.updateOrder(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Order findOrderByOid(String oid) {
		//调用dao层方法查询订单
		OrderDao dao = new OrderDaoImpl();
		Order order = null;
		try {
			order = dao.findOrderByOid(oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

}
