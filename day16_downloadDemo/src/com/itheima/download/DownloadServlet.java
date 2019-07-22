package com.itheima.download;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Base64;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;

/**
 * 自定义的servlet，用来完成下载操作
 */
public class DownloadServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取客户端传过来的参数
		String filename = request.getParameter("username");
		//将获取到的乱码字符串通过ISO-8859-1字符集还原成字节数组，
		//并通过UTF-8字符集重新解码成字符串
		filename = new String(filename.getBytes("ISO-8859-1"),"UTF-8");
		//判断字符串末尾是否是以".txt"格式结尾
		if(filename.contains(".txt")){
			//如果是，则该文件为文本文件，用setContentType()解决中文响应的乱码问题
			response.setContentType("text/html;charset=UTF-8");
		}
		//获取此文件对应项目下的文件路径
		String path = "download/"+filename;
		System.out.println(filename);
		//获取请求头信息，并判断该信息是否包含特定的浏览器内核信息，并作出相对应的浏览器解析方式
		String header = request.getHeader("User-Agent");
		//如果是火狐浏览器
		if(header.contains("Firefox")){
			//则filename用Base64方式进行编码
			filename = Base64.getEncoder().encodeToString(filename.getBytes("UTF-8"));
		}else {
			//如果是其他浏览器，则filename用URLEncoder.encode进行编码
			filename = URLEncoder.encode(filename,"utf-8");
		}
		//添加一个响应头，让客户端---->让客户端弹出下载提示框
		response.setHeader("Content-Disposition","attachment; filename="+filename);
		
		//获得ServletContext对象
		ServletContext sc = getServletContext();
		//通过ServletContext对象，将path路径下的静态资源转换成字节输入流
		InputStream input = sc.getResourceAsStream(path);
		//通过response对象获取字节输出流
		ServletOutputStream out = response.getOutputStream();
		byte[] buffer = new byte[8192];
		int len = 0;
		while((len = input.read(buffer))!=-1){
			out.write(buffer, 0, len);
		}
		out.close();
		input.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
}
