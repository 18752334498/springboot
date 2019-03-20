package com.yucong.event;

import org.springframework.stereotype.Component;

@Component
public class NewEvent {

	public void myEvent() {
		System.out.println("===========从自定义监听器获取bean（newEvent）===========");
	}

}
