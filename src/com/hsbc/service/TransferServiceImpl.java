package com.hsbc.service;

import com.hsbc.exception.AccountNotFoundException;
import com.hsbc.exception.NotAdequateMoney;
import com.hsbc.model.Account;
import com.hsbc.repository.Repository;

public class TransferServiceImpl implements TransferService {
    Repository repository = null;

    public TransferServiceImpl(Repository repository) {
        this.repository = repository;
    }
    @Override
    public void transfer(String source, String destination, double amount) {
        // load source and destination accounts
        Account sourceAccount = repository.loadAccount(source);
        if(sourceAccount == null) {
            throw new AccountNotFoundException(source);
        }

        Account destinationAccount = repository.loadAccount(destination);
        if(destinationAccount == null) {
            throw new AccountNotFoundException(destination);
        }

        // transaction possible
        if(sourceAccount.getBalance() < amount) {
            throw new NotAdequateMoney(source);
        }

        // credit and debit
        // debit from source
        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        destinationAccount.setBalance(destinationAccount.getBalance() + amount);

        // update in database
        repository.updateAccount(sourceAccount);
        repository.updateAccount(destinationAccount);
    }
}
