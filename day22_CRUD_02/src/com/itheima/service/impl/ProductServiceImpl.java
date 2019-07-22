package com.itheima.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itheima.dao.ProductDao;
import com.itheima.dao.impl.ProductDaoImpl;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.utils.ManagerThreadLocal;

public class ProductServiceImpl implements ProductService {
	
	public List<Product> findAll(){
		//调用dao层方法，到数据库查询所有商品信息
		ProductDao dao = new ProductDaoImpl();
		List<Product> list = new ArrayList<>();
		try {
			list = dao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean addProduct(Product product) {
		//调用dao层方法，将product对象数据存储在数据库中
		ProductDao dao = new ProductDaoImpl();
		boolean flag = false;
		try {
			dao.addProduct(product);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Product findProductById(String pid) {
		//调用dao层方法，通过商品pid查询单条商品信息
		ProductDao dao = new ProductDaoImpl();
		Product product = null;
		try {
			product = dao.findProductById(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public boolean updateProduct(Product product) {
		ProductDao dao = new ProductDaoImpl();
		boolean flag = false;
		try {
			dao.updateProduct(product);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean deleteProduct(String pid) {
		//调用dao层方法，通过商品pid删除单条商品信息
		ProductDao dao = new ProductDaoImpl();
		boolean flag = false;
		try {
			dao.deleteProduct(pid);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean deleteChecked(String[] pids) {
		//调用dao层方法，通过商品pid删除商品信息
		boolean flag = false;
		try {
			//开启事务
			ManagerThreadLocal.startTransaction();
			ProductDao dao = new ProductDaoImpl();
			for (String pid : pids) {
				dao.deleteProduct(pid);
			}
			ManagerThreadLocal.commit();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			ManagerThreadLocal.rollback();
		}
		return flag;
	}

	@Override
	public List<Product> search(String pname, String pdesc) {
		//调用dao层方法
		ProductDao dao = new ProductDaoImpl();
		List<Product> list = new ArrayList<>();
		try {
			list = dao.search(pname,pdesc);
//			System.out.println(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public PageBean<Product> findPageBean(int curPage,int pageSize) {
		//创建PageBean
		PageBean pb = new PageBean();
		//创建集合用于接收分页显示的数据集合
		List<Product> list = new ArrayList<>();
		//调用dao层方法
		ProductDao dao = new ProductDaoImpl();
		try {
			//查询总的商品条数
			int totalSize = dao.findCount();
			System.out.println(totalSize);
			//根据总的商品条数和每页显示的数据条数求出总页数
			int totalPage = totalSize%pageSize==0?totalSize/pageSize:totalSize/pageSize+1;
			
			//根据每页显示的数据条数，获取显示信息集合
			list = dao.findPageProduct(curPage,pageSize);
			//将数据封装到PageBean中
			pb.setCurPage(curPage);
			pb.setPageSize(pageSize);
			pb.setTotalPage(totalPage);
			pb.setTotalSize(totalSize);
			pb.setList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//将PageBean返回
		return pb;
	}
}
