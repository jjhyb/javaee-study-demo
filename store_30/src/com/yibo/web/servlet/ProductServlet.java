package com.yibo.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yibo.bean.PageBean;
import com.yibo.bean.Product;
import com.yibo.constant.Constant;
import com.yibo.service.ProductService;
import com.yibo.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	public String getHot(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用业务层方法获取热门商品信息
		ProductService ps = new ProductServiceImpl();
		String json = ps.getHot();
		//解决response回写数据中文乱码问题
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
		return null;
	}
	public String getLatest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用业务层方法获取最新商品信息
		ProductService ps = new ProductServiceImpl();
		String json = ps.getLatest();
		//解决response回写数据中文乱码问题
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
		return null;
	}
	
	public String getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取网页端传过来的商品pid
		String pid = request.getParameter("pid");
		//调用业务层的方法查找商品
		ProductService ps = new ProductServiceImpl();
		Product pro = ps.getProductById(pid);
		//将Product对象存到域对象中,请求转发到商品展示页面去
		request.setAttribute("pro", pro);
		result = Constant.FORWARD;
		return "/jsp/product_info.jsp";
	}
	
	public String page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取网页端传过来的参数
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		String cid = request.getParameter("cid");
		//调用业务层方法查询分页显示数据
		ProductService ps = new ProductServiceImpl();
		PageBean<Product> pb = ps.findPageBean(curPage,cid);
		//将数据存到域对象中
		request.setAttribute("pb", pb);
		//设置请求转发的boolean值
		result = Constant.FORWARD;
		return "/jsp/product_list.jsp";
	}
}
