package com.yibo.web.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yibo.bean.Category;
import com.yibo.constant.Constant;
import com.yibo.service.admin.AdminCategoryService;
import com.yibo.service.admin.impl.AdminCategoryServiceImpl;
import com.yibo.web.servlet.BaseServlet;

/**
 * 后台系统商品分类管理的servlet
 */
public class AdminCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用业务层方法获取分类信息
		AdminCategoryService acs = new AdminCategoryServiceImpl();
		List<Category> list = acs.findAll();
		//将分类集合list存到域对象中，跳转到展示页面中
		request.setAttribute("list", list);
		//设置为请求转发
		result = Constant.FORWARD;
		return "/admin/category/list.jsp";
	}
	
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取admin添加页面分类页面传过来的数据
		String cname = request.getParameter("cname");
		//调用业务层方法添加商品分类
		AdminCategoryService acs = new AdminCategoryServiceImpl();
		acs.add(cname);
		
		//添加成功之后又从新查一遍
		List<Category> list = acs.findAll();
		request.getSession().setAttribute("list", list);
		//重定向到分类展示页面
		result = Constant.REDIRECT;
		return request.getContextPath()+"/admin/category/list.jsp";
	}
	
	public String edit2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取admin添加页面分类页面传过来的数据
		String cname = request.getParameter("cname");
		String cid = request.getParameter("cid");
		//调用业务层方法添加商品分类
		AdminCategoryService acs = new AdminCategoryServiceImpl();
		acs.edit(cid,cname);
		
		//添加成功之后又从新查一遍
		List<Category> list = acs.findAll();
		request.getSession().setAttribute("list", list);
		//重定向到分类展示页面
		result = Constant.REDIRECT;
		return request.getContextPath()+"/admin/category/list.jsp";
	}
	
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取后台商品分类展示页面传过来的参数
		String cid = request.getParameter("cid");
		//调用业务层方法，根据商品分类cid删除商品分类
		AdminCategoryService acs = new AdminCategoryServiceImpl();
		boolean flag = acs.delete(cid);
		//获取session
		HttpSession session = request.getSession();
		//调用service层方法从新查询一遍商品分类，并返回其集合
		List<Category> list = acs.findAll();
		if(flag){
			//删除商品分类成功
			//将list存到域对象中
			session.setAttribute("list", list);
		}else {
			//删除商品分类失败
			//将list存到域对象中
			session.setAttribute("list", list);
			session.setAttribute("msg", "删除商品失败！");
		}
		//设置为重定向
		result = Constant.REDIRECT;
		return request.getContextPath()+"/admin/category/list.jsp";
	}
	
	public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取后台商品分类展示页面传过来的参数
		String cid = request.getParameter("cid");
		//调用业务层方法，根据商品分类cid查找商品分类
		AdminCategoryService acs = new AdminCategoryServiceImpl();
		Category cg = acs.edit(cid);
		//将cg设置到域对象中
		request.setAttribute("cg", cg);
		//设置为请求转发
		result = Constant.FORWARD;
		return "admin/category/edit.jsp";
	}
}
