package utils;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConn{

public static int getRowCount(String quary) throws SQLException, ClassNotFoundException {
int count=0;
    Class.forName("org.postgresql.Driver");

    Connection connection = null;

    connection = DriverManager.getConnection(
            "jdbc:postgresql://ec2-52-30-64-220.eu-west-1.compute.amazonaws.com:5432/kraydel", "kraydel",
            "M!tr@!Kr@ydelQa");
    if (connection != null) {
        Statement stmt = connection.createStatement();
        String sql;
        sql = quary;
        ResultSet rs = stmt.executeQuery(sql);
       while (rs.next()) {
           count++;
        }
    } else {
        System.out.println("Failed to make connection!");
    }

    connection.close();
return count;

}
    public static int getValueInt(String quary,String element) throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");

        Connection connection = null;

        connection = DriverManager.getConnection(
                "jdbc:postgresql://ec2-52-30-64-220.eu-west-1.compute.amazonaws.com:5432/kraydel", "kraydel",
                "M!tr@!Kr@ydelQa");
        int value=0;
        if (connection != null) {
            Statement stmt = connection.createStatement();
            String sql;
            sql = quary;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
               value=rs.getInt(element);
            }
        } else {
            System.out.println("Failed to make connection!");
        }
        connection.close();
        return value;

    }
    public static String getValueString(String quary,String element) throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");

        Connection connection = null;

        connection = DriverManager.getConnection(
                "jdbc:postgresql://ec2-52-30-64-220.eu-west-1.compute.amazonaws.com:5432/kraydel", "kraydel",
                "M!tr@!Kr@ydelQa");
        String value=null;
        if (connection != null) {
            Statement stmt = connection.createStatement();
            String sql;
            sql = quary;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                value=rs.getString(element);
            }
        } else {
            System.out.println("Failed to make connection!");
        }
        connection.close();
        return value;

    }


    public static ResultSet getDBData(String quary) throws SQLException, ClassNotFoundException {
        int count=0;
        Class.forName("org.postgresql.Driver");

        Connection connection = null;
        ResultSet rs=null;
        connection = DriverManager.getConnection(
                "jdbc:postgresql://ec2-52-30-64-220.eu-west-1.compute.amazonaws.com:5432/kraydel", "kraydel",
                "M!tr@!Kr@ydelQa");
        if (connection != null) {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(quary);
        } else {
            System.out.println("Failed to make connection!");
        }

        connection.close();
        return rs;

    }
}
