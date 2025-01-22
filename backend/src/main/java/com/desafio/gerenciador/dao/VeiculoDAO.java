package com.desafio.gerenciador.dao;

import com.desafio.gerenciador.model.Veiculo;
import com.desafio.gerenciador.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {
    public void inserirVeiculo(String modelo, String fabricante, int ano, double preco, String tipo) {
        String sql = "INSERT INTO veiculos (modelo, fabricante, ano, preco, tipo) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, modelo);
            stmt.setString(2, fabricante);
            stmt.setInt(3, ano);
            stmt.setDouble(4, preco);
            stmt.setString(5, tipo);

            stmt.executeUpdate();
            System.out.println("Veículo inserido com sucesso!");


        } catch (SQLException e) {
            System.err.println("Erro ao inserir veículo: " + e.getMessage());
        }
    }

    public List<Veiculo> consultarTodosVeiculos() {
        String sql = "SELECT * FROM veiculos";

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

          return mapearVeiculos(rs);

        } catch (SQLException e) {
            System.err.println("Erro ao obter a lista de veículos: " + e.getMessage());
        }

        return new ArrayList<>();
    }

    public List<Veiculo> consultarVeiculosPorFiltro(String tipo, String modelo, Integer ano) {
        String sql = """
            SELECT * FROM veiculos 
            WHERE (? = '' OR tipo = ?)
                AND (? = '' OR modelo ILIKE ?)
                AND (? IS NULL OR ano = ?)
        """;

        List<Veiculo> veiculos = new ArrayList<>();

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, tipo);
            stmt.setString(2, tipo);
            stmt.setString(3, modelo);
            stmt.setString(4, "%" + modelo + "%");
            if (ano == null) {
                stmt.setNull(5, java.sql.Types.INTEGER);
                stmt.setNull(6, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(5, ano);
                stmt.setInt(6, ano);
            }

            try(ResultSet rs = stmt.executeQuery()) {
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

    public void atualizarVeiculo(int id, String modelo, String fabricante, int ano, double preco) {
        String sql = "UPDATE veiculos SET modelo = ?, fabricante = ?, ano = ?, preco = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, modelo);
            stmt.setString(2, fabricante);
            stmt.setInt(3, ano);
            stmt.setDouble(4, preco);
            stmt.setInt(5, id);

            int rowsUpdate = stmt.executeUpdate();

            if (rowsUpdate > 0) {
                System.out.println("Veículo atualizado com sucesso!");
            } else {
                System.out.println("Nenhum veículo encontrado com o ID informado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao tentar atualizar o veículo: " + e.getMessage());
        }
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

            try (ResultSet rs = stmt.executeQuery()){
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

    private List<Veiculo> mapearVeiculos(ResultSet rs) throws SQLException {
        List<Veiculo> veiculos = new ArrayList<>();

            while (rs.next()) {
                Veiculo veiculo = new Veiculo();

                veiculo.setId(rs.getInt("id"));
                veiculo.setAno(rs.getInt("ano"));
                veiculo.setFabricante(rs.getString("fabricante"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setTipo(rs.getString("tipo"));
                veiculo.setPreco(rs.getDouble("preco"));

                veiculos.add(veiculo);
            }

            return veiculos;
    }
}
