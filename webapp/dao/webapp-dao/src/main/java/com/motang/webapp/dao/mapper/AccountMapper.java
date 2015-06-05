package com.motang.webapp.dao.mapper;

import java.util.List;

import com.motang.webapp.domain.Account;

public interface AccountMapper {
    List<Account> getAll();
    Account getAccountByUsername(String username);
    void insertAccount(Account account);
    
}
