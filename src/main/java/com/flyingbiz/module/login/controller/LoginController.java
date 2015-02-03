package com.flyingbiz.module.login.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.flyingbiz.module.config.Constant;
import com.flyingbiz.module.login.model.LoginLog;
import com.flyingbiz.module.login.model.Role;
import com.flyingbiz.module.login.model.User;
import com.flyingbiz.module.login.service.LoginService;
import com.flyingbiz.module.login.service.RoleService;
import com.flyingbiz.module.login.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(LoginController.class);

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/abc", method = RequestMethod.GET)
	public String welcome2(@RequestParam String rolename) {
		logger.debug("abc");

		Role role = new Role();
		role.setRoleId(UUID.randomUUID().toString());
		role.setRoleName("dawang");
		;
		role.setRolePath("this is path");
		roleService.addNewRole(role);

		ModelAndView mv = new ModelAndView();
		mv.addObject("role", roleService.getRoleById(role.getRoleId()));

		// Spring uses InternalResourceViewResolver and return back index.jsp
		return "login";

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, User user) throws IOException {
		logger.debug("User : " + user + " login start...");

		ModelAndView mv = new ModelAndView();
		LoginLog loginLog = initLoginLog(request);
		User dbUser = loginService.userLogin(user, loginLog);
		if (dbUser == null) {
			mv.addObject(Constant.ERROR_MSG_KEY, "用户名不存在或密码不正确");
		} else {
			setSessionUser(request, dbUser);
			mv.addObject("user", dbUser);

			String url = (String) request.getSession().getAttribute(
					Constant.FORWARD_URL);
			if (url == null || url =="") {
				url = "index";
			}
//			mv.setViewName("dashboard");
			mv.setViewName("uploadFile");

		}

		logger.debug("User : " + user + " login end..." + dbUser == null ? "SUCCESS"
				: "FAILURE");

		return mv;
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		logger.debug("User : logout start...");
		session.removeAttribute(Constant.USER_CONTEXT);
		logger.debug("User : logout end ...");

		return "forward:/index.jsp";
	}

}
