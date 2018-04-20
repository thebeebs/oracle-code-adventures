/* Copyright © 2016 Oracle and/or its affiliates. All rights reserved. */
package com.example.rest;
 
import java.util.Optional;
import java.util.Properties;
import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
 
@SpringBootApplication
public class App {
 
    // Get PORT and HOST from Environment or set default
    public static final Optional<String> host;
    public static final Optional<String> port;
    public static final Properties myProps = new Properties();
 
    static {
        host = Optional.ofNullable(System.getenv("HOSTNAME"));
        port = Optional.ofNullable(System.getenv("PORT"));
    }
 
    /**
     * Start the server.
     */
    public static void main(String[] args) {
        // Set properties
        myProps.setProperty("server.address", host.orElse("localhost"));
        myProps.setProperty("server.port", port.orElse("8080"));
 
        SpringApplication app = new SpringApplication(App.class);
        app.setDefaultProperties(myProps);
        app.run(args);
 
        System.out.println("Server started");
 
        //runGetRequest();
        //runDatabaseQuery();
    }
 
    /**
     * Performs a simple GET request and prints the result to the log.
     */
    private static void runGetRequest() {
 
    	// sample URL
        String url = "http://ip.jsontest.com/";
        CloseableHttpResponse response = null;
 
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            response = httpclient.execute(httpGet);
            String content = EntityUtils.toString(response.getEntity());
            System.out.println("Server response: " + content);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException ie) {
                System.out.println(ie);
            }
        }    
    }
 
    /**
     * Performs a call to the database.
     */
    private static void runDatabaseQuery() {
        String host = "myHost";
        String database = "myDatabase";
        String user = "myUsername";
        String password = "myPassword";
        DBConnection db = new DBConnection("jdbc:mysql://" 
                + host + "/" 
                + database + "?user=" 
                + user + "&password=" 
                + password);
        try {
            db.readData();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}