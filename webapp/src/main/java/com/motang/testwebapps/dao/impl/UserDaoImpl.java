package com.motang.testwebapps.dao.impl;

import org.springframework.stereotype.Component;

import com.motang.testwebapps.dao.UserDao;
import com.motang.testwebapps.vo.User;

@Component("userDao")
public class UserDaoImpl implements UserDao{

	@Override
	public User getUser(String name) throws Exception {
		User user = new User();
		user.setUsername(name);
		user.setPassword(name);
		return user;
	}

}
