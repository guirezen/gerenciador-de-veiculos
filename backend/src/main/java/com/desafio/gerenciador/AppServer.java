package com.desafio.gerenciador;

import com.desafio.gerenciador.routes.CarrosRoutes;
import com.desafio.gerenciador.routes.MotosRoutes;
import com.desafio.gerenciador.routes.VeiculosRoutes;
import com.desafio.gerenciador.utils.CorsConfig;

import static spark.Spark.*;

public class AppServer {
    public static void main(String[] args) {
        port(8080);

        CorsConfig.enableCORS("*", "GET,POST,PUT,DELETE", "Content-Type,Authorization");

        VeiculosRoutes.registerRoutes();
        CarrosRoutes.registerRoutes();
        MotosRoutes.registerRoutes();
    }
}
