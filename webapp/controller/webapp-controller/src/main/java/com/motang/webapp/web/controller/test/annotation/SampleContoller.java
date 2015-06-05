package com.motang.webapp.web.controller.test.annotation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.motang.webapp.domain.Account;
import com.motang.webapp.domain.LoginUserModel;
import com.motang.webapp.service.AccountService;

@Controller
@RequestMapping("/test/annotation")
@SessionAttributes(value = {"userSession"})
public class SampleContoller implements ServletContextAware {
	private Logger logger = LoggerFactory.getLogger(SampleContoller.class);

	private ServletContext servletContext;
	
	@Autowired
	private AccountService accountService;

	@RequestMapping("/test")
	public ModelAndView test() throws Exception {
		ModelAndView mv = new ModelAndView();
		// 添加模型数据 可以是任意的POJO对象
		mv.addObject("message", "Hello World!====");
		ModelMap model = mv.getModelMap();
		model.addAttribute("message", "Hello World!");
		// 设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
		mv.setViewName("example");
		return mv;
	}

	@RequestMapping(value = "/testRequest")
	public ModelAndView testRequest(HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView();
		ModelMap model = mv.getModelMap();
		model.addAttribute("message", "Hello World!");
		mv.setViewName("example");
		return mv;
	}

	@RequestMapping(value = "/requestOrResponse")
	public String requestOrResponse(ServletRequest servletRequest, HttpServletRequest httpServletRequest, 
			ServletResponse servletResponse, HttpServletResponse httpServletResponse) {
		logger.info("servletRequest={}, servletResponse={}, httpServletRequest={}, httpServletResponse={}", new Object[]{servletRequest, servletResponse, httpServletRequest, httpServletResponse});
		return "success";
	}

	@RequestMapping(value = "/inputOrOutBody")
	public void inputOrOutBody(InputStream requestBodyIn, OutputStream responseBodyOut)
	        throws IOException {
		logger.info("requestBodyIn={}, responseBodyOut={}", requestBodyIn, responseBodyOut);
		responseBodyOut.write("success".getBytes());
	}
	
	@RequestMapping(value = "/readerOrWriteBody")
	public void readerOrWriteBody(Reader reader, Writer writer)
	        throws IOException {
		logger.info("reader={}, writer={}", reader, writer);
	    writer.write("hello");
	}

	@RequestMapping(value = "/webRequest")
	public String webRequest(WebRequest webRequest, NativeWebRequest nativeWebRequest) {
		logger.info(webRequest.getParameter("test"));//①得到请求参数test的值
	    webRequest.setAttribute("name", "value", WebRequest.SCOPE_REQUEST);//②
	    logger.info(webRequest.getAttribute("name", WebRequest.SCOPE_REQUEST).toString());
	    HttpServletRequest request = 
	        nativeWebRequest.getNativeRequest(HttpServletRequest.class);//③
	    HttpServletResponse response = 
	        nativeWebRequest.getNativeResponse(HttpServletResponse.class);
	        return "success";
	}

	@RequestMapping(value = "/session")
	public String session(HttpSession session) {
		logger.info("sessionID={}", session.getId());
		Enumeration attributeNames = session.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String key = (String) attributeNames.nextElement();
			logger.info("session key={}, value={}", key, ReflectionToStringBuilder.toString(session.getAttribute(key)));
		}
	    return "success";
	}

	@RequestMapping(value = "/model")
	public String model(Model model){
		logger.info("model={}", model);
		return "success";
	}
	
	@RequestMapping(value = "/error")
	public String error(Model model, Errors errors){
		logger.info("model={}", model);
		return "success";
	}
	
	@RequestMapping(value = "/requestparam1")
	public String requestparam1(@RequestParam(required=false) String username){
		logger.info("username={}", username);
		return "success";
	}
	
	@RequestMapping(value = "/requestparam2")
	public String requestparam2(@RequestParam(value="list") List<String> list){
		logger.info("list={}", ReflectionToStringBuilder.toString(list));
		return "success";
	}
	
	@RequestMapping(value="/{userId}/topics/{topicId}/testPathVariable")
	public String testPathVariable(
	       @PathVariable(value="userId") int userId, 
	       @PathVariable(value="topicId") int topicId){
		logger.info("userId={},topicId={}", userId, topicId);
		return "success";
	}

	@RequestMapping("/testCookie")
	public String testCookie(@CookieValue(value="JSESSIONID", defaultValue="") String sessionId, @CookieValue(value="JSESSIONID", defaultValue="") Cookie cookie){
		logger.info("sessionId={},cookie={}",sessionId,cookie);
		return "success";
	}
	
	
	@RequestMapping(value="/header")
	public String testHeader(
	       @RequestHeader("User-Agent") String userAgent
	       ,@RequestHeader("Accept") String[] accepts
	       ,@RequestHeader("Accept-Encoding") String acceptEncoding
	       ,@RequestHeader("Accept-Language") String acceptLanguage
	       ,@RequestHeader("Accept-Charset") String acceptCharset
	       ,@RequestHeader("Cookie") String cookie
	       ){
		logger.info("userAgent={},accepts={},Accept-Encoding={},Accept-Language={},Accept-Charset={},Cookie={}",new Object[]{userAgent,accepts,acceptEncoding,acceptLanguage,acceptCharset,cookie});
		return "success";
	}
	
	@RequestMapping("/userLogin")
	public String loginPage(@ModelAttribute("user") LoginUserModel user, Model model) {
		logger.info("usermodel={}",ReflectionToStringBuilder.toString(user), ReflectionToStringBuilder.toString(model.asMap()));
		return "userlogin";
	}
	
	@ModelAttribute("cityList")
	public List<String> cityList() {
	    return Arrays.asList("北京","上海","深圳","广州","天津","重庆");
	}
	
	@ModelAttribute("usermodel") 
	public LoginUserModel getUser(@RequestParam(value = "username", defaultValue = "") String username) {
		logger.info("username={}", username);
		// TODO 去数据库根据用户名查找用户对象
		LoginUserModel user = new LoginUserModel();
		user.setUsername("zhang");
		return user;
	}
	
	@ModelAttribute("userSession") 
	public LoginUserModel initUser() {
		LoginUserModel user = new LoginUserModel();
		user.setUsername("zhang session");
		Random random = new Random();
		byte[] bytes = new byte[10];
		random.nextBytes(bytes);
		user.setPassword(new String(bytes));
		logger.info("initUser - user={}", ReflectionToStringBuilder.toString(user));
		return user;
	}
	
	@RequestMapping("/testSession1") 
	public String testSession1(@ModelAttribute("userSession") LoginUserModel user) {
		logger.info("user={}", ReflectionToStringBuilder.toString(user));
		return "userlogin";
	}
	
	@RequestMapping("/testSession2") 
	public String testSession2(@ModelAttribute("userSession") LoginUserModel user, SessionStatus status) {
		logger.info("user={}", ReflectionToStringBuilder.toString(user));
		if(user.getUsername().contains("session")) { 
	        status.setComplete();  
	    }
		return "userlogin";
	}
	
	//no JSP page
	@RequestMapping("/exception1") 
	public String exception1(@ModelAttribute("userSession") LoginUserModel user) {
		logger.info("user={}", ReflectionToStringBuilder.toString(user));
		return "userlogin1";
	}
	
	//only throw runtimeException
	@RequestMapping("/exception2") 
	public void exception2() {
		logger.info("exception2={}", "");
		throw new IllegalArgumentException("error argument exception");
	}
	
	@ExceptionHandler(value={RuntimeException.class,IllegalArgumentException.class})
	private ModelAndView exceptionArgument(HttpServletRequest request, Exception ex) {
		logger.info("exception2", ex);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", ex); 
		
		request.setAttribute("ex", ex);
		if (ex instanceof IllegalArgumentException) {
			return new ModelAndView("error/error-business", model); 
		} else {
			return new ModelAndView("error/error", model);
		}
	}
	
	@RequestMapping("/listAccounts") 
	public String accounts(Model model) {
		List<Account> listAll = accountService.listAll();
		model.addAttribute("accounts", listAll);
		
		return "account";
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
