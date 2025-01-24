package com.desafio.gerenciador.factory;

import com.desafio.gerenciador.model.Carro;
import com.desafio.gerenciador.model.Moto;
import com.desafio.gerenciador.model.Veiculo;
import com.google.gson.Gson;

import java.util.Map;

public class VeiculoFactory {
    public static Veiculo criarVeiculo(String json) {
        Map<String, Object> requestData = new Gson().fromJson(json, Map.class);
        String tipo = (String) requestData.get("tipo");

        if (tipo == null || tipo.isEmpty()) {
            throw new IllegalArgumentException("O campo 'tipo' é obrigatório.");
        }

        if (tipo.equals("carro")) {
            return new Gson().fromJson(json, Carro.class);
        } else if (tipo.equals("moto")) {
            return new Gson().fromJson(json, Moto.class);
        } else {
            throw new IllegalArgumentException("Tipo de veículo inválido. Deve ser 'carro' ou 'moto'.");
        }
    }
}
