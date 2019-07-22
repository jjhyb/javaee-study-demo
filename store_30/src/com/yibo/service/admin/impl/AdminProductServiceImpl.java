package com.yibo.service.admin.impl;

import java.sql.SQLException;
import java.util.List;

import com.yibo.bean.PageBean;
import com.yibo.bean.Product;
import com.yibo.constant.Constant;
import com.yibo.dao.admin.AdminProductDao;
import com.yibo.dao.admin.impl.AdminProductDaoImpl;
import com.yibo.service.admin.AdminProductService;

public class AdminProductServiceImpl implements AdminProductService {


	@Override
	public PageBean<Product> findProPage(int curPage) {
		//创建pageBean用于封装数据
		PageBean<Product> pb = new PageBean<>();
		//调用dao层方法查询上架商品总的数量
		AdminProductDao dao = new AdminProductDaoImpl();
		try {
			int totalSize = dao.findProCount();
			//根据总商品数量和每页显示商品数量，计算页数,用的三元表达式
			int totalPage = totalSize%Constant.ADMIN_PRODUCT_PAGESIZE==0?totalSize/Constant.ADMIN_PRODUCT_PAGESIZE:totalSize/Constant.ADMIN_PRODUCT_PAGESIZE+1;
			//获取每页商品要展示的数据
			List<Product> list = dao.findProAll(curPage,Constant.ADMIN_PRODUCT_PAGESIZE);
			//设置分页数据到PageBean中
			pb.setCurPage(curPage);
			pb.setTotalPage(totalPage);
			pb.setTotalSize(totalSize);
			//这是设置分页显示数据条数
			pb.setPageSize(Constant.ADMIN_PRODUCT_PAGESIZE);
			pb.setList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pb;
	}

	@Override
	public Product findProById(String pid) {
		return null;
	}

	@Override
	public boolean saveProduct(Product pro) {
		//调用dao层方法添加商品信息
		AdminProductDao apd = new AdminProductDaoImpl();
		boolean flag = false;
		try {
			apd.saveProduct(pro);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public void delete(String pid) {
		//调用dao层方法删除商品
		AdminProductDao apd = new AdminProductDaoImpl();
		try {
			apd.delete(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
