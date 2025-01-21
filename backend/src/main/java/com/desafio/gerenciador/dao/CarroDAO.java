package com.desafio.gerenciador.dao;

import com.desafio.gerenciador.model.Carro;
import com.desafio.gerenciador.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarroDAO {
    public void inserirCarro(Carro carro) {

        try (Connection connection = DatabaseConnection.getConnection()) {
            int veiculoId = inserirVeiculo(carro, connection);

            if (veiculoId > 0) {
                inserirDetalhesCarro(carro, veiculoId, connection);
            }
            System.out.println("Carro inserido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao tentar inserir carro: " + e.getMessage());
        }

    }

    private int inserirVeiculo(Carro carro, Connection connection) throws SQLException {
        String sqlVeiculo = "INSERT INTO veiculos (modelo, fabricante, ano, preco, tipo) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement stmtVeiculo = connection.prepareStatement(sqlVeiculo)) {
            stmtVeiculo.setString(1, carro.getModelo());
            stmtVeiculo.setString(2, carro.getFabricante());
            stmtVeiculo.setInt(3, carro.getAno());
            stmtVeiculo.setDouble(4, carro.getPreco());
            stmtVeiculo.setString(5, "carro");

            try (ResultSet rs = stmtVeiculo.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        return -1;
    }

    private void inserirDetalhesCarro(Carro carro, int veiculoId, Connection connection) throws SQLException {
        String sqlCarro = "INSERT INTO carros (veiculo_id, quantidade_portas, tipo_combustivel) VALUES (?, ?, ?)";

        try (PreparedStatement stmtCarro = connection.prepareStatement(sqlCarro)) {
            stmtCarro.setInt(1, veiculoId);
            stmtCarro.setInt(2, carro.getQuantidadePortas());
            stmtCarro.setString(3, carro.getTipoCombustivel());
            stmtCarro.executeUpdate();
        }

    }
}
