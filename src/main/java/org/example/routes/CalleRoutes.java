package org.example.routes;

import io.javalin.Javalin;
import org.example.di.AppModule;

public class CalleRoutes {

    public static void registerRoutes(Javalin app) {
        var calleController = AppModule.getCalleController();

        app.get("/calles", calleController::findAll);
        app.post("/calles", calleController::create);
        app.put("/calles/{id}", calleController::update);
        app.delete("/calles/{id}", calleController::delete);
    }
}
