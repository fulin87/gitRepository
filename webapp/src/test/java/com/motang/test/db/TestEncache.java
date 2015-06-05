package com.motang.test.db;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.motang.dao.mapper.AccountMapper;
import com.motang.domain.Account;

@ContextConfiguration(locations={"/db/mybatis/test-db.xml"})
@TransactionConfiguration(defaultRollback=true)
@TestExecutionListeners({TransactionalTestExecutionListener.class})
//@Transactional
public class TestEncache extends AbstractJUnit4SpringContextTests{
	private Logger logger = LoggerFactory.getLogger(TestEncache.class);

	@Autowired
	private AccountMapper accountMapper;
	
	ExecutorService threadPool = Executors.newFixedThreadPool(10);
	int count = 0;
	
	Runnable[] testTasks = new Runnable[100];
	
	@Test
	@Transactional
	public void testFirstCache(){
		accountMapper.getAll();
	}
	
	//@Repeat(100)
	public void testCache(){
		testTasks[count++] = new Runnable() {

			@Override
			public void run() {
				testGetAccounts();
			}
			
		};
		
		
		if (count >= 100) {
			for (int i = 0; i < count; i++) {
				threadPool.submit(testTasks[i]);
			}
			try {
				threadPool.awaitTermination(200, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//@Test
	//@Transactional
	public void testGetAccounts() {
		Random random = new Random();
		List<Account> all = accountMapper.getAll();
		Account account = accountMapper.getAccountByUsername("motang"+random.nextInt(14));
		logger.info("getAll----- {} ", "first");
		logger.info("accounts = {}", "first", all);
		
		all = accountMapper.getAll();
		account = accountMapper.getAccountByUsername("motang"+random.nextInt(10));
		logger.info("getAll----- {} ", "second");
		logger.info("accounts-{} = {}",  "second", all);
		
		 all = accountMapper.getAll();
		 account = accountMapper.getAccountByUsername("motang"+random.nextInt(10));
		logger.info("getAll----- {} ", "third");
		logger.info("accounts-{} = {}",  "third", all);
		
		all = accountMapper.getAll();
		account = accountMapper.getAccountByUsername("motang"+random.nextInt(10));
		logger.info("getAll----- {} ", "four");
		logger.info("accounts-{} = {}",  "four", all);
		
		account = accountMapper.getAccountByUsername("motang"+random.nextInt(10));
		
		for (Account a : all) {
			logger.info("account = {}", a);
		}
		
		logger.info("account = {}", account);
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
