package com.yibo.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.yibo.bean.Category;
import com.yibo.bean.Product;
import com.yibo.constant.Constant;
import com.yibo.dao.ProductDao;
import com.yibo.utils.ManagerThreadLocal;

public class ProductDaoImpl implements ProductDao {

	@Override
	public List<Product> findHotProducts( ) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "select * from product where pflag=? and is_hot=? limit ?,?";
		return qr.query(ManagerThreadLocal.getConnection(), sql, new BeanListHandler<>(Product.class), Constant.UPJIA,Constant.HOT,0,9);
	}

	@Override
	public List<Product> findLatestProduct() throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "select * from product where pflag=? order by pdate desc limit ?,?";
		return qr.query(ManagerThreadLocal.getConnection(), sql, new BeanListHandler<>(Product.class), Constant.UPJIA,0,9);
	}

	@Override
	public Product getProductById(String pid) throws SQLException, IllegalAccessException, InvocationTargetException {
		QueryRunner qr = new QueryRunner();
		String sql = "select * from product p,category c where p.cid=c.cid and pid=?";
		Map<String, Object> map = qr.query(ManagerThreadLocal.getConnection(),sql,new MapHandler(), pid);
		//将map数据封装到BeanProduct中
		Product pro = new Product();
		BeanUtils.populate(pro, map);
		//将map数据封装到BeanCategory中
		Category c = new Category();
		BeanUtils.populate(c, map);
		
		//将Category的数据设置到Product中
		pro.setCategory(c);
		
		return pro;
	}

	@Override
	public int getProductCount(String cid) throws SQLException {
		QueryRunner qr = new QueryRunner();
		long count = (long)qr.query(ManagerThreadLocal.getConnection(), "select count(*) from product where cid=?", new ScalarHandler(), cid);
		return (int)count;
	}

	@Override
	public List<Product> pageProducts(String cid, int curPage) throws SQLException {
		QueryRunner qr = new QueryRunner();
		//计算mysql分页查询数据
		int a = (curPage-1)*Constant.PRODUCT_PAGESIZE;
		String sql = "select * from product where cid=? limit ?,?";
		return qr.query(ManagerThreadLocal.getConnection(), sql, new BeanListHandler<>(Product.class), cid,a,Constant.PRODUCT_PAGESIZE);
	}

}
