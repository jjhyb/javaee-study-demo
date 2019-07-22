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
 * Servlet implementation class DownloadServlet3
 */
public class DownloadServlet3 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取客户端提交过来的参数
		String filename = request.getParameter("filename");
		//通过ISO-8859-1还原参数编码字节数组,并通过UTF-8进行解码成字符串
		filename = new String(filename.getBytes("ISO-8859-1"),"UTF-8");
		//判断filename是否包含".txt"后缀名
		if(filename.endsWith(".txt")){
			response.setContentType("text/html;charset=UTF-8");
		}
		
		//获取项目目录下的静态资源的相对目录
		String path = "download/"+filename;
		//获取请求头为"User-Agent"的浏览器内核数据
		String header = request.getHeader("User-Agent");
		//如果是火狐浏览器内核
		if(header.contains("Firefox")){
			//则用Base64解析方式对文件名进行编码
			filename = Base64.getEncoder().encodeToString(filename.getBytes("UTF-8"));
		}else {
			//其他浏览器直接用URLEncoder.encode对文件名进行编码
			filename = URLEncoder.encode(filename, "UTF-8");
		}
		
		//给客户端发送响应头用于提示下载弹框
		response.setHeader("Content-Disposition","attachment; filename="+filename);
		
		//获取ServletContext对象
		ServletContext sc = getServletContext();
		//通过ServletContext对象关联path路径获取静态资源的字节输入流
		InputStream in = sc.getResourceAsStream(path);
		ServletOutputStream out = response.getOutputStream();
		int len = 0;
		byte[] buffer = new byte[8192];
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
