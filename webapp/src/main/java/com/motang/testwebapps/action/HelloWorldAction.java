package com.motang.testwebapps.action;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.motang.testwebapps.service.LoginService;
import com.motang.testwebapps.vo.User;
import com.opensymphony.xwork2.ActionSupport;

//@ParentPackage("custom")
//@Results( {
//	@Result(name = "success", location = "HelloWorld.jsp", type = "dispatcher")
//})
@Controller
public class HelloWorldAction extends ActionSupport{
	private static final Logger logger = LoggerFactory.getLogger(HelloWorldAction.class);

	private int helloCount;
	private String userName;
	
	@Autowired
	@Qualifier(value="loginService")
	private LoginService loginService;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getHelloCount() {
		return helloCount;
	}

	public String execute() throws Exception {
		helloCount++;
		logger.info("Thread={}, Object={}", Thread.currentThread().getName(), this);
		TimeUnit.SECONDS.sleep(20);
		
		User user = new User();
		user.setUsername(userName);
		
		loginService.login(user);
		return SUCCESS;
	}
}
