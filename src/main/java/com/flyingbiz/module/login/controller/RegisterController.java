package com.flyingbiz.module.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.flyingbiz.module.login.exception.UserExistException;
import com.flyingbiz.module.login.model.User;
import com.flyingbiz.module.login.service.LoginService;
import com.flyingbiz.module.login.service.RoleService;
import com.flyingbiz.module.login.service.UserService;


@Controller
public class RegisterController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(RegisterController.class);

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request, User user) {
		logger.debug("User Register : register start...");
		ModelAndView view = new ModelAndView();
		try {
			loginService.save(user);
			view.addObject("user", user);
			view.setViewName("forward:/registerDetail.jsp");

		} catch (UserExistException e) {
			view.addObject("errorMsg", "用户名已经存在，请选择其它的名字。");
			view.setViewName("forward:/register.jsp");
		}
		logger.debug("User Register : register end...");

		return view;
	}

	@RequestMapping(value = "/registerDetail", method = RequestMethod.POST)
	public ModelAndView registerDetail(HttpServletRequest request, User user) {
		logger.debug("User Register : registerDetail start...");
		ModelAndView mv = new ModelAndView();

		loginService.updateUser(user);
		mv.setViewName("success");

		logger.debug("User Register : registerDetail end...");

		return mv;
	}
}
