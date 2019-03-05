package com.yucong.event;

import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	private String msg;

	public MyEvent(String msg) {
		super(msg);
		this.msg = msg;
	}

	public void myevent() {
		System.out.println(msg + "\t" + "自定义事件的线程： " + Thread.currentThread().getName());
	}

}
