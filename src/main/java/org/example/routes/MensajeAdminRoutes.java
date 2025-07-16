package org.example.routes;

import io.javalin.Javalin;
import org.example.di.AppModule;

public class MensajeAdminRoutes {

    public static void registerRoutes(Javalin app) {
        var mensajeAdminController = AppModule.getMensajeAdminController();

        app.get("/mensajes-admin", mensajeAdminController::findAll);
        app.post("/mensajes-admin", mensajeAdminController::create);
        app.put("/mensajes-admin/{id}", mensajeAdminController::update);
        app.delete("/mensajes-admin/{id}", mensajeAdminController::delete);


        app.get("/avisos/ultimos7dias", mensajeAdminController::findAvisosUltimos7Dias); //AVISOS < 7 DIAS
        app.get("/avisos/mayores7dias", mensajeAdminController::findAvisosMayoresA7Dias); //para buscar mensajes admin mayores a 7 dias
        app.get("/avisos/{id}", mensajeAdminController::findById); //BUSCA AVISOS POR SU ID, SOLO MUESTRA UNOS PS


    }
}
