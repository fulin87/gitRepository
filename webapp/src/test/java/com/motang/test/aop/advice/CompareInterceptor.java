package com.motang.test.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class CompareInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object result = null;
		Object[] arguments = invocation.getArguments();
		if (arguments.length>0) {
			String stu_name = arguments[0].toString();
			if (stu_name.equals("dragon")) {
				// 如果学生是dragon时,执行目标方法,
				result = invocation.proceed();
			} else {
				System.out.println("此学生是" + stu_name + "而不是dragon,不批准其加入.");
			}
		} else {
			result = invocation.proceed();
		}

		return result;
	}

}
