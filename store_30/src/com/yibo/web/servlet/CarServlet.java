package com.yibo.web.servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSpinnerUI;

import com.yibo.bean.Car;
import com.yibo.bean.Item;
import com.yibo.constant.Constant;
import com.yibo.service.CarService;
import com.yibo.service.impl.CarServiceImpl;

/**
 * Servlet implementation class CarServlet
 */
public class CarServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 向购物车添加商品
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add2Car(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取网页传过来的添加购物车的数据
		String pid = request.getParameter("pid");
		int count = Integer.parseInt(request.getParameter("count"));
		//调用业务层方法
		CarService cs = new CarServiceImpl();
		//获取购物车项Item
		Item item = cs.add2Car(pid,count);
		//从session中获取购物车car
		HttpSession session = request.getSession();
		Car car = (Car) session.getAttribute("car");
		if(car == null){
			//如果购物车为null,则创建购物车
			car = new Car();
		}
		//将数据添加进购物车
		//如果该购物车包含该商品
		if(car.getMap().containsKey(item.getPro().getPid())){
			//从购物车中获取该商品表单项
			Item item2 = car.getMap().get(item.getPro().getPid());
			//将要添加的商品数量和原商品数量相加后设置进该商品项的数量
			item2.setCount(item2.getCount()+count);
			car.getMap().put(pid, item2);
		}else{
			//将商品添加进购物车
			car.getMap().put(pid, item);
		}
		
		//将购物车添加进session中
		session.setAttribute("car", car);
		result = Constant.REDIRECT;
		return request.getContextPath()+"/jsp/cart.jsp";
	}
	
	/**
	 * 删除购物车里面的商品
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取网页端传过来的商品pid;
		String pid = request.getParameter("pid");
		//获取session,并获取到购物车
		HttpSession session = request.getSession();
		Car car = (Car) session.getAttribute("car");
		//多做一步判断，防止出现空指针异常
		if(car != null){
			//通过商品pid将商品从car中移除
			Item item = car.getMap().remove(pid);
		}
		//将删除了商品的购物车在添加进session中
		session.setAttribute("car", car);
		//设置为重定向
		result = Constant.REDIRECT;
		return request.getContextPath()+"/jsp/cart.jsp";
	}
	
	/**
	 * 清空购物车
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取session,然后将购物车移除
		request.getSession().removeAttribute("car");
		//设置重定向并跳转到购物车页面
		result = Constant.REDIRECT;
		return request.getContextPath()+"/jsp/cart.jsp";
	}
	
	public String addCountCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取网页传过来的添加购物车的数据
		String pid = request.getParameter("pid");
		int count = Integer.parseInt(request.getParameter("count"));
		
		//从session中获取数据库
		HttpSession session = request.getSession();
		//从session中获取购物车
		Car car = (Car) session.getAttribute("car");
		//根据pid从Map集合中获取对应的购物车项item
		Item item = car.getMap().get(pid);
		//将客户端购物车传过来的商品购买数量设置到购物车项item中
		item.setCount(count);
		//在将设置后的item以pid为key添加进购物车
		car.getMap().put(pid, item);
		//然后将购物车设置到session中
		session.setAttribute("car", car);
		return request.getContextPath()+"/jsp/cart.jsp";
	}
}
