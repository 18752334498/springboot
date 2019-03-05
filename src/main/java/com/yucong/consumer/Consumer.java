package com.yucong.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@RabbitListener(queues = "topic.queue.yucong")
	public void process1(String msg) {
		System.out.println("Consumer1 收到的消息是:       " + msg);
	}

	/******************************************************************************************************************/
	/**
	 * process2和process3监听的是同一个direct.queue.aa，发过来的消息会轮询的发送个process2和process3
	 * 
	 * @param msg
	 */
	@RabbitListener(queues = "direct.queue.aa")
	public void process2(String msg) {
		System.out.println("Consumer2 收到的消息是:       " + msg);
	}

	@RabbitListener(queues = "direct.queue.aa")
	public void process3(String msg) {
		System.out.println("Consumer3 收到的消息是:       " + msg);
	}


	/******************************************************************************************************************/
	/**
	 * 队列fanout.queue.aa和fanout.queue.bb都收到了交换器fanout.exchange的信息
	 * 
	 * 4 和 5 轮询消费fanout.queue.aa ，6 和 7 轮询消费fanout.queue.bb
	 * 
	 * @param msg
	 */
	@RabbitListener(queues = "fanout.queue.aa")
	public void process4(String msg) {
		System.out.println("Consumer4 收到的消息是:       " + msg);
	}

	@RabbitListener(queues = "fanout.queue.aa")
	public void process5(String msg) {
		System.out.println("Consumer5 收到的消息是:       " + msg);
	}

	@RabbitListener(queues = "fanout.queue.bb")
	public void process6(String msg) {
		System.out.println("Consumer6 收到的消息是:       " + msg);
	}

	@RabbitListener(queues = "fanout.queue.bb")
	public void process7(String msg) {
		System.out.println("Consumer7 收到的消息是:       " + msg);
	}
}
