package com.yibo.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import com.yibo.bean.Car;
import com.yibo.bean.Item;
import com.yibo.bean.Product;
import com.yibo.dao.ProductDao;
import com.yibo.dao.impl.ProductDaoImpl;
import com.yibo.service.CarService;

public class CarServiceImpl implements CarService {

	@Override
	public Item add2Car(String pid, int count) {
		//调用dao层方法根据pid查找商品
		ProductDao dao = new ProductDaoImpl();
		//创建购物车项Item对象
		Item item = new Item();
		Product pro = null;
		try {
			pro = dao.getProductById(pid);
			//将Product和count设置进去
			item.setPro(pro);
			item.setCount(count);
		} catch (IllegalAccessException | InvocationTargetException | SQLException e) {
			e.printStackTrace();
		}
		return item;
	}

}
