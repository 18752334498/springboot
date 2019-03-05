package com.yucong.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.yucong.annotation.DoSomething;

import Utils.SpelParser;

@Aspect
@Component
public class DoSomethingAspect {

	@Around("@annotation(dSomething)")
	// 把 DoSomething 作为参数传进来，参数的别名可以传递给 @Around，不是 @Around 传给 参数
	public Object doAround(ProceedingJoinPoint pjp, DoSomething dSomething) throws Throwable {
		
//		反射获取注解对象，但是可以直接注解对象作为参数传进来
//		Method method = ((MethodSignature) pjp.getSignature()).getMethod();
//		DoSomething doSomething = method.getAnnotation(DoSomething.class);

		// dSomething.key() 获取的是 "#id",如果是具体数值就可以直接拿来用，但这个值是从方法参数里获取，只能从方法获取
		String key = (String) SpelParser.getKey(dSomething.key(), pjp);
		System.out.println(key);
		System.out.println(dSomething.cacheName());
		System.out.println(dSomething.needLog());
		
		// 类名
		System.out.println(pjp.getSourceLocation().getWithinType().getName());
		// 方法名
		System.out.println(pjp.getSignature().getName());

		System.out.println("======1=====");
		Object proceed = pjp.proceed();
		System.out.println("======2=====");
		return proceed;
	}

}
