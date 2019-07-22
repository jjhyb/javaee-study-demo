package com.yibo.web.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yibo.bean.Order;
import com.yibo.bean.PageBean;
import com.yibo.constant.Constant;
import com.yibo.service.admin.AdminOrderService;
import com.yibo.service.admin.impl.AdminOrderServiceImpl;
import com.yibo.web.servlet.BaseServlet;

/**
 * 后台页面处理订单状态的servlet
 */
public class AdminOrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	public String page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取后台订单管理页面发过来的参数
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		
		/*//如果state没有获取到值，那么会抛数组转换异常
		Integer state = Integer.parseInt(request.getParameter("state"));*/
		String state = request.getParameter("state");
		//调用业务层方法处理订单状态的分页显示
		AdminOrderService aos = new AdminOrderServiceImpl();
		PageBean<Order> pb = aos.page(curPage,state);
		//将pb存到域对象中
		request.setAttribute("pb", pb);
		//设置为请求转发
		result = Constant.FORWARD;
		return "/admin/order/list.jsp";
	}

	public void findOrderByOid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取后台订单管理页面发过来的参数
		String oid = request.getParameter("oid");
		//调用业务层方法获查询订单
		AdminOrderService aos = new AdminOrderServiceImpl();
		String json = aos.findOrderitemByOid(oid);
		//设置response回写数据编码问题
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	}
	
	public String updateState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取后台订单管理页面发过来的订单oid
		String oid = request.getParameter("oid");
		//获取后台订单管理页面发过来的订单状态state,只用于订单状态更新失败后的原路返回
		String state = request.getParameter("state");
		//调用业务层方法获查询订单发货状态
		AdminOrderService aos = new AdminOrderServiceImpl();
		//定义Order对象，用于接收修改发货状态的订单对象
		Order order = aos.updateState(oid);
		//设置重定向跳转
		result = Constant.REDIRECT;
		if( order!= null){
			//表示订单状态修改成功
			return request.getContextPath()+"/adminOrder?method=page&curPage=1&state="+order.getState();
		}else {
			//表示订单状态修改失败
			//设置重定向路径跳转回原页面
			return request.getContextPath()+"/adminOrder?method=page&curPage=1&state="+state;
		}
	}
}
