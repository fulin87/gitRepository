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

public class LoginMainServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(LoginMainServlet.class);

	private int count=0;
	public void init() throws ServletException {
		logger.debug("=====>>>Start-----invoke init method....");
		super.init();
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		
		HttpSession session = request.getSession();
		Object isLogin = session.getAttribute("isLogin");
		logger.debug("begin with isLogin={} and count={}", isLogin, count++);
		
		if (isLogin != null && isLogin instanceof String) {
			boolean isLoginFlag = Boolean.parseBoolean(isLogin.toString());
			if (isLoginFlag) {
				logger.debug("Already Login with sessionID={}", session.getId());
			}
			
			writer.write("Success to login main");
		} else {
			writer.write("Please login");
		}
	}
}
