package com.yucong.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

	@Autowired
	private ApplicationEventPublisher publisher;

	@Transactional
	public void order(int a) {
		System.out.println("参数order： " + a + "\t" + "线程： " + Thread.currentThread().getName());

		/**
		 * 这里通过注解的方式完成事件驱动, 参数可以是string,int,Phone,User,但是map,list却不可以
		 */
		publisher.publishEvent(a);

		/**
		 * 这里通过自定义的事件完成事件驱动, 传入的参数是自定义的事件
		 */
		publisher.publishEvent(new MyEvent("ghtyhythtyhtyhtyhtyh"));

	}

}
