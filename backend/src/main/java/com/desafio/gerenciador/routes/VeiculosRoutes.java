package com.desafio.gerenciador.routes;

import com.desafio.gerenciador.factory.VeiculoFactory;
import com.desafio.gerenciador.model.Carro;
import com.desafio.gerenciador.model.Moto;
import com.desafio.gerenciador.model.Veiculo;
import com.desafio.gerenciador.service.VeiculoService;
import com.google.gson.Gson;

import java.util.Map;

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
            Veiculo veiculo = VeiculoFactory.criarVeiculo(req.body());

            veiculoService.cadastrarVeiculo(veiculo);
            res.status(201);
            return "Veículo cadastrado com sucesso!";
        });


        put("/veiculos/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));

            Veiculo veiculo = VeiculoFactory.criarVeiculo(req.body());
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
            String searchTerm = req.queryParams("searchTerm");
            res.type("application/json");

            return gson.toJson(veiculoService.buscarVeiculosPorFiltro(searchTerm));
        });
    }
}
