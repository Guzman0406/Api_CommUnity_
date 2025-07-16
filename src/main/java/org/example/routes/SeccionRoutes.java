package org.example.routes;

import io.javalin.Javalin;
import java.util.Objects;
import org.example.controller.SeccionController;
import org.example.di.AppModule;

public class SeccionRoutes {
    public static void registerRoutes(Javalin app) {
        SeccionController seccionController = AppModule.getSeccionController();
        Objects.requireNonNull(seccionController);
        app.get("/secciones", seccionController::findAllSeccion);
        Objects.requireNonNull(seccionController);
        app.post("/secciones", seccionController::createSeccion);
        Objects.requireNonNull(seccionController);
        app.put("/secciones/{id}", seccionController::updateSeccion);
        Objects.requireNonNull(seccionController);
        app.delete("/secciones/{id}", seccionController::deleteSeccion);
    }
}