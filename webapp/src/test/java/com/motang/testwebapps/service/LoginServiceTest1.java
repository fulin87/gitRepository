package com.motang.testwebapps.service;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.motang.testwebapps.vo.User;

@ContextConfiguration(locations={"/service/service-test-config.xml"})
//@ContextConfiguration(locations={"classpath:service/service-test-config.xml"})
public class LoginServiceTest1 extends AbstractJUnit4SpringContextTests{
	//private static final Logger logger = LoggerFactory.getLogger(LoginServiceTest.class);
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private ApplicationContext context;
	
	@Test
	public void testLogin() {
		try {
			User user = new User();
			user.setUsername("motang");
			user.setPassword("motang");
			
			System.err.println(context);
			Assert.assertTrue(loginService.login(user));
			//Assert.assertFalse(loginService.login(user));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
