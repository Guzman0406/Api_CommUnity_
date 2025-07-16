package org.example.routes;

import io.javalin.Javalin;
import java.util.Objects;
import org.example.controller.NivelUrgenciaController;
import org.example.di.AppModule;

public class NivelUrgenciaRoutes {
    public static void registerRoutes(Javalin app) {
        NivelUrgenciaController nivelUrgenciaController = AppModule.getNivelUrgenciaController();
        Objects.requireNonNull(nivelUrgenciaController);
        app.get("/niveles-urgencia", nivelUrgenciaController::findAll);
        Objects.requireNonNull(nivelUrgenciaController);
        app.post("/niveles-urgencia", nivelUrgenciaController::create);
        Objects.requireNonNull(nivelUrgenciaController);
        app.put("/niveles-urgencia/{id}", nivelUrgenciaController::update);
        Objects.requireNonNull(nivelUrgenciaController);
        app.delete("/niveles-urgencia/{id}", nivelUrgenciaController::delete);
    }
}