package com.mihaimiuta.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbDriver {
    private static DbDriver driver = null;

    public Connection connection;

    private DbDriver() throws SQLException {
        String host = System.getenv("DB_HOST");
        String port = System.getenv("DB_PORT");
        String name = System.getenv("DB_NAME");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        String url = String.format("jdbc:postgresql://%s:%s/%s", host, port, name);
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        connection = DriverManager.getConnection(url, props);
    }

    public static DbDriver getInstance() throws SQLException {
        if (driver == null) {
            driver = new DbDriver();
        }

        return driver;
    }
}
