package com.desafio.gerenciador.dao;

import com.desafio.gerenciador.model.Moto;
import com.desafio.gerenciador.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Moto> consultarTodasMotos() {
        List<Moto> motos = new ArrayList<>();
        String sql = """
                SELECT v.id, v.modelo, v.fabricante, v.ano, v.preco, m.cilindrada
                FROM veiculos v 
                JOIN motos m ON v.id = m.veiculo_id
                """;

        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Moto moto = new Moto();

                moto.setId(rs.getInt("id"));
                moto.setModelo(rs.getString("modelo"));
                moto.setFabricante(rs.getString("fabricante"));
                moto.setAno(rs.getInt("ano"));
                moto.setPreco(rs.getDouble("preco"));
                moto.setCilindrada(rs.getInt("cilindrada"));

                motos.add(moto);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao tentar obter todas as motos: " + e.getMessage());
        }

        return motos;
    }

    public void atualizarMoto(Moto moto) {
        String sqlVeiculo = """
                UPDATE veiculos SET modelo = ?, fabricante = ?, ano = ?, preco = ?
                WHERE veiculo_id = ?
                """;
        String sqlMoto = """
                UPDATE moto SET cilindrada = ?
                WHERE veiculo_id = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmtVeiculo = connection.prepareStatement(sqlVeiculo);
        PreparedStatement stmtMoto = connection.prepareStatement(sqlMoto)) {

            stmtVeiculo.setString(1, moto.getModelo());
            stmtVeiculo.setString(2, moto.getFabricante());
            stmtVeiculo.setInt(3, moto.getAno());
            stmtVeiculo.setDouble(4, moto.getPreco());
            stmtVeiculo.setInt(5, moto.getId());

            stmtVeiculo.executeUpdate();

            stmtMoto.setInt(1, moto.getCilindrada());
            stmtMoto.setInt(2, moto.getId());

            stmtMoto.executeUpdate();

            System.out.println("Moto atualizada com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao tentar atulizar os dados da moto: " + e.getMessage());
        }

    }
}
