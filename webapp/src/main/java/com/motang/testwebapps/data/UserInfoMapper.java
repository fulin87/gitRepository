package com.motang.testwebapps.data;

import java.util.List;

import com.motang.testwebapps.model.UserInfo;

public interface UserInfoMapper {
	void insert(UserInfo userInfo);
	void update(UserInfo userInfo);
	void delete(String id);
	UserInfo getById(String id);
	List<UserInfo> findPage(UserInfo userInfo);
	UserInfo getByUsername(String username);
	UserInfo getByAge(Integer age);
}
