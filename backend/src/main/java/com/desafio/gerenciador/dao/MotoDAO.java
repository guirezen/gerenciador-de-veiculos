package com.desafio.gerenciador.dao;

import com.desafio.gerenciador.model.Moto;
import com.desafio.gerenciador.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MotoDAO {


    public void inserirMoto(Moto moto) {
        String sqlVeiculo = "INSERT INTO veiculos (modelo, fabricante, ano, preco, tipo) VALUES (?, ?, ?, ?, ?)";
        String sqlMoto = "INSERT INTO motos (veiculo_id, cilindrada) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection()) {
            int veiculoId = inserirVeiculo(moto, connection);

            if (veiculoId > 0) {
                inserirDetalhesMoto(moto, veiculoId, connection);
            }

            System.out.println("Moto inserida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao tentar inserir a moto: " + e.getMessage());
        }
    }

    private int inserirVeiculo(Moto moto, Connection connection) throws SQLException {
        String sqlVeiculo = "INSERT INTO veiculos (modelo, fabricante, ano, preco, tipo) VALUES (?, ?, ?, ?, ?) RETURNING id";

        try (PreparedStatement stmtVeiculo = connection.prepareStatement(sqlVeiculo)) {
            stmtVeiculo.setString(1, moto.getModelo());
            stmtVeiculo.setString(2, moto.getFabricante());
            stmtVeiculo.setInt(3, moto.getAno());
            stmtVeiculo.setDouble(4, moto.getPreco());
            stmtVeiculo.setString(5, "moto");

            try (ResultSet rs = stmtVeiculo.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        return -1;
    }

    private void inserirDetalhesMoto(Moto moto, int veiculoId, Connection connection) throws SQLException {
        String sqlMoto = "INSERT INTO motos (veiculo_id, cilindrada) VALUES (?, ?)";

        try (PreparedStatement stmtMoto = connection.prepareStatement(sqlMoto)) {
            stmtMoto.setInt(1, veiculoId);
            stmtMoto.setInt(2, moto.getCilindrada());
            stmtMoto.executeUpdate();
        }
    }
}
