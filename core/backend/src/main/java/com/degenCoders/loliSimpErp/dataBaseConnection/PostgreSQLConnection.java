
package com.degenCoders.loliSimpErp.dataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.degenCoders.loliSimpErp.config.Config;

public class PostgreSQLConnection {

    public static void connect() {
        String url = Config.get("db.url");
        String user = Config.get("db.username");
        String password = Config.get("db.password");

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to connect.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
