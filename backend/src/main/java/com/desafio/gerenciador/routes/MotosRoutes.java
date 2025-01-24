package com.desafio.gerenciador.routes;

import com.desafio.gerenciador.model.Moto;
import com.desafio.gerenciador.service.MotoService;
import com.google.gson.Gson;

import static spark.Spark.*;

public class MotosRoutes {
    private static final MotoService motoService = new MotoService();
    private static final Gson gson = new Gson();

    public static void registerRoutes() {

        get("/motos", (req, res) -> {
            res.type("application/json");
            return gson.toJson(motoService.listarMotos());
        });


        post("/motos", (req, res) -> {
            Moto moto = gson.fromJson(req.body(), Moto.class);
            motoService.cadastrarMoto(moto);
            res.status(201);
            return "Moto cadastrada com sucesso!";
        });


        put("/motos/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            Moto moto = gson.fromJson(req.body(), Moto.class);
            moto.setId(id);
            motoService.atualizarMoto(moto);
            return "Moto atualizada com sucesso!";
        });
    }
}
