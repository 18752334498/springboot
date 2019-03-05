package com.yucong.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * 配置异步线程池
 * 
 * @author Administrator
 *
 */
@Component
public class ThreadAsyncConfigurer implements AsyncConfigurer {

	@Bean(name = "threadPoolA")
	public Executor ycExecutorA() {
		ThreadPoolTaskExecutor t = new ThreadPoolTaskExecutor();
		t.setCorePoolSize(1);// 设置核心线程数
		t.setMaxPoolSize(10);// 设置最大线程数
		t.setQueueCapacity(10);// 线程池所使用的缓冲队列
		t.setKeepAliveSeconds(60);//
		t.setWaitForTasksToCompleteOnShutdown(true);// 等待任务在关机时完成--表明等待所有线程执行完
		t.setAwaitTerminationSeconds(60);// 等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止
		t.setThreadNamePrefix("ycAsync-A-");// 线程名称前缀
		t.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		t.initialize();// 初始化线程
		return t;
	}

	@Bean(name = "threadPoolB")
	public Executor ycExecutorB() {
		ThreadPoolTaskExecutor t = new ThreadPoolTaskExecutor();
		t.setCorePoolSize(1);
		t.setMaxPoolSize(10);
		t.setQueueCapacity(10);
		t.setKeepAliveSeconds(60);
		t.setWaitForTasksToCompleteOnShutdown(true);
		t.setAwaitTerminationSeconds(60);
		t.setThreadNamePrefix("ycAsync-B-");
		t.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		t.initialize();
		return t;
	}

}
