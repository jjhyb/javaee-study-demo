package com.itheima.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.dsna.util.images.ValidateCode;

/**
 * Servlet implementation class VcodeServlet
 */
public class VcodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//初始化验证码对象
		ValidateCode vdc = new ValidateCode(100, 40, 4, 8);
		//获得验证码字符串表现形式
		String code = vdc.getCode();
		//获取session对象
		HttpSession session = request.getSession();
		//将验证码字符串设置到session中
		session.setAttribute("code", code);
		vdc.write(response.getOutputStream());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
