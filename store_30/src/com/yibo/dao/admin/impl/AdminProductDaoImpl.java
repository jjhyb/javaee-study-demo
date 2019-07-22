package com.yibo.dao.admin.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.yibo.bean.Product;
import com.yibo.constant.Constant;
import com.yibo.dao.admin.AdminProductDao;
import com.yibo.utils.ManagerThreadLocal;

public class AdminProductDaoImpl implements AdminProductDao {

	@Override
	public int findProCount() throws SQLException {
		QueryRunner qr = new QueryRunner();
		long count = (long)qr.query(ManagerThreadLocal.getConnection(), "select count(*) from product where pflag=?", new ScalarHandler(),Constant.UPJIA);
		return (int)count;
	}

	@Override
	public List<Product> findProAll(int curPage, Integer adminPagesize) throws SQLException {
		//根据mysql要求设置分页数
		int a = (curPage-1)*adminPagesize;
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(),"select * from product where pflag=? limit ?,?",new BeanListHandler<>(Product.class),Constant.UPJIA,a,adminPagesize);
	}

	@Override
	public void saveProduct(Product pro) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "insert into product values(?,?,?,?,?,?,?,?,?,?)";
		qr.update(ManagerThreadLocal.getConnection(),sql,pro.getPid(),pro.getPname(),pro.getMarket_price(),pro.getShop_price(),pro.getPimage(),pro.getPdate(),pro.getIs_hot(),pro.getPdesc(),pro.getPflag(),pro.getCategory().getCid());
	}

	@Override
	public void delete(String pid) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"delete from product where pid=?", pid);
	}

}
