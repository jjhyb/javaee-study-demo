package com.itheima.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用业务逻辑
		ProductService ps = new ProductServiceImpl();
		List<Product> products = ps.findAll();
		//判断返回结果是否为null
		if(products != null){
			//如果不为null,则遍历结果结合
			/*for (int i = 0; i < products.size(); i++) {
				//将遍历出来的javaBean对象设置到request域中
				request.setAttribute((i+1)+"", products.get(i));
			}*/
			request.setAttribute("products", products);
		}
		//请求转发到商品首页
		request.getRequestDispatcher("index3.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
