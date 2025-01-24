package com.desafio.gerenciador;

import com.desafio.gerenciador.routes.CarrosRoutes;
import com.desafio.gerenciador.routes.MotosRoutes;
import com.desafio.gerenciador.routes.VeiculosRoutes;

import static spark.Spark.*;

public class AppServer {
    public static void main(String[] args) {
        port(8080);

        VeiculosRoutes.registerRoutes();
        CarrosRoutes.registerRoutes();
        MotosRoutes.registerRoutes();
    }
}
