<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	Object userinfo = request.getSession().getAttribute(cn.amichina.common.constant.SystemConstant.SESSION_USER_KEY);
	if (null == userinfo) {
			out.println("<script type=\"text/javascript\">alert('Please logon again!');window.location.href='user/login'; </script>");
	}
%>