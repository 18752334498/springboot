package com.yucong.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * spring-boot-starter-aop，加入依赖，默认就开启了AOP的支持
 * @see AopAutoConfiguration
 *      默认是使用基于JDK的动态代理来实现AOP，spring.aop.proxy-target-class=false
 *      或者不配置，表示使用JDK的动态代理， pring.aop.proxy-target-class=true表示使用cglib，
 *      如果配置了spring.aop.proxy-target-class=false，但是代理类没有实现接口，则依然使用cglib
 */
@Aspect
//@Component
public class TestAspect {
	
	/**
	 * execution (* com.sample.service.impl..*.*(..)) 整个表达式可以分为五个部分：
	 * 1、execution(): 表达式主体。
	 * 2、第一个*号：表示返回类型，*号表示所有的类型。
	 * 3、包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.sample.service.impl包、子孙包下所有类的方法。
	 * 4、第二个*号：表示类名，*号表示所有的类。
	 * 5、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数。
	 */
	
	/**
	 * ----------around,start---------
		----------before,start---------
		URL : http://localhost:8888/aspect/test1
		HTTP_METHOD : GET
		IP : 0:0:0:0:0:0:0:1
		CLASS_METHOD : com.yucong.service.AnnoAspectService.test1
		ARGS : [12]
		----------before,end---------
		我是service里的业务逻辑。。。
		----------around,end---------结果是： 我是service返回值。。。
		----------After,start---------
		方法最后执行.....
		----------After,end---------
		----------AfterReturning,start---------
		方法的返回值 : 我是service返回值。。。
		----------AfterReturning,end---------
	 */

	@Pointcut("execution(public * com.yucong.service.AnnoAspectService.*(..))")
	public void test() {}

	@Around("test()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable { // ProceedingJoinPoint is only supported for
																		// around advice
		System.out.println("----------around,start---------");
		// 如果不执行这句，会不执行切面的Before方法及controller的业务方法
		Object proceed = pjp.proceed();
		System.out.println("----------around,end---------结果是： " + proceed);
		return proceed;
	}

	@Before(value = "test()")
	public void doBefore(JoinPoint jp) throws Throwable {
		System.out.println("----------before,start---------");
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 记录下请求内容
		System.out.println("URL : " + request.getRequestURL().toString());
		System.out.println("HTTP_METHOD : " + request.getMethod());
		System.out.println("IP : " + request.getRemoteAddr());
		System.out.println(
				"CLASS_METHOD : " + jp.getSignature().getDeclaringTypeName() + "." + jp.getSignature().getName());
		System.out.println("ARGS : " + Arrays.toString(jp.getArgs()));
		System.out.println("----------before,end---------");
	}

	@AfterReturning(returning = "ret", pointcut = "test()")
	public void doAfterReturning(Object ret) throws Throwable {
		System.out.println("----------AfterReturning,start---------");
		// 处理完请求，返回内容
		System.out.println("方法的返回值 : " + ret);
		System.out.println("----------AfterReturning,end---------");
	}

	// 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
	@After("test()")
	public void after(JoinPoint pjp) {
		System.out.println("----------After,start---------");
		System.out.println("方法最后执行.....");
		System.out.println("----------After,end---------");
	}
	
	
}
