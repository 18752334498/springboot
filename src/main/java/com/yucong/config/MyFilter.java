package com.yucong.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class MyFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("自定义 MyFilter   。。。。  init");

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;

		if (request.getRequestURI().contains("favicon.ico")) {
			System.out.println("过滤 favicon.ico ..."); // 页面初始化时会发生一次favicon.ico请求
			return;
		}
		System.out.println("RequestURL: " + request.getRequestURL() + "\t\tRequestURI: " + request.getRequestURI());

		chain.doFilter(req, resp);
	}

	@Override
	public void destroy() {

		System.out.println("自定义 MyFilter   。。。。  destroy");
	}

}
