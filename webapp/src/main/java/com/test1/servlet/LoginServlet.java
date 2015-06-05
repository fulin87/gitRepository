package com.test1.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginServlet extends HttpServlet{
	private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);
	
	private static final  String user = "motang";
	private static final  String pwd = "secret";
	
	public void init() throws ServletException {
		logger.debug("=====>>>Start-----invoke init method....");
		super.init();
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		logger.debug("Enter invoke service method");
		
		PrintWriter writer = response.getWriter();
		
		HttpSession session = request.getSession();
		Object isLogin = session.getAttribute("isLogin");
		
		if (isLogin != null && isLogin instanceof String) {
			boolean isLoginFlag = Boolean.parseBoolean(isLogin.toString());
			if (isLoginFlag) {
				logger.debug("Already Login with sessionID={}", session.getId());
			}
			
			writer.write("Already Login");
		} else {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			if (user.equals(username) && pwd.equals(password)) {
				session.setAttribute("isLogin", Boolean.TRUE.toString());
				writer.write("Login first");
			} else {
				logger.debug("Cannot validate user with username={} and password={}", username, password);
				writer.write("Cannot Login");
			}
		}
		
	}
	
	@Override
	public void destroy() {
		logger.debug("=====<<<End----invoke destroy method....");
		super.destroy();
	}
}
