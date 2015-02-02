package com.flyingbiz.module.login.daoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.flyingbiz.module.login.dao.BaseHibernateDAO;
import com.flyingbiz.module.login.dao.LoginLogDao;
import com.flyingbiz.module.login.model.LoginLog;


@Repository
public class LoginLogDaoImpl extends BaseHibernateDAO implements LoginLogDao {
	private static final Logger logger = LoggerFactory
			.getLogger(LoginLogDaoImpl.class);

	@Override
	public void login(LoginLog log) {
		logger.debug("login start...");
		getSession().save(log);
		logger.debug("login end...");
	}

}
