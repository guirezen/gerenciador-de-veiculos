package com.desafio.gerenciador.service;

import com.desafio.gerenciador.dao.CarroDAO;
import com.desafio.gerenciador.model.Carro;

import java.util.List;

public class CarroService {
    private final CarroDAO carroDAO;

    public CarroService() {
        this.carroDAO = new CarroDAO();
    }

    public void cadastrarCarro(Carro carro) {
        if (carro.getQuantidadePortas() <= 0) {
            throw new IllegalArgumentException("Quantidade de portas inválida.");
        }
        if (carro.getTipoCombustivel() == null || carro.getTipoCombustivel().isEmpty()) {
            throw new IllegalArgumentException("Tipo de combustível inválido.");
        }

        carroDAO.inserirCarro(carro);
    }

    public List<Carro> listarCarros() {
        return carroDAO.consultarTodosCarros();
    }

    public void atualizarCarro(Carro carro) {
        if (carro.getQuantidadePortas() <= 0) {
            throw new IllegalArgumentException("Quantidade de portas inválidas.");
        }
        if (carro.getTipoCombustivel() == null || carro.getTipoCombustivel().isEmpty()) {
            throw new IllegalArgumentException("Tipo de combustível inválido.");
        }
        if (carro.getId() <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        carroDAO.atualizarCarro(carro);
    }
}
