package com.yucong.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 在Spring中，监听某一个事件有两种方式 ，继承ApplicationListener接口 ，使用注解@EventListener来监听一个事件
 * 
 */
@Component // 取消自定义监听
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {

	// 不加异步，所用的线程都是主线程（http-nio-8888-exec-1）
	@Async("threadPoolA")
	@Override
	public void onApplicationEvent(ApplicationEvent event) {

		System.out.println("============进入监听器============:  " + event.getClass());
		// 判断事件为MyEvent时候执行
		if (event instanceof MyEvent) {
			MyEvent evt = (MyEvent) event;
			evt.myevent();
		}
	}

}
