package com.motang.testwebapps.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.motang.testwebapps.dao.UserDao;
import com.motang.testwebapps.service.LoginService;
import com.motang.testwebapps.vo.User;

@Service("loginService")
public class LoginServiceImpl implements LoginService{

	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	
	public boolean login(User user) {
		try {
			if (user != null) {
				User user2 = userDao.getUser(user.getUsername());
				if (user2!=null && user2.getPassword() != null && user2.getPassword().equals(user.getPassword())) {
					return true;
				}
			}
			
		} catch (Exception e) {
			logger.error("Cannot login", e);
		}
		
		return false;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
}
