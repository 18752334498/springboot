package com.yucong.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 在Spring中，监听某一个事件有两种方式 ，继承ApplicationListener接口 ，使用注解@EventListener来监听一个事件
 * 
 */
@Service
public class EmailService {

	public void email(String string) {
		System.out.println("==================: " + string);
	}

	// 异步线程
	@Async("threadPoolB")
	@EventListener
	public void send111(int a) {
		System.out.println("参数111： " + a + "\t" + "线程： " + Thread.currentThread().getName());
		email("111");
	}

	// 主线程
	@EventListener
	public void send222(int a) {
		System.out.println("参数222： " + a + "\t" + "线程： " + Thread.currentThread().getName());
		email("222");
	}

}
