package com.desafio.gerenciador.dao;

import com.desafio.gerenciador.model.Carro;
import com.desafio.gerenciador.utils.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Carro> consultarTodosCarros () {
        List<Carro> carros = new ArrayList<>();
        String sql = """
            SELECT v.id, v.modelo, v.fabricante, v.ano, v.preco, c.quantidade_portas, c.tipo_combustivel
            FROM veiculos v
            JOIN carros c ON v.id = c.veiculo_id
          """;

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Carro carro = new Carro();

                carro.setId(rs.getInt("id"));
                carro.setModelo(rs.getString("modelo"));
                carro.setFabricante(rs.getString("fabricante"));
                carro.setAno(rs.getInt("ano"));
                carro.setPreco(rs.getDouble("preco"));
                carro.setQuantidadePortas(rs.getInt("quantidade_portas"));
                carro.setTipoCombustivel(rs.getString("tipo_combustivel"));

                carros.add(carro);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao tentar obter todos os carros: " + e.getMessage());
        }

        return carros;
    }

    public void atualizarCarro(Carro carro) {
        String sqlVeiculo = """
                    UPDATE veiculos SET modelo = ?, fabricante = ?, ano = ?, preco = ?
                    WHERE veiculo_id = ?
                """;
        String sqlCarro = """
                    UPDATE carros SET quantidade_portas = ?, tipo_combustivel = ?
                    WHERE veiculo_id = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmtVeiculo = connection.prepareStatement(sqlVeiculo);
        PreparedStatement stmtCarro = connection.prepareStatement(sqlCarro)) {

            stmtVeiculo.setString(1, carro.getModelo());
            stmtVeiculo.setString(2, carro.getFabricante());
            stmtVeiculo.setInt(3, carro.getAno());
            stmtVeiculo.setDouble(4, carro.getPreco());
            stmtVeiculo.setInt(5, carro.getId());
            stmtVeiculo.executeUpdate();

            stmtCarro.setInt(1, carro.getQuantidadePortas());
            stmtCarro.setString(2, carro.getTipoCombustivel());
            stmtCarro.setInt(3, carro.getId());
            stmtCarro.executeUpdate();

            System.out.println("Carro atualizado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao tentar atualizar o carro: " + e.getMessage());
        }

    }
}
