package com.yibo.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.yibo.bean.Car;
import com.yibo.bean.Item;
import com.yibo.bean.Order;
import com.yibo.bean.PageBean;
import com.yibo.bean.User;
import com.yibo.constant.Constant;
import com.yibo.service.OrderService;
import com.yibo.service.impl.OrderServiceImpl;
import com.yibo.utils.PaymentUtil;

/**
 * 处理订单的servlet
 */
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 生成订单记录，持久化入数据库，并且清除订单内的购物车商品信息
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String saveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取客户端传过来的数据
		String[] pids = request.getParameterValues("pid");
		//通过session获取到购物车
		HttpSession session = request.getSession();
		Car car = (Car) session.getAttribute("car");
		//获取用户信息
		User user = (User) session.getAttribute("user");
		//获取到购物车的购物车项Map集合
		Map<String, Item> map = car.getMap();
		
		//创建一个map集合，用于在用户没有完全提交购物车里面的商品信息情况下的存储商品pid和商品item
		Map<String,Item> mapitem = new HashMap<>();
		
		//创建业务层对象
		OrderService os = new OrderServiceImpl();
		//判断购物车里面的商品是否被全部提交
		if(pids.length == map.size()){
			//购物车里面的商品全部被提交
			//调用业务层方法
			//将订单返回，然后去往订单详情页面付款
			Order order = os.saveOrder(user,car);
			//将订单存放到域对象中
			session.setAttribute("order", order);
			//接着清空购物车
			session.removeAttribute("car");
		}else {
			//购物车里面的商品只被提交了一部分
			//那么遍历从购物车获取过来的购物车项商品pid数组
			for (String pid : pids) {
				//然后用获取的pid去比对购物车的购物项集合的key(商品pid),
				for (Entry<String, Item> en : car.getMap().entrySet()) {
					//如果相同则获取购物车项Map集合的value
					if(pid.equals(en.getKey())){
						//如果相同则将对应的map集合的value获取出来
						Item item = en.getValue();
						//然后又从新以pid为key，以获取到的item为value存到新的集合中
						mapitem.put(pid, item);
					}
				}
			}
			//重新创建一个购物车car，用于存储用户没有完全提交购物车里面的数据,将此购物车传到业务层处理订单
			Car carorder = new Car();
			//将用户提交的要处理的购物车项集合封装到这个临时购物车中
			carorder.setMap(mapitem);
			//然后调用业务层方法处理订单
			Order order = os.saveOrder(user,carorder);
			//将订单存放到域对象中
			session.setAttribute("order", order);
			//然后清空购物车里面的已经提交的生成订单的商品
			for (String pid : pids) {
				//通过获取到的商品pid删除购物车里面的购物车项
				car.getMap().remove(pid);
			}
		}
		//设置页面跳转为重定向
		result = Constant.REDIRECT;
		return request.getContextPath()+"/jsp/order_info.jsp";
	}
	
	/**
	 * 订单分页
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取客户端传过来的数据
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		//获取域对象session
		HttpSession session = request.getSession();
		//通过session获取到用户信息
		User user = (User) session.getAttribute("user");
		//调用业务层方法查询全部订单
		OrderService os = new OrderServiceImpl();
		
		PageBean<Order> pb = os.findOrderPage(curPage,user);
		//数据存到域对象request中
		request.setAttribute("pb", pb);
		
		//设置跳转为请求转发
		result = Constant.FORWARD;
		return "/jsp/order_list.jsp";
	}
	
	/**
	 * 根据oid查找整个订单
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByOid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取客户端数据
		String oid = request.getParameter("oid");
		//获取到session,在获取到登录用户对象
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		//调用业务层方法处理付款业务
		OrderService os = new OrderServiceImpl();
		Order order = os.findByOid(oid,user);
		//将订单order设置到域对象中
		session.setAttribute("order", order);
		//重定向跳转到订单详情页面
		result = Constant.REDIRECT;
		return request.getContextPath()+"/jsp/order_info.jsp";
	}
	
	/**
	 * 订单支付功能
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取支付页面传递过来的数据
		String oid = request.getParameter("oid");
		//如果调用request.getParameterMap()方法的话可以后面一次性封装name,address,telephone三个属性
		Map<String, String[]> map = request.getParameterMap();
		/*String address = request.getParameter("address");
		String name = request.getParameter("name");
		String telephone = request.getParameter("telephone");*/
		
		//获取用户选择支付的银行
		String pd_FrpId = request.getParameter("pd_FrpId");
		//获取session，进行获取用户信息
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		//调用业务层方法根据订单oid以及用户查询订单详情
		OrderService os = new OrderServiceImpl();
		Order order = os.findByOid(oid, user);
		try {
			//将name,address,telephone等信息更新到order中
			BeanUtils.populate(order, map);
			//调用业务层方法将订单详情更新到数据库order表中
			os.updateOrder(order);
			//支付操作，组装用户跳转支付页面的url地址
			String url = PaymentUtil.buildUrl(pd_FrpId, oid, "0.01");
			//重定向发送请求到易宝支付公司
			response.sendRedirect(url);
			return null;
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			request.setAttribute("msg", "转账失败");
		}
		
		return "/jsp/msg.jsp";
	}
	
	/**
	 * 处理支付结果的方法
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String callback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取第三方支付公司支付完成之后发过来的请求中的参数
		String p1_MerId = request.getParameter("p1_MerId");
		String r0_Cmd = request.getParameter("r0_Cmd");
		String r1_Code = request.getParameter("r1_Code");
		String r2_TrxId = request.getParameter("r2_TrxId");
		String r3_Amt = request.getParameter("r3_Amt");
		String r4_Cur = request.getParameter("r4_Cur");
		String r5_Pid = request.getParameter("r5_Pid");
		String r6_Order = request.getParameter("r6_Order");
		String r7_Uid = request.getParameter("r7_Uid");
		String r8_MP = request.getParameter("r8_MP");
		String r9_BType = request.getParameter("r9_BType");
		String rb_BankId = request.getParameter("rb_BankId");
		String ro_BankOrderId = request.getParameter("ro_BankOrderId");
		String rp_PayDate = request.getParameter("rp_PayDate");
		String rq_CardNo = request.getParameter("rq_CardNo");
		String ru_Trxtime = request.getParameter("ru_Trxtime");
		// 身份校验 --- 判断是不是支付公司通知你
		String hmac = request.getParameter("hmac");//是第三方支付公司携带过来的hmac
		//读取商户的密钥
		String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
				"keyValue");

		// 自己对上面数据进行加密 --- 比较支付公司发过来hamc
		//通过这次校验，就能验证第三方公司发过来的请求是否真实可靠
		boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
				r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
				r8_MP, r9_BType, keyValue);
		if (isValid) {
			// 响应数据有效，确实是易付公司发过来的请求
			if (r9_BType.equals("1")) {
				// 浏览器重定向
				System.out.println("111");
				request.setAttribute("msg", "您的订单号为:"+r6_Order+",金额为:"+r3_Amt+"已经支付成功,等待发货~~");
				
			} else if (r9_BType.equals("2")) {
				// 服务器点对点 --- 支付公司通知你
				System.out.println("付款成功！222");
				// 修改订单状态 为已付款
				// 回复支付公司
				response.getWriter().print("success");
			}
			
			//修改订单状态，要将订单的state进行修改
			OrderService service = new OrderServiceImpl();
			Order order = service.findOrderByOid(r6_Order);
			order.setState(Constant.PAYED);
			service.updateOrder(order);
		} else {
			// 数据无效，请求已经被篡改，不是易付公司发过来
			System.out.println("数据被篡改！");
			
		}
		result = Constant.FORWARD;
		return "/jsp/msg.jsp";
	}

}
