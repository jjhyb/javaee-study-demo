package com.yibo.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import com.yibo.bean.PageBean;
import com.yibo.bean.Product;
import com.yibo.constant.Constant;
import com.yibo.dao.ProductDao;
import com.yibo.dao.impl.ProductDaoImpl;
import com.yibo.service.ProductService;
import com.yibo.utils.JedisUtil;
import com.yibo.utils.JsonUtil;

import redis.clients.jedis.Jedis;

public class ProductServiceImpl implements ProductService {

	@Override
	public String getHot() {
		//从redis中获取热门商品数据
		String json = getFromRedis(Constant.STORE_PRODUCT_HOT);
		//如果json为null
		if(json==null){
			try {
				//从mysql数据库中获取热门商品信息
				json = findHotProducts();
				//将json数据保存到redis中
				save2Redis(Constant.STORE_PRODUCT_HOT, json);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return json;
	}

	@Override
	public String getLatest() {
		//从redis中获取最新商品数据
		String json = getFromRedis(Constant.STORE_PRODUCT_LATEST);
		//如果json为null
		if(json==null){
			try {
				//从mysql数据库中获取最新商品信息
				json = findLatestProducts();
				//将json数据保存到redis中
				save2Redis(Constant.STORE_PRODUCT_LATEST, json);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return json;
	}
	
	/**
	 * 从mysql数据库中将热门商品信息查出来
	 * @return
	 * @throws SQLException 
	 */
	private String findHotProducts() throws SQLException{
		//从mysql数据库中查询热门商品数据
		ProductDao dao = new ProductDaoImpl();
		List<Product> list = dao.findHotProducts();
		//将list集合转换为json
		return JsonUtil.list2json(list);
	}
	
	/**
	 * 从mysql数据库中将热门商品信息查出来
	 * @return
	 * @throws SQLException 
	 */
	private String findLatestProducts() throws SQLException{
		//从mysql数据库中查询热门商品数据
		ProductDao dao = new ProductDaoImpl();
		List<Product> list = dao.findLatestProduct();
		//将list集合转换为json
		return JsonUtil.list2json(list);
	}
	
	/**
	 * 将商品信息保存到redis数据库中
	 * @param key
	 * @param value
	 */
	private void save2Redis(String key,String value){
		//创建jedis连接
		Jedis jedis = JedisUtil.getJedis();
		//获取到数据之后再把数据存放到redis中
		jedis.set(key, value);
		//关闭资源
		jedis.close();
	}
	
	/**
	 * 从redis数据库中获取商品信息
	 * @param key
	 * @return
	 */
	private String getFromRedis(String key){
		//创建jedis连接
		Jedis jedis = JedisUtil.getJedis();
		//获取到数据之后再把数据存放到redis中
		String json = jedis.get(key);
		//关闭资源
		jedis.close();
		
		return json;
	}

	@Override
	public Product getProductById(String pid) {
		//调用dao层方法根据商品Pid获取商品信息
		ProductDao dao = new ProductDaoImpl();
		Product pro = null;
		try {
			pro = dao.getProductById(pid);
		} catch (IllegalAccessException | InvocationTargetException | SQLException e) {
			e.printStackTrace();
		}
		return pro;
	}

	@Override
	public PageBean<Product> findPageBean(int curPage, String cid) {
		//调用业务层方法先获取该商品分类的总数据条数
		ProductDao dao = new ProductDaoImpl();
		PageBean<Product> pb = new PageBean<>();
		try {
			//通过dao层方法得到该商品分类总的数据条数
			int totalSize = dao.getProductCount(cid);
			//通过每页显示数据条数求得总的页数
			//这是3元表达式，是if else语句的简写格式
			int totalPage = totalSize%Constant.PRODUCT_PAGESIZE==0 ? totalSize/Constant.PRODUCT_PAGESIZE : totalSize/Constant.PRODUCT_PAGESIZE+1;
			
			//查询分页显示的商品数据
			List<Product> list = dao.pageProducts(cid,curPage);
			
			//将数据设置到PageBean中
			pb.setCurPage(curPage);
			pb.setTotalPage(totalPage);
			pb.setTotalSize(totalSize);
			pb.setPageSize(Constant.PRODUCT_PAGESIZE);
			pb.setList(list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pb;
	}
}
