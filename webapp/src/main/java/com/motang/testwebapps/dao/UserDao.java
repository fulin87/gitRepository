package com.motang.testwebapps.dao;

import com.motang.testwebapps.vo.User;

public interface UserDao {
	public User getUser(String name) throws Exception;
}
