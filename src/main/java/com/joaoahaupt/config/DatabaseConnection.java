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


    public static Jdbi getJdbi() {
        try {
            Jdbi jdbi = Jdbi.create(DriverManager.getConnection(URL, USER, PASSWORD));
            jdbi.installPlugin(new SqlObjectPlugin());
            return jdbi;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar no banco!", e);
        }
    }

    public static void closeConnection(Connection connection) throws SQLException {

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}
