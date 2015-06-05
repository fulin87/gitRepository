package com.motang.test.aop;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.util.CollectionUtils;

import com.motang.dao.mapper.AccountMapper;
import com.motang.domain.Account;

@ContextConfiguration(locations={"/aop/test-aop.xml"})
//@TransactionConfiguration(defaultRollback=true)
//@TestExecutionListeners({TransactionalTestExecutionListener.class})
//@Transactional
public class TestAop extends AbstractJUnit4SpringContextTests {
	@Autowired
	private AccountMapper accountMapper;
	
	//@Test
	public void testServiceLog(){
		List<Account> all = accountMapper.getAll();
		if (!CollectionUtils.isEmpty(all)){
			for (Account account : all) {
				System.out.println(account);
			}
		}
	}
	@Test
	public void testServiceLog2(){
		Account account = accountMapper.getAccountByUsername("motang1");
		System.out.println(account);
	}
}
