package com.yucong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.yucong.event.NewApplicationListener;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.yucong.mapper")
@ServletComponentScan
@EnableTransactionManagement
@EnableAsync
public class SpringBootApp {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SpringBootApp.class);
		app.addListeners(new NewApplicationListener());
		app.run(args);

//		new SpringApplicationBuilder(SpringBootApp.class).listeners(new NewApplicationListener()).run(args);
	}
}
