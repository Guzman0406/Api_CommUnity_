package org.example.routes;

import io.javalin.Javalin;
import org.example.di.AppModule;

public class EstadoReporteRoutes {

    public static void registerRoutes(Javalin app) {
        var estadoReporteController = AppModule.getEstadoReporteController();

        app.get("/estados-reporte", estadoReporteController::findAll);
        app.post("/estados-reporte", estadoReporteController::create);
        app.put("/estados-reporte/{id}", estadoReporteController::update);
        app.delete("/estados-reporte/{id}", estadoReporteController::delete);
    }
}
