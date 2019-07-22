package com.yibo.service.admin;

import com.yibo.bean.PageBean;
import com.yibo.bean.Product;

public interface AdminProductService {

	PageBean<Product> findProPage(int curPage);

	Product findProById(String pid);

	boolean saveProduct(Product pro);

	void delete(String pid);

}
