package com.yibo.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.yibo.bean.Category;
import com.yibo.constant.Constant;
import com.yibo.dao.CategoryDao;
import com.yibo.dao.impl.CategoryDaoImpl;
import com.yibo.service.CategoryService;
import com.yibo.utils.JedisUtil;
import com.yibo.utils.JsonUtil;

import redis.clients.jedis.Jedis;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public String findAll() {
		/*//创建jedis连接
		Jedis jedis = JedisUtil.getJedis();
		//从jedis中获取数据
		String json = jedis.get(Constant.STORE_CATEGORY_ALL);
		//如果数据为null,那么代表redis中没有保存数据
		if(json == null){
			try {
				//则要先从mysql数据库中把分类信息查询出来
				json = findAllCategory();
				//获取到数据之后再把数据存放到redis中
				jedis.set(Constant.STORE_CATEGORY_ALL, json);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/
		
		//1从redis中获取分类信息的数据
		String json = getFromRedis(Constant.STORE_CATEGORY_ALL);
		//如果数据为null,那么代表redis中没有保存数据
		if(json == null){
			try {
				//则要先从mysql数据库中获取分类信息
				json = findAllCategory();
				//将从mysql数据库中的分类信息保存到redis数据库中
				save2Redis(Constant.STORE_CATEGORY_ALL, json);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return json;
	}

	/**
	 * 从mysql数据库中将分类信息查出来
	 * @return
	 * @throws SQLException 
	 */
	private String findAllCategory() throws SQLException{
		//调用dao层方法查询全部的分析信息
		CategoryDao dao = new CategoryDaoImpl();
		List<Category> list = dao.findAllCategory();
		//将list集合转换为json
		return JsonUtil.list2json(list);
	}
	
	/**
	 * 将商品分类信息保存到redis数据库中
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
	 * 从redis数据库中获取商品分类信息
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
}
