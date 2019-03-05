package com.yucong.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.yucong.annotation.CacheRedisByOneParam;

import Utils.SpelParser;

@Aspect
@Component
public class CacheRedisAspect {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Around("@annotation(crs)")
	public Object doAround(ProceedingJoinPoint pjp, CacheRedisByOneParam crs) {
		String value = (String) SpelParser.getKey(crs.value(), pjp);
		String prefix = crs.prefix();
		System.out.println(pjp.getSourceLocation().getWithinType().getName());// 类名
		System.out.println(pjp.getSignature().getName());// 方法名
		System.out.println(pjp.getArgs());// 参数值

		Object result = redisTemplate.opsForValue().get(prefix + "_" + value);
		if (result != null) {
			System.out.println("缓存取值。。。");
			return result;
		}
		System.out.println("缓存没值！！！");
		try {
			result = pjp.proceed();
			if (result != null) {
				redisTemplate.opsForValue().set(prefix + "_" + value, result);
				System.out.println("将值加入缓存。。。");
			}
		} catch (Throwable e) {
			System.out.println("程序异常。。。");
			e.printStackTrace();
		}
		return result;
	}
}
