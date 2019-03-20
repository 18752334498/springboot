package com.yucong.event;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * <span>容器启动的时候可以注册此监听</span>
 * <span>ApplicationReadyEvent：在所有bean初始化完毕，所有回调处理完成，系统准备处理服务请求时触发；</span>
 */
public class NewApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		System.out.println("进入=================NewApplicationListener，此时，容器初始化完成==================");
		ConfigurableApplicationContext context = event.getApplicationContext();
		NewEvent newEvent = (NewEvent) context.getBean("newEvent");
		newEvent.myEvent();
	}

}
