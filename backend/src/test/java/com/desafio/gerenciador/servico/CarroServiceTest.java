package com.desafio.gerenciador.servico;

import com.desafio.gerenciador.model.Carro;
import com.desafio.gerenciador.service.CarroService;
import com.desafio.gerenciador.utils.DatabaseTestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CarroServiceTest {
     private CarroService carroService;

     @BeforeEach
     public void setup() {
         DatabaseTestUtil.resetDatabase();
         carroService = new CarroService();
     }

     @Test
    public void testCadastrarCarroValido() {
         Carro carro = new Carro();
         carro.setModelo("Onix");
         carro.setFabricante("Chevrolet");
         carro.setAno(2021);
         carro.setPreco(80000.0);
         carro.setTipo("carro");
         carro.setQuantidadePortas(4);
         carro.setTipoCombustivel("flex");

         Assertions.assertDoesNotThrow(() -> carroService.cadastrarCarro(carro));
     }

     @Test
    public void testCadastrarCarroComQuantidadePortasInvalida() {
         Carro carro = new Carro();
         carro.setModelo("Onix");
         carro.setFabricante("Chevrolet");
         carro.setAno(2021);
         carro.setPreco(80000.0);
         carro.setTipo("carro");
         carro.setQuantidadePortas(0);
         carro.setTipoCombustivel("flex");

         Assertions.assertThrows(IllegalArgumentException.class, () -> carroService.cadastrarCarro(carro));
     }

    @Test
    public void testCadastrarCarroComTipoCombustivelNulo() {
        Carro carro = new Carro();
        carro.setModelo("Onix");
        carro.setFabricante("Chevrolet");
        carro.setAno(2021);
        carro.setPreco(80000.0);
        carro.setTipo("carro");
        carro.setQuantidadePortas(4);
        carro.setTipoCombustivel(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> carroService.cadastrarCarro(carro));
    }
    @Test
    public void testCadastrarCarroComTipoCombustivelVazio() {
        Carro carro = new Carro();
        carro.setModelo("Onix");
        carro.setFabricante("Chevrolet");
        carro.setAno(2021);
        carro.setPreco(80000.0);
        carro.setTipo("carro");
        carro.setQuantidadePortas(4);
        carro.setTipoCombustivel("");

        Assertions.assertThrows(IllegalArgumentException.class, () -> carroService.cadastrarCarro(carro));
    }

     @Test
    public void testListarCarros() {
         List<Carro> carros = carroService.listarCarros();

         Assertions.assertNotNull(carros);
         Assertions.assertFalse(carros.isEmpty());
         Assertions.assertEquals(2, carros.size());

         Carro carro = carros.get(0);
         Assertions.assertEquals("Onix", carro.getModelo());
         Assertions.assertEquals("Chevrolet", carro.getFabricante());
         Assertions.assertEquals(2021, carro.getAno());
         Assertions.assertEquals(80000.0, carro.getPreco());
         Assertions.assertEquals(4, carro.getQuantidadePortas());
         Assertions.assertEquals("flex", carro.getTipoCombustivel());
     }

    @Test
    public void testAtualizarCarro() {
        Carro carro = new Carro();
        carro.setId(1);
        carro.setModelo("Onix Atualizado");
        carro.setFabricante("Chevrolet");
        carro.setAno(2022);
        carro.setPreco(85000.0);
        carro.setTipo("carro");
        carro.setQuantidadePortas(4);
        carro.setTipoCombustivel("etanol");

        Assertions.assertDoesNotThrow(() -> carroService.atualizarCarro(carro));
    }
}
