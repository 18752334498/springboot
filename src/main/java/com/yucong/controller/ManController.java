package com.yucong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yucong.entity.Man;
import com.yucong.service.ManService;

@RestController
@RequestMapping("/man")
public class ManController {

	@Autowired
	private ManService manService;

	@RequestMapping("select") // required = false 如果参数没值则会赋值null
	public String selectById(@RequestParam(name = "id", required = false) Integer id) {
		id = id == null ? 0 : id;
		System.out.println("id: " + id);
		List<Man> list = manService.selectById(id);
		list.forEach(System.out::println);
		return JSON.toJSONString(list);
	}

	@RequestMapping("wife") // required = false 如果参数没值则会赋值null
	public String selectManAndWifeById(@RequestParam(name = "id", required = false) Integer id) {
		id = id == null ? 0 : id;
		System.out.println("id: " + id);
		List<Man> list = manService.selectManAndWifeById(id);
		System.out.println(list.get(0).getName());
		return JSON.toJSONString(list);
	}

	@RequestMapping("wife1") // required = false 如果参数没值则会赋值null
	public String selectManAndWifeById1(@RequestParam(name = "id", required = false) Integer id) {
		id = id == null ? 0 : id;
		System.out.println("id: " + id);
		List<Man> list = manService.selectManAndWifeById1(id);
//		System.out.println(list.get(0).getName());		//只查man
//		System.out.println(list.get(0).getWife());		//加载wife
//		list.forEach(System.out::println);				//加载wife
		return JSON.toJSONString(list); 				//加载wife
	}

	@RequestMapping("address")
	public String selectAddressOfMan(@RequestParam(name = "id", required = false) Integer id) {
		id = id == null ? 0 : id;
		System.out.println("id: " + id);
		List<Man> list = manService.selectAddressOfMan(id);
		list.forEach(System.out::println);
		return JSON.toJSONString(list);
	}

	@RequestMapping("address1")
	public String selectAddressOfMan1(@RequestParam(name = "id", required = false) Integer id) {
		id = id == null ? 0 : id;
		System.out.println("id: " + id);
		List<Man> list = manService.selectAddressOfMan1(id);
		System.out.println(list.get(0).getName());
		System.out.println(list.get(0).getAddresses().get(0));
//		list.forEach(System.out::println);
		return "address1";
	}

}
