package com.motang.test.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.motang.domain.Account;
import com.motang.service.AccountService;

@ContextConfiguration(locations={"/aop/test-aop.xml"})
public class AccountServiceTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	private AccountService accountService;
	
	@Test
	public void testListAll(){
		List<Account> listAll = accountService.listAll();
		for (Account account : listAll) {
			System.out.printf("AccountServiceTest.testListAll(): %s \n", account);
		}
	}
}
