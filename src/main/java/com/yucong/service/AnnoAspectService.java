package com.yucong.service;

import org.springframework.stereotype.Service;

import com.yucong.annotation.DoSomething;

@Service
public class AnnoAspectService {

	@DoSomething(key = "#id", cacheName = "user333", needLog = true)
	public String test1(String id, String name) {
		System.out.println("我是service里的业务逻辑。。。");
		return "我是service返回值。。。";
	}
}
