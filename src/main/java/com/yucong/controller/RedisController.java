package com.yucong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yucong.service.RedisService;

@RestController
@RequestMapping("redis")
public class RedisController {

	@Autowired
	private RedisService redisService;

	@RequestMapping("test1")
	public String test1() {
		redisService.set();
		return "set";
	}

	@RequestMapping("test2")
	public String test2() {
		redisService.get();
		return "get";
	}
}
