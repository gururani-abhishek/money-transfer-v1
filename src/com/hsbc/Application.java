package com.hsbc;

import com.hsbc.repository.JdbcRepositoryImpl;
import com.hsbc.repository.Repository;
import com.hsbc.service.TransferService;
import com.hsbc.service.TransferServiceImpl;

public class Application {
    public static void main(String[] args) {
        Repository repository = new JdbcRepositoryImpl();
        TransferService transferService = new TransferServiceImpl(repository);

        transferService.transfer("atul", "sneha", 100);
    }
}
