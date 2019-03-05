package com.yucong.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yucong.model.Person;
import com.yucong.model.User;

@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping("form")
	public String login(HttpServletRequest request) {
		request.getSession().setAttribute("user", new Person(1, "admin", String.valueOf(new Random().nextInt(999999))));
		return "login";
	}

	@ResponseBody
	@RequestMapping(value = "validate", method = RequestMethod.POST)
	public User validate(User user, HttpServletRequest request) {
		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("password"));
		System.out.println(request.getParameter("sex"));
		System.out.println(request.getParameter("height"));
		System.out.println(user);
		return user;
	}

	@ResponseBody
	@RequestMapping("test")
	public User test() {
		User user = new User(11, "admin", 123456);
		return user;
	}

}
