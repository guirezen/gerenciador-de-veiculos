package com.desafio.gerenciador.servico;

import com.desafio.gerenciador.model.Carro;
import com.desafio.gerenciador.model.Moto;
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
    public void testCadastrarCarroValido() {
        Carro carro = criarCarroValido();
        Assertions.assertDoesNotThrow(() -> veiculoService.cadastrarVeiculo(carro));
    }

    @Test
    public void testCadastrarCarroSemQuantidadePortas() {
        Carro carro = criarCarroValido();
        carro.setQuantidadePortas(0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> veiculoService.cadastrarVeiculo(carro));
    }

    @Test
    public void testCadastrarCarroSemTipoCombustivel() {
        Carro carro = criarCarroValido();
        carro.setTipoCombustivel(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> veiculoService.cadastrarVeiculo(carro));
    }

    @Test
    public void testCadastrarMotoValida() {
        Moto moto = criarMotoValida();
        Assertions.assertDoesNotThrow(() -> veiculoService.cadastrarVeiculo(moto));
    }

    @Test
    public void testCadastrarMotoSemCilindrada() {
        Moto moto = criarMotoValida();
        moto.setCilindrada(0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> veiculoService.cadastrarVeiculo(moto));
    }

    @Test
    public void testCadastrarVeiculoSemTipo() {
        Carro carro = criarCarroValido();
        carro.setTipo(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> veiculoService.cadastrarVeiculo(carro));
    }

    @Test
    public void testCadastrarVeiculoComTipoInvalido() {
        Carro carro = criarCarroValido();
        carro.setTipo("caminhao");
        Assertions.assertThrows(IllegalArgumentException.class, () -> veiculoService.cadastrarVeiculo(carro));
    }


    @Test
    public void testAtualizarCarroValido() {
        Carro carro = criarCarroValido();
        carro.setId(1);
        carro.setModelo("Onix Plus Turbo");
        Assertions.assertDoesNotThrow(() -> veiculoService.atualizarVeiculo(carro));
    }

    @Test
    public void testAtualizarCarroSemQuantidadePortas() {
        Carro carro = criarCarroValido();
        carro.setId(1);
        carro.setQuantidadePortas(0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> veiculoService.atualizarVeiculo(carro));
    }

    @Test
    public void testAtualizarMotoValida() {
        Moto moto = criarMotoValida();
        moto.setId(2);
        moto.setModelo("CB 500X");
        Assertions.assertDoesNotThrow(() -> veiculoService.atualizarVeiculo(moto));
    }

    @Test
    public void testAtualizarMotoSemCilindrada() {
        Moto moto = criarMotoValida();
        moto.setId(2);
        moto.setCilindrada(0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> veiculoService.atualizarVeiculo(moto));
    }

    @Test
    public void testAtualizarVeiculoSemTipo() {
        Carro carro = criarCarroValido();
        carro.setId(3);
        carro.setTipo(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> veiculoService.atualizarVeiculo(carro));
    }

    @Test
    public void testAtualizarVeiculoComTipoInvalido() {
        Carro carro = criarCarroValido();
        carro.setId(3);
        carro.setTipo("caminhao");
        Assertions.assertThrows(IllegalArgumentException.class, () -> veiculoService.atualizarVeiculo(carro));
    }

    
    private Carro criarCarroValido() {
        Carro carro = new Carro();
        carro.setModelo("Onix Plus");
        carro.setFabricante("Chevrolet");
        carro.setAno(2023);
        carro.setPreco(85000.0);
        carro.setTipo("carro");
        carro.setQuantidadePortas(4);
        carro.setTipoCombustivel("flex");
        return carro;
    }

    private Moto criarMotoValida() {
        Moto moto = new Moto();
        moto.setModelo("CB 500F");
        moto.setFabricante("Honda");
        moto.setAno(2023);
        moto.setPreco(32000.0);
        moto.setTipo("moto");
        moto.setCilindrada(500);
        return moto;
    }
}
