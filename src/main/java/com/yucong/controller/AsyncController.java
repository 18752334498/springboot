package com.yucong.controller;

import java.util.concurrent.Callable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

@RestController
@RequestMapping("async")
public class AsyncController {

	/**
	 * mvc 异步
	 * 
	 * @return
	 */
	@RequestMapping("test")
	public Callable<String> test() {
		System.out.println("主线程： " + Thread.currentThread().getName());
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(5000);
				System.out.println("异步线程： " + Thread.currentThread().getName());
				return "异步数据。。。";
			}
		};
		System.out.println("end....");
		return callable;
	}

	/**
	 * 超时设置 WebAsyncTask
	 * 
	 * @return
	 */
	@RequestMapping("timeout")
	public WebAsyncTask<String> test1() {
		System.out.println("主线程： " + Thread.currentThread().getName());
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println(Thread.currentThread().getName() + " 进入call方法");
				Thread.sleep(10000);
				return "success.....";
			}
		};
		System.out.println("end.......");
		return new WebAsyncTask<>(8000, callable);
	}

}
