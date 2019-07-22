package com.itheima.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.utils.ManagerThreadLocal;
import com.itheima.utils.StringUtil;

public class ProductDaoImpl implements ProductDao {
	public List<Product> findAll() throws SQLException{
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(),"select * from product", new BeanListHandler<>(Product.class));
	}

	@Override
	public void addProduct(Product product) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(), "insert into product values(?,?,?,?,?,?,?)",product.getPid(),product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),product.getPdesc(),product.getPdate());
	}

	@Override
	public Product findProductById(String pid) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(),"select * from product where pid=?", new BeanHandler<>(Product.class), pid);
		
	}

	@Override
	public void updateProduct(Product p) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(), "update product set pname=?,market_price=?,shop_price=?,pimage=?,pdesc=?,pdate=? where pid=?",p.getPname(),p.getMarket_price(),p.getShop_price(),p.getPimage(),p.getPdesc(),p.getPdate(),p.getPid());
	}

	@Override
	public void deleteProduct(String pid) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(), "delete from product where pid=?", pid);
	}

	@Override
	public List<Product> search(String pname, String pdesc) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "select * from product";
		//定义一个存放参数的集合
		ArrayList<Object> list = new ArrayList<>();
		//如果pname和pdesc都不为null或空字符串
		if(!StringUtil.isEmpty(pname) && !StringUtil.isEmpty(pdesc)){
			sql += " where pname like ? or pdesc like ?";
			list.add("%"+pname+"%");
			list.add("%"+pdesc+"%");
			//如果只有pname不是null或空字符串
		}else if(!StringUtil.isEmpty(pname)){
			sql += " where pname like ?";
			list.add("%"+pname+"%");
			//如果只有pdesc不是空字符串
		}else if(!StringUtil.isEmpty(pdesc)){
			sql += " where pdesc like ?";
			list.add("%"+pdesc+"%");
		}
		System.out.println(sql);
		Object[] obj  = list.toArray();
		return qr.query(ManagerThreadLocal.getConnection(), sql, new BeanListHandler<>(Product.class),obj);
	}

	@Override
	public int findCount() throws SQLException {
		QueryRunner qr = new QueryRunner();
		long count = (long) qr.query(ManagerThreadLocal.getConnection(),"select count(*) from product", new ScalarHandler());
		System.out.println(count);
		return (int)count;
	}

	@Override
	public List<Product> findPageProduct(int curPage, int pageSize) throws SQLException {
		QueryRunner qr = new QueryRunner();
		//客户端显示的页数必须要-1才能在数据库查出对应的数据
		curPage = curPage -1;
		return qr.query(ManagerThreadLocal.getConnection(),"select * from product limit ?,?", new BeanListHandler<>(Product.class), curPage,pageSize);
	}
}
