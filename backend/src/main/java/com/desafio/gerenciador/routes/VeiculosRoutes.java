package com.desafio.gerenciador.routes;

import com.desafio.gerenciador.model.Veiculo;
import com.desafio.gerenciador.service.VeiculoService;
import com.google.gson.Gson;

import static spark.Spark.*;

public class VeiculosRoutes {
    private static final VeiculoService veiculoService = new VeiculoService();
    private static final Gson gson = new Gson();

    public static void registerRoutes() {

        get("/veiculos", (req, res) -> {
            res.type("application/json");
            return gson.toJson(veiculoService.listarVeiculos());
        });

        post("/veiculos", (req, res) -> {
            Veiculo veiculo = gson.fromJson(req.body(), Veiculo.class);
            veiculoService.cadastrarVeiculo(veiculo);
            res.status(201);
            return "Veículo cadastrado com sucesso!";
        });

        put("/veiculos/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            Veiculo veiculo = gson.fromJson(req.body(), Veiculo.class);
            veiculo.setId(id);
            veiculoService.atualizarVeiculo(veiculo);
            return "Veículo atualizado com sucesso!";
        });

        delete("/veiculos/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            veiculoService.removerVeiculo(id);
            return "Veículo removido com sucesso!";
        });

        get("/veiculos/filtro", (req, res) -> {
            String tipo = req.queryParams("tipo");
            String modelo = req.queryParams("modelo");
            Integer ano = req.queryParams("ano") != null ? Integer.parseInt(req.queryParams("ano")) : null;
            res.type("application/json");
            return gson.toJson(veiculoService.buscarVeiculosPorFiltro(tipo, modelo, ano));
        });
    }
}
