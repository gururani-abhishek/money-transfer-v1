package com.hsbc.db;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnectionFactory {
    // driver init
    // connection create

    public static Connection getConnection() {
        Connection con = null;
        try {
            DriverManager.registerDriver(new Driver());
            String url = "jdbc:mysql://localhost:3306/bankdb"; // sql db
            String user = "root"; // sql user name
            String pass = "root12345"; // sql user pass

            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException eObject) {
            eObject.printStackTrace();
        }
        return con;
    }
}
