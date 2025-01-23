package com.desafio.gerenciador.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String ENV = "test";
//    private static final String ENV = "production";

    private static final String PROD_URL = "jdbc:postgresql://localhost:5433/gerenciador_veiculos";
    private static final String TEST_URL = "jdbc:postgresql://localhost:5434/gerenciador_veiculos_test";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        String url = "test".equals(ENV) ? TEST_URL : PROD_URL;

        System.out.println("Conectando ao banco: " + url);
        return DriverManager.getConnection(url, USER, PASSWORD);
    }
}
