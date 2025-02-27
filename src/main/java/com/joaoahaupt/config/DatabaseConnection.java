package com.joaoahaupt.config;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/scribble";
    private static final String USER = "root";
    private static final String PASSWORD = "Jazz!Skull15";

    private static Connection connection;
    private static Jdbi jdbi;

    public static Jdbi getJdbi() {
        if (jdbi == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                jdbi = Jdbi.create(connection);
                jdbi.installPlugin(new SqlObjectPlugin());
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao conectar no banco!", e);
            }
        }
        return jdbi;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connection = null;
                jdbi = null;
            }
        }
    }
}
