package com.yucong.config;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yucong.model.User;

@Configuration
public class BeanConfig {

	@Bean
	public User getUsers() {
		System.out.println("==========================自定义bean初始化输出============================");
		return new User(1, "admin", 222);
	}

	@Bean
	public FilterRegistrationBean<Filter> myFilter() {
		FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
		filter.setFilter(new MyFilter());
		filter.setOrder(1);
		filter.addUrlPatterns("/*");
		return filter;
	}

}
