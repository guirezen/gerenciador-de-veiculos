package com.desafio.gerenciador.servico;

import com.desafio.gerenciador.model.Moto;
import com.desafio.gerenciador.service.MotoService;
import com.desafio.gerenciador.utils.DatabaseTestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MotoServiceTest {
    private MotoService motoService;

    @BeforeEach
    public void setup() {
        DatabaseTestUtil.resetDatabase();
        motoService = new MotoService();
    }

    @Test
    public void testCadastrarMotoValida() {
        Moto moto = new Moto();
        moto.setModelo("XRE 300");
        moto.setFabricante("Honda");
        moto.setAno(2022);
        moto.setPreco(25000.0);
        moto.setTipo("moto");
        moto.setCilindrada(300);

        Assertions.assertDoesNotThrow(() -> motoService.cadastrarMoto(moto));
    }

    @Test
    public void testCadastrarMotoComCilindradaInvalida() {
        Moto moto = new Moto();
        moto.setModelo("XRE 300");
        moto.setFabricante("Honda");
        moto.setAno(2022);
        moto.setPreco(25000.0);
        moto.setTipo("moto");
        moto.setCilindrada(0);

        Assertions.assertThrows(IllegalArgumentException.class, () -> motoService.cadastrarMoto(moto));
    }

    @Test
    public void testListarMotos() {
        List<Moto> motos = motoService.listarMotos();
        Assertions.assertNotNull(motos);
    }
}
