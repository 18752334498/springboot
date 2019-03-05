package com.yucong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yucong.model.Phone;
import com.yucong.model.User;
import com.yucong.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 调用test1，@Async @Transactional 注解都是用的情况，遇到异常会回滚
	 */
	@RequestMapping("test1")
	public void test1() {

		System.out.println("主线程开始。。。		" + Thread.currentThread().getName());
		userService.test1(1);
		System.out.println("主线程结束。。。");
	}

	@RequestMapping("test3")
	public void test3() {

		System.out.println("主线程开始。。。	" + Thread.currentThread().getName());
		userService.test3(3);
		System.out.println("主线程结束。。。");
	}

	@RequestMapping("insertBatch")
	public String insertBatch() {
		userService.insertBatch();
		return "fffffff";
	}

	@RequestMapping("test4")
	public User test4(int id) {
		User user = userService.testRedis(id);
		return user;
	}

	@RequestMapping("test5")
	public User test5(int id) {
		User user = userService.testAnnotationRedis(id);
		return user;
	}

	@RequestMapping("test6")
	public void test6() {
		userService.test4(new Phone(111, "小米", 333, 111));
	}

}
