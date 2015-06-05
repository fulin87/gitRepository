package com.motang.webapp.service;

import java.util.List;

import com.motang.webapp.domain.Account;

public interface AccountService {
    List<Account> listAll();
    Account getAccountByUsername(String username);
    void insertAccount(Account account);
}
