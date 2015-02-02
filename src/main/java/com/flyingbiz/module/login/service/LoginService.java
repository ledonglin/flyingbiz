package com.flyingbiz.module.login.service;

import com.flyingbiz.module.login.exception.UserExistException;
import com.flyingbiz.module.login.model.LoginLog;
import com.flyingbiz.module.login.model.User;

public interface LoginService {

	User userLogin(User user, LoginLog log);
	
	void save(User user) throws UserExistException;
	
	void updateUser(User user);
}
