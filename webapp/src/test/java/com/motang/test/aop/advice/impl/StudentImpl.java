package com.motang.test.aop.advice.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.motang.test.aop.advice.IStudent;

public class StudentImpl implements IStudent,InvocationHandler{

	private int count;
	@Override
	public void addStudent(String name) {
		count++;
		System.out.println( " 欢迎  " + name + "  你加入Spring家庭! " );	
		
//		IStudent newProxyInstance = (IStudent) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{IStudent.class}, this);
//		int student = newProxyInstance.getStudent();
//		System.out.println(student);
	}

	@Override
	public int getStudent() {

		
		return count;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object invoke = method.invoke(this, args);
		System.out.println("=================");
		return invoke;
	}

//	public static void main(String[] args) {
//		StudentImpl test = new StudentImpl();
//		test.addStudent("motang");
//	}
}
