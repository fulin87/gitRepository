package com.test1.servlet.test;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.test1.servlet.LoginMainServlet;

public class LoginMainServletTest {
	private LoginMainServlet loginMainServlet;

	@Before
	public void onSetUp() throws Exception {
		loginMainServlet = new LoginMainServlet();
		
	}
	
	@Test
	public void testLogin() {
		MockServletContext context = new MockServletContext();
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("isLogin", "true");
		request.setSession(session);
		
		//request.addParameter("choice", expanded);
		//request.addParameter("contextMenu", "left");
		
		try {
			loginMainServlet.service(request, response);
			
			Assert.assertEquals("Success to login main", response.getContentAsString());
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
