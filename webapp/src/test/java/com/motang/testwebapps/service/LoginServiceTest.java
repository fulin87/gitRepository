package com.motang.testwebapps.service;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.motang.testwebapps.dao.UserDao;
import com.motang.testwebapps.service.impl.LoginServiceImpl;
import com.motang.testwebapps.vo.User;

public class LoginServiceTest {
	//private static final Logger logger = LoggerFactory.getLogger(LoginServiceTest.class);
	
	private IMocksControl control;
	private LoginServiceImpl loginService;
	private UserDao userDaoMock;
	
	@Before
    public void setup() {
		loginService = new LoginServiceImpl();
		
		control = EasyMock.createControl();
		userDaoMock = control.createMock(UserDao.class);
		
		loginService.setUserDao(userDaoMock);
		
		//ClassLoader classLoader = this.getClass().getClassLoader();
		//ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
    }
	
	@Test
	public void testLogin() {
		try {
			User user = new User();
			user.setUsername("motang");
			
			EasyMock.expect(userDaoMock.getUser("motang")).andReturn(user);
			EasyMock.replay(userDaoMock);
			user.setPassword("motang");
			Assert.assertTrue(loginService.login(user));
			//Assert.assertFalse(loginService.login(user));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
