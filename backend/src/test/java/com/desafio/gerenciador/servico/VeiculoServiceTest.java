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
        DatabaseTestUtil.resetDatabase();
        veiculoService = new VeiculoService();
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
    public void testCadastrarVeiculoComPrecoInvalido() {
        Veiculo veiculo = new Veiculo();
        veiculo.setModelo("Civic");
        veiculo.setFabricante("Honda");
        veiculo.setAno(1900);
        veiculo.setPreco(-1);
        veiculo.setTipo("carro");

        Assertions.assertThrows(IllegalArgumentException.class, () -> veiculoService.cadastrarVeiculo(veiculo));
    }
    @Test
    public void testCadastrarVeiculoComModeloNulo() {
        Veiculo veiculo = new Veiculo();
        veiculo.setModelo(null);
        veiculo.setFabricante("Honda");
        veiculo.setAno(1900);
        veiculo.setPreco(100000.0);
        veiculo.setTipo("carro");

        Assertions.assertThrows(IllegalArgumentException.class, () -> veiculoService.cadastrarVeiculo(veiculo));
    }
    @Test
    public void testCadastrarVeiculoComModeloVazio() {
        Veiculo veiculo = new Veiculo();

        veiculo.setModelo("");
        veiculo.setFabricante("Honda");
        veiculo.setAno(1900);
        veiculo.setPreco(100000.0);
        veiculo.setTipo("carro");

        Assertions.assertThrows(IllegalArgumentException.class, () -> veiculoService.cadastrarVeiculo(veiculo));
    }
    @Test
    public void testCadastrarVeiculoComFabricanteNulo() {
        Veiculo veiculo = new Veiculo();
        veiculo.setModelo("Civic");
        veiculo.setFabricante(null);
        veiculo.setAno(1900);
        veiculo.setPreco(100000.0);
        veiculo.setTipo("carro");

        Assertions.assertThrows(IllegalArgumentException.class, () -> veiculoService.cadastrarVeiculo(veiculo));
    }
    @Test
    public void testCadastrarVeiculoComFabricanteVazio() {
        Veiculo veiculo = new Veiculo();

        veiculo.setModelo("Civic");
        veiculo.setFabricante("");
        veiculo.setAno(1900);
        veiculo.setPreco(100000.0);
        veiculo.setTipo("carro");

        Assertions.assertThrows(IllegalArgumentException.class, () -> veiculoService.cadastrarVeiculo(veiculo));
    }

    @Test
    public void testCadastrarVeiculoComTipoNulo() {
        Veiculo veiculo = new Veiculo();
        veiculo.setModelo("Civic");
        veiculo.setFabricante("Honda");
        veiculo.setAno(1900);
        veiculo.setPreco(100000.0);
        veiculo.setTipo(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> veiculoService.cadastrarVeiculo(veiculo));
    }
    @Test
    public void testCadastrarVeiculoComTipoDiferente() {
        Veiculo veiculo = new Veiculo();
        veiculo.setModelo("Civic");
        veiculo.setFabricante("Honda");
        veiculo.setAno(1900);
        veiculo.setPreco(100000.0);
        veiculo.setTipo("caminhão");

        Assertions.assertThrows(IllegalArgumentException.class, () -> veiculoService.cadastrarVeiculo(veiculo));
    }

    @Test
    public void testBuscarVeiculosPorFiltro() {
        List<Veiculo> veiculos = veiculoService.buscarVeiculosPorFiltro("carro", null, null);
        Assertions.assertNotNull(veiculos);
        Assertions.assertFalse(veiculos.isEmpty());
    }

    @Test
    public void testAtualizarVeiculo() {
        Veiculo veiculo = new Veiculo();

        veiculo.setId(1);
        veiculo.setModelo("Civic Atualizado");
        veiculo.setFabricante("Honda");
        veiculo.setAno(2023);
        veiculo.setPreco(110000.0);
        veiculo.setTipo("carro");

        Assertions.assertDoesNotThrow(() -> veiculoService.atualizarVeiculo(veiculo));
    }

    @Test
    public void testRemoverVeiculo() {
        Assertions.assertDoesNotThrow(() -> veiculoService.removerVeiculo(1));
    }
}
