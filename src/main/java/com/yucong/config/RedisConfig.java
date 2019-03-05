package com.yucong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 在 @link RedisAutoConfiguration 里已经完成了 redisTemplate 的配置和注入,启动即用
 * 这里试着修改了一下,将RedisTemplate<Object, Object> 改为了 RedisTemplate<String, Object>
 * 但最好不要改
 */
//@Configuration
public class RedisConfig {

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws Exception {
		System.err.println("注入  redisTemplate 。。。。");
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}

	@Bean
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws Exception {
		System.err.println("注入  stringRedisTemplate 。。。。");
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}
}
