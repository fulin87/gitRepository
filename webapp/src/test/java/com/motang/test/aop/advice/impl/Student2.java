package com.motang.test.aop.advice.impl;


public class Student2 {

	private int count;
	
	public void addStudent(String name) {
		count++;
		System.out.println( "============ 欢迎  " + name + "  你加入Spring家庭! " );	
	}

	public int getStudent() {
		return count;
	}


}
