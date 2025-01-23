package com.desafio.gerenciador.controller;

import com.desafio.gerenciador.model.Carro;
import com.desafio.gerenciador.service.CarroService;

import java.util.List;

public class CarroController {
    private final CarroService carroService;

    public CarroController() {
        this.carroService = new CarroService();
    }

    public void cadastrarCarro(Carro carro) {
        try {
            carroService.cadastrarCarro(carro);
            System.out.println("Carro cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao cadastrar carro: " + e.getMessage());
        }
    }

    public List<Carro> listarCarros() {
        return carroService.listarCarros();
    }

    public void atualizarCarro(Carro carro) {
        try {
            carroService.atualizarCarro(carro);
            System.out.println("Carro atualizado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao atualizar carro: " + e.getMessage());
        }
    }
}
