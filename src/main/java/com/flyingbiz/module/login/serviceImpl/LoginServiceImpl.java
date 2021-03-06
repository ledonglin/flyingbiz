package com.flyingbiz.module.login.serviceImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flyingbiz.module.login.dao.LoginLogDao;
import com.flyingbiz.module.login.dao.UserDao;
import com.flyingbiz.module.login.exception.UserExistException;
import com.flyingbiz.module.login.model.LoginLog;
import com.flyingbiz.module.login.model.User;
import com.flyingbiz.module.login.service.LoginService;


@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private LoginLogDao loginLogDao;

	@Override
	public User userLogin(User user, LoginLog loginLog) {
		User dbUser = this.userDao.findUserByLoginName(user.getUserName());
		if (null != dbUser) {
			if(user.getPassword().equals(dbUser.getPassword())){
				loginLog.setUserId(dbUser.getUserId());
				loginLogDao.login(loginLog);
				dbUser.setPassword("");
				return dbUser;
			}else {
				loginLog.setUserId("UnAuthentication User:" + user.getUserName());
				loginLogDao.login(loginLog);
				return null;
			}
		} else {
			loginLog.setUserId("Unknow User:" + user.getUserName());
			loginLogDao.login(loginLog);
			return null;
		}

	}

	/**
	 * 注册一个新用户,如果用户名已经存在此抛出UserExistException的异常
	 * 
	 * @param user
	 */
	@Override
	public void save(User user) throws UserExistException {
		User dbUser = userDao.findUserByLoginName(user.getUserName());
		if (dbUser != null) {
			throw new UserExistException("用户名已经存在");
		} else {
			user.setUserId(UUID.randomUUID().toString());
			userDao.regeistUser(user);;
		}
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}


}
