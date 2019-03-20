package com.yucong.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import com.yucong.mapper.UserMapper;
import com.yucong.model.User;

@Service
public class RedisService {

//	@Autowired
//	private StringRedisTemplate template;

	@Autowired
	private static RedisTemplate<Object, Object> redisTemplate;

	@Autowired
	private UserMapper userMapper;

	public void set() {
		ValueOperations<Object, Object> forValue = redisTemplate.opsForValue();
		HashOperations<Object, Object, Object> forHash = redisTemplate.opsForHash();
		ListOperations<Object, Object> forList = redisTemplate.opsForList();
		SetOperations<Object, Object> forSet = redisTemplate.opsForSet();
		ZSetOperations<Object, Object> opsForZSet = redisTemplate.opsForZSet();

		forValue.set("age", 10);
		forValue.set("user", userMapper.selectUserById(3));

		forHash.putAll("userMap", userMapper.selectMapById());

		User user1 = userMapper.selectUserById(1);
		User user2 = userMapper.selectUserById(7);
		List<User> list = new ArrayList<User>();
		list.add(user1);
		list.add(user2);
		forList.leftPushAll("list", list);

	}

	/**
	 * 实体类一定要实现序列化
	 */
	public void get() {
		ValueOperations<Object, Object> forValue = redisTemplate.opsForValue();
		HashOperations<Object, Object, Object> forHash = redisTemplate.opsForHash();
		ListOperations<Object, Object> forList = redisTemplate.opsForList();
		SetOperations<Object, Object> forSet = redisTemplate.opsForSet();
		ZSetOperations<Object, Object> opsForZSet = redisTemplate.opsForZSet();

		int age = (int) forValue.get("age");
		User user = (User) forValue.get("user");
//		Users [id=3, name=Tom--3, age=22]
		System.out.println(age + "\n" + user);
		
		Map<Object, Object> resultMap = forHash.entries("userMap");
		Set<Object> resultMapSet = forHash.keys("userMap");
		List<Object> reslutMapList = forHash.values("userMap");
		int value = (int) forHash.get("userMap", "age");
//		resultMap:{age=22, id=10, name=Tom--10}
//		resultMapSet:[age, id, name]
//		reslutMapList:[22, 10, Tom--10]
//		value:22
		System.out.println("resultMap:" + resultMap);
		System.out.println("resultMapSet:" + resultMapSet);
		System.out.println("reslutMapList:" + reslutMapList);
		System.out.println("value:" + value);

//		[Users [id=1, name=Tom--1, age=22], Users [id=7, name=Tom--7, age=22]]
		System.out.println(forList.leftPop("list"));
	}

}
