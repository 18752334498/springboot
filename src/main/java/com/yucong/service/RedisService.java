package com.yucong.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.yucong.mapper.UserMapper;
import com.yucong.model.User;

@Service
public class RedisService {

	@Autowired
	private StringRedisTemplate template;

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Autowired
	private UserMapper userMapper;

	public void set() {
		template.opsForValue().set("name", "tom");
		redisTemplate.opsForValue().set("age", new Random().nextInt(1000));
		redisTemplate.opsForValue().set("user", userMapper.selectUserById(3));

		redisTemplate.opsForHash().putAll("userMap", userMapper.selectMapById());

		User user1 = userMapper.selectUserById(1);
		User user2 = userMapper.selectUserById(7);
		List<User> list = new ArrayList<User>();
		list.add(user1);
		list.add(user2);
		redisTemplate.opsForList().leftPushAll("list", list);

		redisTemplate.expire("timeout", 1, TimeUnit.MINUTES);

	}

	/**
	 * 实体类一定要实现序列化
	 */
	public void get() {
		String name = template.opsForValue().get("name");
		int age = (int) redisTemplate.opsForValue().get("age");
		User user = (User) redisTemplate.opsForValue().get("user");
//		tom : 527
//		Users [id=3, name=Tom--3, age=22]
		System.out.println(name + " : " + age + "\n" + user);
		
		Map<Object, Object> resultMap = redisTemplate.opsForHash().entries("userMap");
		Set<Object> resultMapSet = redisTemplate.opsForHash().keys("userMap");
		List<Object> reslutMapList = redisTemplate.opsForHash().values("userMap");
		int value = (int) redisTemplate.opsForHash().get("userMap", "age");
//		resultMap:{age=22, id=10, name=Tom--10}
//		resultMapSet:[age, id, name]
//		reslutMapList:[22, 10, Tom--10]
//		value:22
		System.out.println("resultMap:" + resultMap);
		System.out.println("resultMapSet:" + resultMapSet);
		System.out.println("reslutMapList:" + reslutMapList);
		System.out.println("value:" + value);

//		[Users [id=1, name=Tom--1, age=22], Users [id=7, name=Tom--7, age=22]]
		System.out.println(redisTemplate.opsForList().leftPop("list"));

		System.out.println(redisTemplate.getExpire("timeout"));
		System.out.println(redisTemplate.getExpire("timeout", TimeUnit.MINUTES));
	}

}
