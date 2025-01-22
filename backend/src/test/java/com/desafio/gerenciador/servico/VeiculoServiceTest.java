package com.desafio.gerenciador.servico;

import com.desafio.gerenciador.model.Veiculo;
import com.desafio.gerenciador.service.VeiculoService;
import com.desafio.gerenciador.utils.DatabaseConnection;
import com.desafio.gerenciador.utils.DatabaseTestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class VeiculoServiceTest {
    private VeiculoService veiculoService;

    @BeforeEach
    public void setup() {
        veiculoService = new VeiculoService();
        DatabaseTestUtil.resetDatabase();
    }

    @Test
    public void testCadastrarVeiculoValido() {
        Veiculo veiculo = new Veiculo();

        veiculo.setModelo("Civic");
        veiculo.setFabricante("Honda");
        veiculo.setAno(2022);
        veiculo.setPreco(100000.0);
        veiculo.setTipo("carro");

        Assertions.assertDoesNotThrow(() -> veiculoService.cadastrarVeiculo(veiculo));
    }

    @Test
    public void testCadastrarVeiculoComAnoInvalido() {
        Veiculo veiculo = new Veiculo();
        veiculo.setModelo("Civic");
        veiculo.setFabricante("Honda");
        veiculo.setAno(1800);
        veiculo.setPreco(100000.0);
        veiculo.setTipo("carro");

        Assertions.assertThrows(IllegalArgumentException.class, () -> veiculoService.cadastrarVeiculo(veiculo));
    }

    @Test
    public void testBuscarVeiculosPorFiltro() {
        List<Veiculo> veiculos = veiculoService.buscarVeiculosPorFiltro("carro", null, null);
        Assertions.assertNotNull(veiculos);
        Assertions.assertFalse(veiculos.isEmpty());
    }
}
