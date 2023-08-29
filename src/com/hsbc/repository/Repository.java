package com.hsbc.repository;

import com.hsbc.model.Account;

public interface Repository {
    Account loadAccount(String number);
    void updateAccount(Account account);
}
