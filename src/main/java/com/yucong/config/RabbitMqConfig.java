package com.yucong.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

	@Bean(name = "myQueue")
	public Queue myQueue() {
		return new Queue("topic.queue.yucong");
	}

	@Bean(name = "myExchange")
	public TopicExchange myExchange() {
		return new TopicExchange("topic.exchange.yucong");
	}

	@Bean(name = "topicBinding")
	Binding topicBinding(@Qualifier("myQueue") Queue myQueue, @Qualifier("myExchange") TopicExchange myExchange) {
		return BindingBuilder.bind(myQueue).to(myExchange).with("topic.routeKey.*");
	}

	/******************************************************************************************************************/
	@Bean(name = "directQueue")
	public Queue directQueue() {
		QueueBuilder.durable("direct.queue.aa").build();
		return new Queue("direct.queue.aa");
	}


	@Bean(name = "directExchange")
	public DirectExchange directExchange() {
		ExchangeBuilder.directExchange("direct.exchange.aa").durable(true).build();
		return new DirectExchange("direct.exchange.aa");
	}

	@Bean(name = "directBinding")
	Binding directBinding(@Qualifier("directQueue") Queue directQueue,
			@Qualifier("directExchange") DirectExchange directExchange) {
		return BindingBuilder.bind(directQueue).to(directExchange).with("direct.routeKey.aa");
	}

	/******************************************************************************************************************/
	@Bean(name = "fanoutQueueaa")
	public Queue fanoutQueueaa() {
		return new Queue("fanout.queue.aa");
	}

	@Bean(name = "fanoutQueuebb")
	public Queue fanoutQueuebb() {
		return new Queue("fanout.queue.bb");
	}

	@Bean(name = "fanoutExchange")
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange("fanout.exchange");
	}

	@Bean
	public Binding fanoutBinding1() {
		return BindingBuilder.bind(fanoutQueueaa()).to(fanoutExchange());
	}

	@Bean
	public Binding fanoutBinding2() {
		return BindingBuilder.bind(fanoutQueuebb()).to(fanoutExchange());
	}

	public RabbitTemplate.ConfirmCallback confirmCallback() {
		return new ConfirmCallback() {

			@Override
			public void confirm(CorrelationData correlationData, boolean ack, String cause) {

			}
		};
	}

	public RabbitTemplate.ReturnCallback returnCallback() {
		return new ReturnCallback() {
			@Override
			public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
				
			}
		};
	}


//	路由键：routings = [ 'happy.work',  'happy.life' , 'happy.work.teacher',  'sad.work',  'sad.life', 'sad.work.teacher' ]
//	"#"：匹配所有的路由键
//	"happy.#"：匹配  'happy.work',  'happy.life' , 'happy.work.teacher'
//	"work.#"：无匹配
//	“happy.*”：匹配 'happy.work',  'happy.life'
//	"*.work"：匹配 'happy.work',  'sad.work'
//	"*.work.#"：匹配  'happy.work',  'happy.work.teacher',  'sad.work', 'sad.work.teacher' 

}
