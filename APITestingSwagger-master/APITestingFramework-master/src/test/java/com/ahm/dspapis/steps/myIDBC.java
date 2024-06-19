package com.ahm.dspapis.steps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class myIDBC {
    public static void main(String[] args) {
        try {
            // Establishing a connection
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/apitestingformat",
                    "root",
                    "Root123"
            );

            // Creating a statement
            Statement statement = connection.createStatement();
            // Executing a query
            ResultSet resultSet = statement.executeQuery("SELECT * FROM BOOKDETAILS");

            // Iterating through the result set
            while (resultSet.next()) {
                System.out.println(resultSet.getString("firstname"));
                System.out.println(resultSet.getString("totalprice"));
            }

            // Closing the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
