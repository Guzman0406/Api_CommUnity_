package org.example.routes;

import io.javalin.Javalin;
import java.util.Objects;
import org.example.controller.TipoReporteController;
import org.example.di.AppModule;

public class TipoReporteRoutes {
    public static void registerRoutes(Javalin app) {
        TipoReporteController controller = AppModule.getTipoReporteController();
        Objects.requireNonNull(controller);
        app.get("/tipos-reporte", controller::getAll);
        Objects.requireNonNull(controller);
        app.post("/tipos-reporte", controller::create);
        Objects.requireNonNull(controller);
        app.put("/tipos-reporte/{id}", controller::update);
        Objects.requireNonNull(controller);
        app.delete("/tipos-reporte/{id}", controller::delete);
    }
}