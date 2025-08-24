package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver PostgreSQL não encontrado", e);
        }
        
        Properties props = new Properties();
        props.setProperty("user", System.getProperty("db.user", "postgres"));
        props.setProperty("password", System.getProperty("db.password", "makil22"));
        
        String url = System.getProperty("db.url", "jdbc:postgresql://localhost:5432/midiasdao");
        
        return DriverManager.getConnection(url, props);
    }

}