package com.yibo.service.admin.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yibo.bean.Category;
import com.yibo.constant.Constant;
import com.yibo.dao.admin.AdminCategoryDao;
import com.yibo.dao.admin.impl.AdminCategoryDaoImpl;
import com.yibo.service.admin.AdminCategoryService;
import com.yibo.utils.JedisUtil;
import com.yibo.utils.JsonUtil;
import com.yibo.utils.UUIDUtils;

import redis.clients.jedis.Jedis;

public class AdminCategoryServiceImpl implements AdminCategoryService {

	private static final String String = null;

	@Override
	public List<Category> findAll() {
		//调用dao层方法查询分类信息
		AdminCategoryDao dao = new AdminCategoryDaoImpl();
		List<Category> list = new ArrayList<>();
		try {
			list = dao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void add(String cname) {
		//创建一个Category分类对象
		Category cg = new Category();
		//设置商品ID
		cg.setCid(UUIDUtils.getId());
		//设置商品cname
		cg.setCname(cname);
		//调用dao层方法添加分类信息
		AdminCategoryDao dao = new AdminCategoryDaoImpl();
		try {
			dao.addCategory(cg);
			//添加分类成功后，要及时更新分类到redis，以便用户访问网站时获取
			List<Category> list = findAll();
			//将list集合转换为json
			String json = JsonUtil.list2json(list);
			//将json数据存入redis中
			save2Redis(Constant.STORE_CATEGORY_ALL,json);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将数据更新到redis中
	 * @param string2
	 * @param string3
	 */
	private void save2Redis(String key, String value) {
		//创建jedis连接
		Jedis jedis = JedisUtil.getJedis();
		//将获取到的数据存储到jedis中
		jedis.set(key, value);
		//关闭资源
		jedis.close();
	}

	@Override
	public boolean delete(java.lang.String cid) {
		//调用dao层方法删除商品分类
		AdminCategoryDao dao = new AdminCategoryDaoImpl();
		boolean flag = false;
		try {
			dao.delete(cid);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Category edit(String cid) {
		//调用dao层方法查询商品分类,根据cid
		AdminCategoryDao dao = new AdminCategoryDaoImpl();
		Category cg = null;
		try {
			cg = dao.edit(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cg;
	}

	@Override
	public void edit(String cid, String cname) {
		//调用dao层方法更新商品分类,根据cid
		AdminCategoryDao dao = new AdminCategoryDaoImpl();
		try {
			dao.edit(cid,cname);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}