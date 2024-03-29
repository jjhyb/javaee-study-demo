package com.yibo.service.admin;

import java.util.List;

import com.yibo.bean.Category;

public interface AdminCategoryService {
	/**
	 * 查询所有商品分类
	 * @return
	 */
	List<Category> findAll();

	/**
	 * 添加商品分类
	 * @param cname
	 */
	void add(String cname);
	
	/**
	 * 根据商品分类cid删除商品分类
	 * @param cid
	 * @return
	 */
	boolean delete(String cid);

	/**
	 * 根据商品分类cid查询商品分类
	 * @param cid
	 * @return
	 */
	Category edit(String cid);

	/**
	 * 根据商品分类cid更新商品分类
	 * @param cid
	 * @param cname
	 */
	void edit(String cid, String cname);


}
