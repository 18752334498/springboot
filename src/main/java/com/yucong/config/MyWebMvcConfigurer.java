package com.yucong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

	/**
	 * 在 LoginController 访问 http://localhost:8888/login/form 也可以跳转到登陆页面
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/jack").setViewName("login");
		WebMvcConfigurer.super.addViewControllers(registry);
	}

	/**
	 * 配置springmvc异步线程池
	 */
	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		configurer.setDefaultTimeout(60 * 1000L);
		configurer.registerCallableInterceptors(timeoutInterceptor());
		configurer.setTaskExecutor(threadPoolTaskExecutor());
	}

	@Bean
	public TimeoutCallableProcessingInterceptor timeoutInterceptor() {
		return new TimeoutCallableProcessingInterceptor();
	}

	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor t = new ThreadPoolTaskExecutor();
		t.setCorePoolSize(2);
		t.setMaxPoolSize(5);
		t.setKeepAliveSeconds(60);
		t.setThreadNamePrefix("YC_");
		return t;
	}

}
