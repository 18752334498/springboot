package com.yucong.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yucong.service.PhoneService;

@RestController
@RequestMapping("phone")
public class PhoneController {

	@Autowired
	private PhoneService phoneService;

	@RequestMapping("buy")
	public String buy(@RequestParam("id") long id, @RequestParam("count") int count) {
		String string = phoneService.buy(id, count);
		System.out.println(string);
		return string;
	}

	@RequestMapping("buyDirect")
	public String buyDirect(@RequestParam("id") long id, @RequestParam("count") int count) {
		String string = phoneService.buyDirect(id, count);
		System.out.println(string);
		return string;
	}

	@RequestMapping("insert")
	public void insertBatch() {
		phoneService.insertBatch();
	}

	@RequestMapping("page")
	public void selectWithPage() {
		System.out.println(phoneService.selectWithPage());
	}

	@RequestMapping("get")
	public String selectById(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		System.out.println(request.getParameter("page"));
		System.out.println(request.getParameter("rows"));
		if (!StringUtils.isEmpty(request.getParameter("page")) && !StringUtils.isEmpty(request.getParameter("rows"))) {
			int a = Integer.valueOf(request.getParameter("page"));
			int b = Integer.valueOf(request.getParameter("rows"));
			map.put("pageIndex", (a - 1) * b);
			map.put("pageRows", b);
		} else {
			map.put("pageIndex", 0);
			map.put("pageRows", 15);
		}

		System.out.println(map);

		List<Map<String, Object>> list = phoneService.selectByPagination(map);
		int total = phoneService.selectAll();

		Map<String, Object> result = new HashMap<>();
		result.put("rows", list);
		result.put("total", total);
		return JSON.toJSONString(result);
	}
}
