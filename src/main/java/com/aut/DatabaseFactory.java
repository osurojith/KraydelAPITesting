package com.aut;


import org.junit.Assert;

import java.sql.*;

import static com.aut.APIFactory.connection;

public class DatabaseFactory {

    public static Connection setDatabaseConnection() throws ClassNotFoundException, SQLException {
        Connection connection=null;
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(
                "jdbc:postgresql://ec2-52-31-57-4.eu-west-1.compute.amazonaws.com:25421/kraydel", "kraydel",
                "M!tr@!Kr@ydelQa");
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
        connection.close();
        return resultSet;
    }

    public static void validateResultSet(String sql) throws SQLException, ClassNotFoundException {
       ResultSet resultSet= getDBData(sql);
       Assert.assertEquals( "No record found: SQL: " + sql,true, resultSet.next());

    }

}
