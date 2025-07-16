package org.example.routes;

import io.javalin.Javalin;
import org.example.di.AppModule;

public class UsuarioRoutes {

    public static void registerRoutes(Javalin app) {
        var usuarioController = AppModule.getUsuarioController();

        app.get("/usuarios", usuarioController::findAllUsuarios);

        app.post("/usuarios", usuarioController::createUsuario);

        app.put("/usuarios/{id}", usuarioController::updateUsuario);

        app.delete("/usuarios/{id}", usuarioController::deleteUsuario);

        app.post("/login", usuarioController::login);


    }
}