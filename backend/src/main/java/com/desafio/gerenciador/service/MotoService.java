package com.desafio.gerenciador.service;

import com.desafio.gerenciador.dao.MotoDAO;
import com.desafio.gerenciador.model.Moto;

import java.util.List;

public class MotoService {
    private final MotoDAO motoDAO;
    private final VeiculoService veiculoService;


    public MotoService() {
        this.motoDAO = new MotoDAO();
        this.veiculoService = new VeiculoService();
    }

    public void cadastrarMoto(Moto moto) {
        veiculoService.validarAtributosGerais(moto);

        if (moto.getCilindrada() <= 0) {
            throw new IllegalArgumentException("Cilindrada inválida.");
        }

        motoDAO.inserirMoto(moto);
    }

    public List<Moto> listarMotos() {
        return motoDAO.consultarTodasMotos();
    }

    public void atualizarMoto(Moto moto) {
        veiculoService.validarAtributosGerais(moto);

        if (moto.getCilindrada() <= 0) {
            throw new IllegalArgumentException("Cilindrada inválida.");
        }
        if (moto.getId() <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        motoDAO.atualizarMoto(moto);
    }
}
