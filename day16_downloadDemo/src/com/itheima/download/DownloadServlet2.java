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

/**
 * Servlet implementation class DownloadServlet2
 */
public class DownloadServlet2 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取客户端的参数
		String filename = request.getParameter("filename");
		//将字符串filename通过ISO-8859-1字符集还原成原来的字节编码
		//并将还原后的字节数组通过UTF-8进行解码
		filename = new String(filename.getBytes("ISO-8859-1"),"UTF-8");
		//判断filename字符串是否是以.txt后缀结尾
		if(filename.endsWith(".txt")){
			//如果是以.txt结尾，则设置其编码方式为UTF-8，并告诉给客户端用UTF-8进行解码
			response.setContentType("text/html;charset=UTF-8");
		}
		
		
		
		
		//获取静态资源的相对路径
		String path = "download/"+filename;
		System.out.println(filename);
		
		//获取请求头为"User-Agent"的浏览器内核信息
		//根据不同的浏览器内核进行不同的下载提示框中文乱码进行解析
		String header = request.getHeader("User-Agent");
		//如果是火狐浏览器内核，进行Base64方式进行编码，以便火狐解析
		if(header.contains("Firefox")){
			filename = Base64.getEncoder().encodeToString(filename.getBytes("UTF-8"));
		}else {//其他浏览器则进行其他方式处理
			filename = URLEncoder.encode(filename, "UTF-8");
		}
		//设置响应头，给用户下载提示框
		response.setHeader("Content-Disposition","attachment; filename="+filename);
		//获取ServletContext对象
		ServletContext sc = getServletContext();
		//通过文件的相对关联ServletContext获取静态资源的字节输入流
		InputStream in = sc.getResourceAsStream(path);
		System.out.println(in);
		//通过response获取字节输出流
		ServletOutputStream out = response.getOutputStream();
		byte[] buffer = new byte[8192];
		int len = 0;
		while((len = in.read(buffer)) != -1){
			out.write(buffer, 0, len);
		}
		out.close();
		in.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
