package com.revature.utility;

import org.postgresql.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {

    public static Connection getConnection() throws SQLException {

        String url = System.getenv("gcp_url");
        String username = System.getenv("postgres_username");
        String password = System.getenv("postgresPS");

        DriverManager.registerDriver(new Driver());

        return DriverManager.getConnection(url, username, password);
    }
}
