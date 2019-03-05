package com.yucong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yucong.service.AnnoAspectService;

@RestController
@RequestMapping("aspect")
public class AspectController {

	@Autowired
	private AnnoAspectService aspectService;

	@RequestMapping("test1")
	public String test1(String id, String name) {
		System.out.println(id + ":" + name);
		aspectService.test1(id, name);
		return "aspect。。。";
	}
}
