package com.flyingbiz.module.login.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flyingbiz.module.login.dao.LoginLogDao;
import com.flyingbiz.module.login.dao.UserDao;
import com.flyingbiz.module.login.model.User;
import com.flyingbiz.module.login.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private LoginLogDao loginLogDao;

	@Override
	public User findUserbyLoginName(User user) {
		return userDao.findUserByLoginName(user.getUserName());
	}

}
