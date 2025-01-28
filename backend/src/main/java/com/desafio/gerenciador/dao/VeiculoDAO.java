package com.desafio.gerenciador.dao;

import com.desafio.gerenciador.model.Veiculo;
import com.desafio.gerenciador.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VeiculoDAO {

    public List<Map<String, Object>> consultarTodosVeiculos() {
        String sql = """
                    SELECT 
                        v.id, 
                        v.modelo, 
                        v.fabricante, 
                        v.ano, 
                        v.preco, 
                        v.tipo,
                        c.quantidade_portas, 
                        c.tipo_combustivel,
                        m.cilindrada
                    FROM veiculos v
                    LEFT JOIN carros c ON v.id = c.veiculo_id
                    LEFT JOIN motos m ON v.id = m.veiculo_id
                """;

        List<Map<String, Object>> veiculos = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> veiculo = new HashMap<>();
                veiculo.put("id", rs.getInt("id"));
                veiculo.put("modelo", rs.getString("modelo"));
                veiculo.put("fabricante", rs.getString("fabricante"));
                veiculo.put("ano", rs.getInt("ano"));
                veiculo.put("preco", rs.getDouble("preco"));
                veiculo.put("tipo", rs.getString("tipo"));
                veiculo.put("quantidade_portas", rs.getObject("quantidade_portas"));
                veiculo.put("tipo_combustivel", rs.getObject("tipo_combustivel"));
                veiculo.put("cilindrada", rs.getObject("cilindrada"));

                veiculos.add(veiculo);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao obter a lista de veículos: " + e.getMessage());
        }

        return veiculos;
    }

    public List<Veiculo> consultarVeiculosPorFiltro(String searchTerm) {
        String sql = """
                    SELECT
                        v.id, v.modelo, v.fabricante, v.ano, v.preco, v.tipo
                    FROM veiculos v
                    WHERE v.tipo ILIKE ?
                       OR v.modelo ILIKE ?
                       OR v.fabricante ILIKE ?
                       OR CAST(v.ano AS TEXT) ILIKE ?
                """;
        List<Veiculo> veiculos = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            String like = "%" + searchTerm + "%";

            stmt.setString(1, like);
            stmt.setString(2, like);
            stmt.setString(3, like);
            stmt.setString(4, like);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Veiculo veiculo = new Veiculo();

                    veiculo.setId(rs.getInt("id"));
                    veiculo.setModelo(rs.getString("modelo"));
                    veiculo.setFabricante(rs.getString("fabricante"));
                    veiculo.setAno(rs.getInt("ano"));
                    veiculo.setPreco(rs.getDouble("preco"));
                    veiculo.setTipo(rs.getString("tipo"));

                    veiculos.add(veiculo);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao obter a lista de veículos por filtro: " + e.getMessage());
        }

        return veiculos;
    }

    public void removerVeiculo(int id) {
        String sql = "DELETE FROM veiculos WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsDelete = stmt.executeUpdate();

            if (rowsDelete > 0) {
                System.out.println("Veículo removido com sucesso.");
            } else {
                System.out.println("Nenhum veículo encontrado com o ID fornecido.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao tentar deletar veículo: " + e.getMessage());
        }
    }

    public Veiculo consultarVeiculoPorID(int id) {
        String sql = "SELECT * FROM veiculos WHERE id = ?";
        Veiculo veiculo = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    veiculo = new Veiculo();
                    veiculo.setId(rs.getInt("id"));
                    veiculo.setModelo(rs.getString("modelo"));
                    veiculo.setFabricante(rs.getString("fabricante"));
                    veiculo.setAno(rs.getInt("ano"));
                    veiculo.setPreco(rs.getDouble("preco"));
                    veiculo.setTipo(rs.getString("tipo"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao tentar obter o veículo com o ID informado.");
        }

        return veiculo;
    }
}
