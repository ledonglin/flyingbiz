package com.flyingbiz.module.login.dao;

import com.flyingbiz.module.login.model.User;


public interface UserDao {

	public User findUserByLoginName(String loginName);

	public void regeistUser(User user);

	public void update(User user);

}
