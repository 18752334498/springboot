package com.yucong.service;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yucong.annotation.CacheRedisByOneParam;
import com.yucong.mapper.UserMapper;
import com.yucong.model.Phone;
import com.yucong.model.User;

@Service
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true) // 暴露代理对象，使用cglib代理
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public List<User> getAllUsers() {
		return userMapper.getAllUsers();
	}

	// 内部方法调用不会触发动态代理（@Transactional @Async）
	// @Transactional 和 @Async 同时使用时不会回滚

	@Async // 如果此业务是附加业务不能干扰主页物，则需要异步执行
	@Transactional // 内部方法调用不会触发动态代理，包括@Async
	public void test1(int id) {
		System.out.println("异步线程名称： " + Thread.currentThread().getName());
		System.out.println(userMapper.updateAgeById(id));
		try {
			Thread.sleep(2000);
			System.out.println("睡眠结束。。。");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		int a = 1 / 0;
	}

	@Transactional
	public void test2(int id) {
		System.out.println("test2线程名称： " + Thread.currentThread().getName());
		System.out.println(userMapper.updateAgeById(id));
		try {
			Thread.sleep(2000);
			System.out.println("睡眠结束。。。");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*
		 * 在test3调用test2，且都加上事物，如果异常，都会回滚
		 */
//		int a = 1 / 0;
	}

	@Transactional
	public void test3(int id) {
		System.out.println(userMapper.selectUserById(id));
		System.out.println("=====================");
		System.out.println(userMapper.updateAgeById(id));
		System.out.println("==========调用test1===========");
		try {
			// 获取当前对象的动态代理，要引入aop 的jar包
			UserService currentProxy = (UserService) AopContext.currentProxy();
			currentProxy.test2(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("==========调用test1===========");
		System.out.println(userMapper.selectUserById(id));
	}

	@Autowired
	private ApplicationEventPublisher publisher;

	/**
	 * 修改用户信息的时候，通过事件驱动来修改手机信息
	 */
	@Transactional
	public void test4(Phone phone) {
		userMapper.updateAgeById(1);

		publisher.publishEvent(phone);

//		throw new RuntimeException("自定义异常。。。");
	}

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	/*
	 * 批量插入
	 */
	public void insertBatch() {
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = null;
		for (int i = 1; i <= 1000; i++) {
			user = new User();
			user.setId(i);
			user.setName("Tom--" + i);
			user.setAge(22);
			Integer count = mapper.insertBatch(user);
			if (i % 100 == 0) {
				sqlSession.commit();
				System.out.println(count);
			}
		}
	}

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	private static final String PREFIX = "USER";

	/**
	 * 不使用注解，从redis取值
	 */
	public User testRedis(int id) {
		User user = (User) redisTemplate.opsForValue().get(PREFIX + "_" + id);
		if (user == null) {
			System.out.println("缓存没值！！！");
			user = userMapper.selectUserById(id);
			redisTemplate.opsForValue().set(PREFIX + "_" + id, user);
			return user;
		}
		System.out.println("缓存取值。。。");
		return user;
	}

	/**
	 * 使用注解aop ，从redis取值
	 */
	// 使用el表达式，从参数取值
	@CacheRedisByOneParam(value = "#id", prefix = PREFIX)
	public User testAnnotationRedis(int id) {
		return userMapper.selectUserById(id);
	}

}
