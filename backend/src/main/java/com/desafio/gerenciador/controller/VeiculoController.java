package com.desafio.gerenciador.controller;

import com.desafio.gerenciador.model.Veiculo;
import com.desafio.gerenciador.service.VeiculoService;

import java.util.List;

public class VeiculoController {
    private final VeiculoService veiculoService;

    public VeiculoController() {
        this.veiculoService = new VeiculoService();
    }

    public void cadastrarVeiculo(Veiculo veiculo) {
        try {
            veiculoService.cadastrarVeiculo(veiculo);
            System.out.println("Veículo cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao cadastrar veículo: " + e.getMessage());
        }
    }

    public List<Veiculo> listarVeiculos() {
        return veiculoService.listarVeiculos();
    }

    public List<Veiculo> buscarVeiculosPorFiltro(String tipo, String modelo, Integer ano) {
        try {
            return veiculoService.buscarVeiculosPorFiltro(tipo, modelo, ano);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao buscar veículos: " + e.getMessage());
            return List.of();
        }
    }

    public void atualizarVeiculo(Veiculo veiculo) {
        try {
            veiculoService.atualizarVeiculo(veiculo);
            System.out.println("Veículo atualizado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao atualizar veículos: " + e.getMessage());
        }
    }

    public void removerVeiculo(int id) {
        try {
            veiculoService.removerVeiculo(id);
            System.out.println("Veículo removido com sucesso!");
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao deletar veículo: " + e.getMessage());
        }
    }
}
