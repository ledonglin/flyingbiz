package com.flyingbiz.module.login.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.flyingbiz.module.config.Constant;
import com.flyingbiz.module.login.exception.UserExistException;
import com.flyingbiz.module.login.model.LoginLog;
import com.flyingbiz.module.login.model.User;
import com.flyingbiz.module.login.service.LoginService;
import com.flyingbiz.module.login.service.RoleService;
import com.flyingbiz.module.login.service.UserService;

@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {
	private static final Logger logger = Logger.getLogger(BaseController.class);
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private LoginService loginService;
	@RequestMapping(value = "/prelogin", method = RequestMethod.GET)
	public String preLogin(HttpServletRequest request) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response, User user) throws IOException {
		logger.debug("User : " + user + " login start...");

		ModelAndView mv = new ModelAndView();
		LoginLog loginLog = initLoginLog(request);
		User dbUser = loginService.userLogin(user, loginLog);
		if (dbUser == null) {
			mv.addObject(Constant.ERROR_MSG_KEY, "用户名不存在或密码不正确");
			response.sendRedirect("prelogin");

		} else {
			logger.info("User : " + user.getUserName() + " login end..." + dbUser != null ? "SUCCESS" : "FAILURE");
			setSessionUser(request, dbUser);
			mv.addObject("user", dbUser);

			String url = (String) request.getSession().getAttribute(Constant.FORWARD_URL);
			if (url == null || url == "") {
				url = "index";
			}
			response.sendRedirect(url);
		}
	}
	
	@RequestMapping(value = "/preregister", method = RequestMethod.GET)
	public ModelAndView preRegister(HttpServletRequest request, User user) {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("register");
		return view;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response, User user) {
		logger.debug("User Register : register start...");
		ModelAndView view = new ModelAndView();
		try {
			loginService.save(user);
			setSessionUser(request, user);
			view.addObject("user", user);
			view.setViewName("forward:/index");

		} catch (UserExistException e) {
			view.addObject("errorMsg", "用户名已经存在，请选择其它的名字。");
			view.setViewName("forward:/register.jsp");
		}
		logger.debug("User Register : register end...");

		return view;
	}
	
	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		User loginUser = getSessionUser(request);
		mv.addObject("user", loginUser);
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		removeSessionUser(request);
		mv.setViewName("forward:/login.jsp");
		return mv;
	}
}
