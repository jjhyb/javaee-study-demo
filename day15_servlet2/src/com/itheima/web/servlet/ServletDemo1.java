package com.itheima.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Users;
import com.itheima.service.Service;
import com.itheima.service.impl.ServiceImpl;

public class ServletDemo1 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Service service = new ServiceImpl();
		Users user = service.doLogin(username,password);
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		if(user == null){
			out.write("用户名或密码错误，请重新登录!");
		}else {
			out.write("欢迎你:"+user.getUsername());
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
	

}
