package com.motang.controller.annotation;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.motang.domain.Account;
import com.motang.domain.AccountList;
import com.motang.domain.LoginUserModel;
import com.motang.service.AccountService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping("/on")
	public String on() {
		return "success";
	}
	
	@RequestMapping("/on.form")
	public Account logon(String username) {
		Account account = accountService.getAccountByUsername(username);
		return account;
	}
	
	@RequestMapping("/listAll.form")
	public AccountList logon() {
		List<Account> accounts = accountService.listAll();
		
		AccountList accountList = new AccountList();
		accountList.setAccount(accounts);
		return accountList;
	}
	
	@RequestMapping("/validate.form")
	public Object validate(@Valid LoginUserModel userModel, BindingResult result) {
		if (result.hasErrors()) {
			return result.getAllErrors();
		}
		
		List<Account> accounts = accountService.listAll();
		
		AccountList accountList = new AccountList();
		accountList.setAccount(accounts);
		
		return accountList;
	}
	
	@RequestMapping(value="/restfull")
	public String restfullService(@RequestHeader("User-Agent") String userAgent
		       ,@RequestHeader("Accept") String[] accepts
		       ,@RequestHeader("Accept-Encoding") String acceptEncoding
		       ,@RequestHeader("Accept-Language") String acceptLanguage
		       //,@RequestHeader("Accept-Charset") String acceptCharset
		       ,@RequestParam("method2") String method2
		       ,HttpServletRequest httpServletRequest
		       ,NativeWebRequest nativeWebRequest
		       ,WebRequest webRequest
		       ,ServletRequest servletRequest
		       ,@RequestParam Map<String, String> parameters
		       ,@RequestHeader Map<String, String> headers
		       ,@RequestBody String bodys
		       //,@RequestHeader("Cookie") String cookie
		       ){
			//logger.info("userAgent={},accepts={},Accept-Encoding={},Accept-Language={},Accept-Charset={},Cookie={}",new Object[]{userAgent,accepts,acceptEncoding,acceptLanguage,acceptCharset,cookie});
			logger.info("userAgent={},accepts={},Accept-Encoding={},Accept-Language={},method={},method2={}",
					new Object[]{userAgent,accepts,acceptEncoding,acceptLanguage,httpServletRequest.getMethod(),method2});
			
			//Set<String> keySet = headers.keySet();
			Set<Entry<String, String>> entrySet = parameters.entrySet();
			for (Entry<String, String> entry : entrySet) {
				logger.info("======>>Request parameter: {}={}", entry.getKey(), entry.getValue());
			}
			
			entrySet = headers.entrySet();
			for (Entry<String, String> entry : entrySet) {
				logger.info("======>>Request header: {}={}", entry.getKey(), entry.getValue());
			}
			
			logger.info("======>>Request Body: {}", bodys);
			
			Enumeration attributeNames = httpServletRequest.getAttributeNames();
			for (Iterator iterator = entrySet.iterator(); iterator.hasNext();) {
				Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
				logger.info("======>>Request attributes: {}={}", entry.getKey(), entry.getValue());
			}
			
			return "success";
	}
	
	@RequestMapping(value="/restfullMethodTest", method=RequestMethod.GET)
	public String restfullGet() {
		logger.info("*****Invoke restfullGet");
		return "success";
	}
	@RequestMapping(value="/restfullMethodTest", method=RequestMethod.DELETE)
	public String restfullDelete() {
		logger.info("*****Invoke restfullDelete");
		return "success";
	}
	@RequestMapping(value="/restfullMethodTest", method=RequestMethod.POST)
	public String restfullPost() {
		logger.info("*****Invoke restfullPost");
		return "success";
	}
	@RequestMapping(value="/restfullMethodTest", method=RequestMethod.PUT)
	public String restfullPut() {
		logger.info("*****Invoke restfullPut");
		return "success";
	}
}
