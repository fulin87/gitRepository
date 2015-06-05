package com.motang.webapp.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.motang.webapp.dao.mapper.AccountMapper;
import com.motang.webapp.domain.Account;
import com.motang.webapp.service.AccountService;

@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService{
    private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    @Qualifier("accountMapper")
    private AccountMapper accountMapper;
    
    @Autowired
    private DataSource datasource;
    
    @Override
    public List<Account> listAll() {
        List<Account> all = accountMapper.getAll();
        if (all != null) {
            for (Account account : all) {
                logger.info("listAll---------------------{}", account);
            }
        }
        
        Connection connection = DataSourceUtils.getConnection(datasource);
        logger.info("listAll===================>>>>{}", connection);
        
        return all;
    }

	@Override
	public Account getAccountByUsername(String username) {
		Account account = accountMapper.getAccountByUsername(username);
		logger.info("get {}" + account);
		 Connection connection = DataSourceUtils.getConnection(datasource);
	    logger.info("getAccountByUsername==================>>>>{}", connection);
		return account;
	}

	@Override
	//@Rollback(true)
	public void insertAccount(Account account) {
		accountMapper.insertAccount(account);
	}
    

}
