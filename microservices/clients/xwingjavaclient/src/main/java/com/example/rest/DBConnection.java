/* Copyright © 2016 Oracle and/or its affiliates. All rights reserved. */

package com.example.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {

    private final String jdbcURL;
 
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
 
    public DBConnection(String jdbcURL){
        this.jdbcURL = jdbcURL;
    }
 
    /**
     * Sets up the connection to the database and executes a sample query.
     */
    public void readData() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL);
            statement = connection.createStatement();
            
            // sample SQL query
            String sqlQuery = "select * from SampleTable;";
            resultSet = statement.executeQuery(sqlQuery);
            getResultSet(resultSet);
        } finally{
            close();
        }
    }
 
    /**
     * Prints the x and y coordinates of the query result to the log.
     */
    private void getResultSet(ResultSet resultSet) throws Exception {
        while (resultSet.next()) {
            System.out.println("X: " + resultSet.getString("xCoordinate"));
            System.out.println("Y: " + resultSet.getString("yCoordinate"));
        }
    }
 
    private void close(){
        try {
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        } catch(Exception e){
        	System.out.println(e);
        }
    }

}
