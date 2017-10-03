package com.aut;


import org.junit.Assert;

import java.sql.*;

import static com.aut.APIFactory.connection;

public class DatabaseFactory extends BaseClass {

    public static Connection setDatabaseConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(
                System.getenv("DATABASE_URL"), System.getenv("DB_USER"),
                System.getenv("DB_PASSWORD"));
        return connection;
    }

    public static ResultSet getDBData(String quary) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;
        if (connection != null) {
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = stmt.executeQuery(quary);
        } else {
            System.out.println("Failed to make connection!");
        }
        return resultSet;
    }

    public static void resetDB() throws SQLException {
        while (results.previous()) {
            //reset table to position one
        }
    }

    public static void validateResultSet(String sql) throws SQLException, ClassNotFoundException {
        System.out.println(sql);
        ResultSet resultSet = getDBData(sql);
        Assert.assertEquals("No record found: SQL: " + sql, true, resultSet.next());
    }


}
