package com.desafio.gerenciador.routes;

import com.desafio.gerenciador.model.Carro;
import com.desafio.gerenciador.service.CarroService;
import com.google.gson.Gson;

import static spark.Spark.*;

public class CarrosRoutes {
    private static final CarroService carroService = new CarroService();
    private static final Gson gson = new Gson();

    public static void registerRoutes() {

        get("/carros", (req, res) -> {
            res.type("application/json");
            return gson.toJson(carroService.listarCarros());
        });


        post("/carros", (req, res) -> {
            Carro carro = gson.fromJson(req.body(), Carro.class);
            carroService.cadastrarCarro(carro);
            res.status(201);
            return "Carro cadastrado com sucesso!";
        });


        put("/carros/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            Carro carro = gson.fromJson(req.body(), Carro.class);
            carro.setId(id);
            carroService.atualizarCarro(carro);
            return "Carro atualizado com sucesso!";
        });
    }
}
