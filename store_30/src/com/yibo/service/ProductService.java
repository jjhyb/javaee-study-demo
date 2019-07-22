package com.yibo.service;

import com.yibo.bean.PageBean;
import com.yibo.bean.Product;

public interface ProductService {
	/**
	 * 查找热门商品业务
	 * @return
	 */
	String getHot();
	
	/**
	 * 查找最新商品业务
	 * @return
	 */
	String getLatest();

	/**
	 * 根据商品pid查找商品
	 * @param pid
	 * @return
	 */
	Product getProductById(String pid);

	PageBean<Product> findPageBean(int curPage, String cid);

}
