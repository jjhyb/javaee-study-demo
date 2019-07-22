package com.yibo.web.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import com.yibo.bean.PageBean;
import com.yibo.bean.Product;
import com.yibo.constant.Constant;
import com.yibo.service.admin.AdminProductService;
import com.yibo.service.admin.impl.AdminProductServiceImpl;
import com.yibo.web.servlet.BaseServlet;

/**
 * 后台系统管理商品的servlet
 */
public class AdminProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	public String page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求商品展示发过来的数据
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		//调用业务层方法获取商品 分页显示数据
		AdminProductService aps = new AdminProductServiceImpl();
		PageBean<Product> pb = aps.findProPage(curPage);
		//将PageBean设置到域对象中
		request.setAttribute("pb", pb);
		//设置为请求转发
		result = Constant.FORWARD;
		
		return "/admin/product/list.jsp";
	}
	
	public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取后台管理商品页面发送过来的商品pid
		String pid = request.getParameter("pid");
		//调用业务层方法，根据pid查询商品信息
		AdminProductService aps = new AdminProductServiceImpl();
		Product pro = aps.findProById(pid);
		return null;
	}
	
	
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取网页传来的要删除的商品pid
		String pid = request.getParameter("pid");
		//调用业务层方法删除商品
		AdminProductService aps = new AdminProductServiceImpl();
		aps.delete(pid);
		//那么重定向到所有商品分页查询的servlet
		result = Constant.REDIRECT;
		return request.getContextPath()+"/adminProduct?method=page&curPage=1";
	}
}
