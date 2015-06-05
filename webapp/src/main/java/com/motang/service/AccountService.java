package com.motang.service;

import java.util.List;

import com.motang.domain.Account;

public interface AccountService {
    List<Account> listAll();
    Account getAccountByUsername(String username);
    void insertAccount(Account account);
}
