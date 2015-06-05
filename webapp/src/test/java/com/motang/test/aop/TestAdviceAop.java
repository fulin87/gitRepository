package com.motang.test.aop;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.motang.test.aop.advice.IStudent;
import com.motang.test.aop.advice.impl.Student2;

@ContextConfiguration(locations = { "/aop/test-aop-factorybean.xml" })
public class TestAdviceAop extends AbstractJUnit4SpringContextTests {
	@Autowired
	@Qualifier("student1")
	private IStudent student;

	@Autowired
	@Qualifier("student2")
	private Student2 student2;

	@Test
	public void testServiceLog() throws InterruptedException {
		student.addStudent("motang");
		TimeUnit.SECONDS.sleep(2);
		student2.addStudent("motang");
		TimeUnit.SECONDS.sleep(2);
		student.addStudent("dragon");
		TimeUnit.SECONDS.sleep(2);
		student2.addStudent("dragon");
		TimeUnit.SECONDS.sleep(2);
		System.out.printf("there are %s students in interface by JDKProxy.\n", student.getStudent());
		System.out.printf("there are %s students in class by CIGLIBProxy.\n", student2.getStudent());

	}
}
