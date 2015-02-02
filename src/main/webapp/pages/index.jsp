<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.flyingbiz.module.config.Config,
				com.flyingbiz.module.config.Constant,
				com.flyingbiz.module.login.model.User"  %>
<% 
    String context = request.getContextPath();
    request.setAttribute("context",context);
    User user = (User)session.getAttribute(Constant.USER_CONTEXT);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>
<body>
Hello, jsp
UserName： <%=user.getUserName() %>

不拦截的URL：<%=Config.getProperties("byPassURL") %>
</body>
</html>
