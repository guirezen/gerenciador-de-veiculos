package com.desafio.gerenciador.service;

import com.desafio.gerenciador.dao.VeiculoDAO;
import com.desafio.gerenciador.model.Carro;
import com.desafio.gerenciador.model.Moto;
import com.desafio.gerenciador.model.Veiculo;

import java.util.List;

public class VeiculoService {
    private final VeiculoDAO veiculoDao;

    public VeiculoService() {
        this.veiculoDao = new VeiculoDAO();
    }

    public void cadastrarVeiculo(Veiculo veiculo) {
        validarAtributosGerais(veiculo);

        if (veiculo.getTipo().equals("carro")) {
            if (!(veiculo instanceof Carro)) {
                throw new IllegalArgumentException("Os atributos específicos de Carro são obrigatórios.");
            }
            CarroService carroService = new CarroService();
            carroService.cadastrarCarro((Carro) veiculo);
        } else if (veiculo.getTipo().equals("moto")) {
            if (!(veiculo instanceof Moto)) {
                throw new IllegalArgumentException("Os atributos específicos de Moto são obrigatórios.");
            }
            MotoService motoService = new MotoService();
            motoService.cadastrarMoto((Moto) veiculo);
        } else {
            throw new IllegalArgumentException("Tipo de veículo inválido. Deve ser 'carro' ou 'moto'.");
        }
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
        validarAtributosGerais(veiculo);

        if (veiculo.getTipo().equals("carro")) {
            if (!(veiculo instanceof Carro)) {
                throw new IllegalArgumentException("Os atributos específicos de Carro são obrigatórios.");
            }
            CarroService carroService = new CarroService();
            carroService.atualizarCarro((Carro) veiculo);
        } else if (veiculo.getTipo().equals("moto")) {
            if (!(veiculo instanceof Moto)) {
                throw new IllegalArgumentException("Os atributos específicos de Moto são obrigatórios.");
            }
            MotoService motoService = new MotoService();
            motoService.atualizarMoto((Moto) veiculo);
        } else {
            throw new IllegalArgumentException("Tipo de veículo inválido. Deve ser 'carro' ou 'moto'.");
        }
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
