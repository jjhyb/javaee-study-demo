package com.itheima.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.service.impl.ProductServiceImpl;
import com.itheima.utils.DateUtil;
import com.mchange.v2.beans.BeansUtils;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决中文乱码问题
		request.setCharacterEncoding("UTF-8");
		//获取method请求采参数
		String methodstr = request.getParameter("method");
		//客户端通过地址栏传值，传过来的值和要调用的那个方法名一样
		//知道方法名，用反射做，因为参数都一样，方法名不一样
		Class<? extends ProductServlet> clazz = this.getClass();
		try {
			//根据方法名获取method对象
			Method method = clazz.getMethod(methodstr, HttpServletRequest.class,HttpServletResponse.class);
			//调用method方法
			method.invoke(this, request,response);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		} 
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * 查询所有商品的方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用业务逻辑
		ProductService ps = new ProductServiceImpl();
		List<Product> list = ps.findAll();
		//将查询回来的结果集设置到域对象中
		request.setAttribute("list", list);
		//请求转发到list.jsp页面
		request.getRequestDispatcher("/list.jsp").forward(request, response);;
	}
	
	/**
	 * 添加商品的方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建一个javaBean对象，用于封装表单数据
		Product product = new Product();
		try {
			//通过BeanUtils将表单数据封装到javaBean中
			BeanUtils.populate(product, request.getParameterMap());
			//调用UUID获得商品唯一pid
			String pid = UUID.randomUUID().toString();
			//将pid设置到javaBean中
			product.setPid(pid);
			//获取时间戳，并将时间戳封装到javaBean中
			product.setPdate(DateUtil.getCurrentTimeStamp());
			//调用业务逻辑
			ProductService ps = new ProductServiceImpl();
			//将商品存储到数据库，并返回boolean
			boolean flag = ps.addProduct(product);
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			if(flag){
				//如果添加成功,则调用findAll方法，查询出全部商品，并跳转到list.jsp页面
				findAll(request,response);
			}else {
				writer.write("添加商品失败,3秒后跳转至首页!");
				response.setHeader("refresh", "3;url="+request.getContextPath()+"/index.jsp");
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过商品pid查询单个商品记录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findProductById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		//获取客户端传过来的要查询的商品pid
		String pid = request.getParameter("pid");
		//调用业务逻辑,根据pid查询商品信息
		ProductService ps = new ProductServiceImpl();
		Product product = ps.findProductById(pid);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		if(product != null){
			//如果查询成功，则将商品信息封装到域对象中
			request.setAttribute("p", product);
			//请求转发跳转到产品更新页面edit.jsp
			request.getRequestDispatcher("edit.jsp").forward(request, response);;
		}else {
			writer.write("获取数据失败！");
			response.setHeader("refresh", "2;url="+request.getContextPath()+"/index.jsp");
		}
	} 
	
	/**
	 * 更新商品信息方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		//创建一个javaBean对象，用于封装表单数据
		Product product = new Product();
		try {
			//通过BeanUtils将表单数据封装到javaBean中
			BeanUtils.populate(product,request.getParameterMap());
			//获取时间戳，并将时间戳作为商品更新时间戳封装到javaBean中
			product.setPdate(DateUtil.getCurrentTimeStamp());
			//调用业务逻辑
			ProductService ps = new ProductServiceImpl();
			boolean flag = ps.updateProduct(product);
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			if(flag){
				//如果更新成功，则调用findAll方法，查询出全部商品，并跳转到list.jsp页面
				findAll(request,response);
			}else {
				writer.write("更改数据失败！");
				response.setHeader("refresh", "2;url="+request.getContextPath()+"/index.jsp");
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据商品pid删除单个商品的方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取客户端传过来的要删除商品的pid
		String pid = request.getParameter("pid");
		//调用业务逻辑
		ProductService ps = new ProductServiceImpl();
		boolean flag = ps.deleteProduct(pid);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		if(flag){
			//如果删除成功,则调用findAll方法，查询出全部商品，并跳转到list.jsp页面
			findAll(request,response);
		}else {
			writer.write("删除数据失败！");
			response.setHeader("refresh", "2;url="+request.getContextPath()+"/index.jsp");
		}
	}
	
}
