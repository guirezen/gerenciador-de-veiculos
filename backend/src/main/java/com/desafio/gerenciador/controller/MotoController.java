package com.desafio.gerenciador.controller;

import com.desafio.gerenciador.model.Moto;
import com.desafio.gerenciador.service.MotoService;

import java.util.List;

public class MotoController {
    private final MotoService motoService;

    public MotoController() {
        this.motoService = new MotoService();
    }

    public void cadastrarMoto(Moto moto) {
        try {
            motoService.cadastrarMoto(moto);
            System.out.println("Moto cadastrada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao cadastrar moto: " + e.getMessage());
        }
    }

    public List<Moto> listarMotos() {
        return motoService.listarMotos();
    }

    public void atualizarMoto(Moto moto) {
        try {
            motoService.atualizarMoto(moto);
            System.out.println("Moto atualizada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao atualizar moto: " + e.getMessage());
        }
    }
}
