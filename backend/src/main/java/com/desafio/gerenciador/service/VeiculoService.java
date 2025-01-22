package com.desafio.gerenciador.service;

import com.desafio.gerenciador.dao.VeiculoDAO;
import com.desafio.gerenciador.model.Veiculo;

import java.util.List;

public class VeiculoService {
    private final VeiculoDAO veiculoDao;

    public VeiculoService() {
        this.veiculoDao = new VeiculoDAO();
    }

    public void cadastrarVeiculo(Veiculo veiculo) {
        validarAtributosGerais(veiculo);

        veiculoDao.inserirVeiculo(veiculo.getModelo(), veiculo.getFabricante(), veiculo.getAno(), veiculo.getPreco(), veiculo.getTipo());
    }

    public List<Veiculo> listarVeiculos() {
        return veiculoDao.consultarTodosVeiculos();
    }

    public List<Veiculo> buscarVeiculosPorFiltro(String tipo, String modelo, Integer ano) {
        if (tipo != null && !tipo.isEmpty() && !tipo.equals("carro") && !tipo.equals("moto")) {
            throw new IllegalArgumentException("Tipo inválido: deve ser 'carro', 'moto', ou vazio");
        }
        if (modelo != null && modelo.isEmpty()) {
            throw new IllegalArgumentException("Modelo inválido: não pode ser vazio.");
        }
        if (ano != null && ano <= 1886) {
            throw new IllegalArgumentException("Ano inválido: Deve ser 1886 ou posterior");
        }

        return veiculoDao.consultarVeiculosPorFiltro(
                tipo != null ? tipo : "",
                modelo != null ? modelo : "",
                ano
        );
    }

    public void atualizarVeiculo(Veiculo veiculo) {
        if (veiculo.getId() <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        validarAtributosGerais(veiculo);

        veiculoDao.atualizarVeiculo(veiculo.getId(), veiculo.getModelo(), veiculo.getFabricante(), veiculo.getAno(), veiculo.getPreco());
    }

    public void removerVeiculo(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        veiculoDao.removerVeiculo(id);
    }

    public void validarAtributosGerais(Veiculo veiculo) {
        if (veiculo.getModelo() == null || veiculo.getModelo().isEmpty()) {
            throw new IllegalArgumentException("Modelo inválido: não pode ser nulo ou vazio.");
        }
        if (veiculo.getFabricante() == null || veiculo.getFabricante().isEmpty()) {
            throw new IllegalArgumentException("Fabricante inválido: não pode ser nulo ou vazio.");
        }
        if (veiculo.getAno() < 1886) {
            throw new IllegalArgumentException("Ano inválido: deve ser 1886 ou posterior.");
        }
        if (veiculo.getPreco() <= 0) {
            throw new IllegalArgumentException("Preço inválido: deve ser maior que zero.");
        }
        if (veiculo.getTipo() == null || (!veiculo.getTipo().equals("carro") && !veiculo.getTipo().equals("moto"))) {
            throw new IllegalArgumentException("Tipo inválido: deve ser 'carro' ou 'moto'.");
        }
    }
}
