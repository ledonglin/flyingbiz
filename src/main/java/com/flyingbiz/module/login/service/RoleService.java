package com.flyingbiz.module.login.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flyingbiz.module.login.dao.RoleDao;
import com.flyingbiz.module.login.model.Role;


@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	private static final Logger log = LoggerFactory.getLogger(RoleDao.class);

	public void addNewRole(Role role) {
		log.debug("invoke method addNewRole start ...");
		roleDao.addNewRole(role);
		log.debug("invoke method addNewRole end ...");

	}

	public Role getRoleById(String roleId) {
		log.debug("invoke method getRoleById start ...");
		Role role = roleDao.getRoleById(roleId);
		log.debug("invoke method getRoleById end...");

		return role;
	}

}
