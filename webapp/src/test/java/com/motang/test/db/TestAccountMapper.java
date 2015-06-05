package com.motang.test.db;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.motang.dao.mapper.AccountMapper;
import com.motang.domain.Account;
import com.motang.service.AccountService;

//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/db/mybatis/test-db.xml"})
@TransactionConfiguration(defaultRollback=true)
@TestExecutionListeners({TransactionalTestExecutionListener.class})
@Transactional
public class TestAccountMapper extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private AccountService accountService;
	
	//@Test
	public void testInsertAcccount(){
		Account account = new Account();
		account.setUsername("motang15");
		account.setFirstName("motang1");
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
		
		accountService.insertAccount(account);
	}
	
	//@Test
	public void testGetAll() {
		ExecutorService threadPool = Executors.newFixedThreadPool(2);
		
		for (int i = 0; i < 10; i++) {
			threadPool.execute(new Runnable() {
				
				@Override
				public void run() {
					List<Account> all = accountMapper.getAll();
					if (!CollectionUtils.isEmpty(all)){
						for (Account account : all) {
							System.out.println(account);
						}
					}
					
					Random random = new Random();
					try {
						int nextInt = random.nextInt(10);
						System.err.printf("%s=====>>>Wait %s \n", Thread.currentThread().getName(), nextInt);
						TimeUnit.SECONDS.sleep(nextInt);
						
						if (nextInt > 5 ) {
							Account account = accountMapper.getAccountByUsername("j2ee");
							if (account != null) {
								System.out.printf("%s message as <<< %s \n", Thread.currentThread().getName(), account);
							}
						} else {
							List<Account> accounts = accountService.listAll();
							if (!CollectionUtils.isEmpty(accounts)){
								for (Account account : accounts) {
									System.out.printf("%s message as <<< %s \n", Thread.currentThread().getName(), account);
								}
							}
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			});
		}
	
		try {
			threadPool.awaitTermination(1000, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
