package com.yucong.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	/**
	 * 全局异常捕捉，可以返回字符串或者页面
	 * 
	 * @param request
	 * @param e
	 * @return
	 */

//	@ResponseBody
	@ExceptionHandler(Exception.class)
	public String ff(HttpServletRequest request, Exception e) {

		e.printStackTrace();

//		return "服务器异常，请稍后再试！";
		return "error";
	}

}
