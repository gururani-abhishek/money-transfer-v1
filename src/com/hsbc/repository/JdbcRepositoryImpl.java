package com.hsbc.repository;

import com.hsbc.db.DataBaseConnectionFactory;
import com.hsbc.exception.DataAccessException;
import com.hsbc.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcRepositoryImpl implements Repository {

    @Override
    public Account loadAccount(String number) {
        Account fetchedAccount = null;
        try {
            Connection con = DataBaseConnectionFactory.getConnection();
            String sqlCommand = "select * from account where number=?";
            PreparedStatement preparedStatement = con.prepareStatement(sqlCommand);
            preparedStatement.setString(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();

            // idk why?
            if(resultSet.next()) {
                fetchedAccount = new Account();
                fetchedAccount.setNumber(number);
                fetchedAccount.setBalance(resultSet.getDouble(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fetchedAccount;
    }

    @Override
    public void updateAccount(Account account) {
        try {
            Connection con = DataBaseConnectionFactory.getConnection();
            String sqlCommand = "update account set balance=? where number=?";
            PreparedStatement preparedStatement = con.prepareStatement(sqlCommand);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setString(2, account.getNumber());
            preparedStatement.executeUpdate(); // forgot to execute the query.
        } catch (SQLException e) {
            throw new DataAccessException(e.toString());
        }
    }
}
