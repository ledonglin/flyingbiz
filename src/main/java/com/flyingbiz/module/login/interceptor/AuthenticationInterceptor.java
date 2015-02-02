package com.flyingbiz.module.login.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.flyingbiz.module.config.Config;
import com.flyingbiz.module.config.Constant;

public class AuthenticationInterceptor implements HandlerInterceptor {
    private Log logger = LogFactory.getLog(this.getClass());
    protected static final String COMMAND_TAG = "command";
	static String byPassURLs=Config.getProperties("byPassURL","");

	private boolean getByPassURLs(String servletPath) {
		String[] byPassURLArray = byPassURLs.split(",");
		for(String bypassUrl : byPassURLArray) {
			if(servletPath.startsWith(bypassUrl))
				return true;
		}
		return false;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String byPassInterceptor= (String)request.getAttribute( "byPassInterceptor");
		String servletPath=request.getServletPath();
		
		if (servletPath != null) {
			if ( ("true").equals(byPassInterceptor) || getByPassURLs(servletPath)) {
				logger.info("No Authentication/Authorization - " + servletPath
						+ " - " + byPassInterceptor);
				request.setAttribute("byPassInterceptor", "false");
				return true;
			}
		}

		String command = (String) request.getParameterMap().get(COMMAND_TAG);
		logger.debug("Before intercepting the command " + command
				+ " of Controller " + request.getServletPath());

		if ("account/login".equals(servletPath) || "account/regedit".equals(servletPath)) {
			return true;
		} else if ("logout".equals(command)) {
			return true;
		} else {
			HttpSession session = request.getSession(false);
			if(null != session && null != session.getAttribute(Constant.USER_CONTEXT))
				return true;
			else {
				response.sendRedirect("prelogin?url=" + request.getQueryString());
				return false;
			}
		}

	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
