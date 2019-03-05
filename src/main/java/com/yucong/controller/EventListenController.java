package com.yucong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yucong.event.OrderService;

@RestController
@RequestMapping("event")
public class EventListenController {

	@Autowired
	private OrderService orderService;

	@RequestMapping("test")
	public String test() {

		orderService.order(345);
		return "dddddddddddddd";
	}

}
