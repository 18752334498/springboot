package com.yucong.provider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class Provider {

	@Autowired
	private AmqpTemplate rabbitTemplate;

//	@Scheduled(fixedRate = 5000)
	public void send1() {
		this.rabbitTemplate.convertAndSend("topic.exchange.yucong", "topic.routeKey.1", "定时发送的消息。。。");
	}

//	@Scheduled(fixedRate = 5000)
	public void send2() {
		this.rabbitTemplate.convertAndSend("direct.exchange.aa", "direct.routeKey.aa", "定时发送的消息。。。");
	}

//	@Scheduled(fixedRate = 5000)
	public void send3() {
		this.rabbitTemplate.convertAndSend("fanout.exchange", "这是要被抛弃的字段", "定时发送的消息。。。");

	}



}
