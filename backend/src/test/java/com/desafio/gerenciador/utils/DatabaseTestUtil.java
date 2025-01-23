package com.desafio.gerenciador.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTestUtil {
    public static void resetDatabase() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement()) {

            stmt.execute("SET search_path TO public;");

            stmt.execute("DROP TABLE IF EXISTS motos CASCADE;");
            stmt.execute("DROP TABLE IF EXISTS carros CASCADE;");
            stmt.execute("DROP TABLE IF EXISTS veiculos CASCADE;");

            stmt.execute("""
            CREATE TABLE veiculos (
                id SERIAL PRIMARY KEY,
                modelo VARCHAR(100) NOT NULL,
                fabricante VARCHAR(100) NOT NULL,
                ano INT NOT NULL CHECK (ano >= 1886),
                preco DECIMAL(10, 2) NOT NULL,
                tipo VARCHAR(10) NOT NULL CHECK (tipo IN ('carro', 'moto'))
            );
        """);
            stmt.execute("""
            CREATE TABLE carros (
                veiculo_id INT PRIMARY KEY REFERENCES veiculos (id) ON DELETE CASCADE,
                quantidade_portas INT NOT NULL CHECK (quantidade_portas > 0),
                tipo_combustivel VARCHAR(10) NOT NULL
            );
        """);
            stmt.execute("""
            CREATE TABLE motos (
                veiculo_id INT PRIMARY KEY REFERENCES veiculos (id) ON DELETE CASCADE,
                cilindrada INT NOT NULL CHECK (cilindrada > 0)
            );
        """);

            stmt.execute("""
            INSERT INTO veiculos (modelo, fabricante, ano, preco, tipo)
            VALUES 
                ('Onix', 'Chevrolet', 2021, 80000.00, 'carro'),
                ('Civic', 'Honda', 2020, 90000.00, 'carro'),
                ('XRE 300', 'Honda', 2022, 25000.00, 'moto');
        """);
            stmt.execute("""
            INSERT INTO carros (veiculo_id, quantidade_portas, tipo_combustivel)
            VALUES 
                (1, 4, 'flex'),
                (2, 4, 'gasolina');
        """);
            stmt.execute("""
            INSERT INTO motos (veiculo_id, cilindrada)
            VALUES 
                (3, 300);
        """);

            System.out.println("Banco de testes resetado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao resetar o banco de dados de teste: " + e.getMessage());
        }
    }
}
