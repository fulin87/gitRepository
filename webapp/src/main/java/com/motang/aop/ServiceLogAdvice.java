package com.motang.aop;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import com.motang.domain.Account;

@Aspect
public class ServiceLogAdvice implements MethodBeforeAdvice,MethodInterceptor,AfterAdvice{

	private Logger logger = LoggerFactory.getLogger(ServiceLogAdvice.class);
	
	public void logBefore() {
		logger.info("logBefore");
	}
	
	public void logAfter() {
		logger.info("logAfter");
	}
	
	@Pointcut("execution(* com.motang.dao.mapper.*.get*(java.lang.String)) && args(username)")
	public void getAccountByUsername(String username){
		logger.info("logAfterddddd {}", username);
	}
	
	@Before("getAccountByUsername(username)")
	public void logBeforeArgs(String username) {
		logger.info("logBeforeArgs {}", username);
	}
	
	@After("getAccountByUsername(username)")
	public void logAfterArgs(String username) {
		logger.info("logAfter {}", username);
	}

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		logger.info("log Before......................");
		
	}
	
	@AfterReturning(pointcut="execution(* com.motang.dao.mapper.*.get*(java.lang.String))",returning="arg")
	public Account logArgs(Account arg) {
		logger.info("====logArgs {}", arg);
		return arg;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		return invocation.proceed();
	}
}
