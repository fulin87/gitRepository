package com.motang.test.db;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.motang.dao.mapper.AccountMapper;
import com.motang.domain.Account;

@ContextConfiguration(locations={"/db/mybatis/test-db.xml"})
@TransactionConfiguration(defaultRollback=true)
@TestExecutionListeners({TransactionalTestExecutionListener.class})
public class TestEncache2 extends AbstractJUnit4SpringContextTests{
	private Logger logger = LoggerFactory.getLogger(TestEncache2.class);
	
	private Account account;
	
	@Autowired
	private AccountMapper accountMapper;
	
	@Before
	public void initData() {
		account = new Account();
		
		account.setFirstName("motang");
		account.setLastName("Tang");
		account.setAddress1("");
		account.setAddress2("");
		account.setBannerName("");
		account.setBannerOption(true);
		account.setCity("sz");
		account.setFavouriteCategoryId("dd");
		account.setLanguagePreference("zh");
		account.setPassword("motang");
		account.setPhone("123");
		account.setState("GD");
		account.setZip("");
		account.setCountry("CN");
		account.setEmail("morly.tang@cignacmb.com");
		
		listAll();
	}
	
	//@Test
	//@Transactional
	public void test() {
		for (int i = 6; i < 10; i++) {
			listAll();
			logger.info("listAll --- {}", i);
			
			account.setUsername("test" + i);
			accountMapper.insertAccount(account);
			logger.info("insertAccount <<< {}", i);
			//listAll();
			
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		logger.info("Done");
		
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void listAll() {
		List<Account> all = accountMapper.getAll();
		if (!CollectionUtils.isEmpty(all)){
			for (Account account : all) {
				System.out.println(account);
			}
		}
	}
	
}
