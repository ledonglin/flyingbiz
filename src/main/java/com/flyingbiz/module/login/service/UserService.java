package com.flyingbiz.module.login.service;

import com.flyingbiz.module.login.model.User;


public interface UserService {
	public User findUserbyLoginName(User user);
}
