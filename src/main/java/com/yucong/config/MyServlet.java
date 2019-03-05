package com.yucong.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/myServlet/*", loadOnStartup = 1)
public class MyServlet implements Servlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("自定义servlet。。。。init。。。。");
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=utf-8");
		System.out.println("自定义servlet。。。。service。。。。");
		PrintWriter out = res.getWriter();
		out.print("<p style='color:red'>servlet直接打印输出。。。。</p>");

	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroy() {
		System.out.println("自定义servlet。。。。destory。。。。");

	}

}
