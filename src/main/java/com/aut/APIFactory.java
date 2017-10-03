package com.aut;
import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeSuite;

import java.sql.Connection;
import java.sql.SQLException;

import static com.aut.DatabaseFactory.setDatabaseConnection;

public class APIFactory {
    public static Connection connection = null;


    @BeforeSuite
    public void init() throws SQLException, ClassNotFoundException {
        connection= setDatabaseConnection();
    }
    @AfterSuite
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
