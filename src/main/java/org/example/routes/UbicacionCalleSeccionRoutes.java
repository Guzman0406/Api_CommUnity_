package org.example.routes;

import io.javalin.Javalin;
import org.example.controller.UbicacionCalleSeccionController;
import org.example.controller.CalleController;
import org.example.di.AppModule;

public class UbicacionCalleSeccionRoutes {

    public static void registerUbicacionCalleSeccion(Javalin app) {
        UbicacionCalleSeccionController ubicacionController = AppModule.getUbicacionCalleSeccionController();
        CalleController calleController = AppModule.getCalleController(); // 👈 Declaras el controller

        app.get("/ubicaciones", ubicacionController::findAll);
        app.post("/ubicaciones", ubicacionController::create);
        app.put("/ubicaciones/{id}", ubicacionController::update);
        app.delete("/ubicaciones/{id}", ubicacionController::delete);

        // Ejemplo: http://localhost:7001/id-ubicacion/3/5,
        //es para que despues de que metan la calle y su seccion les devuelva el id de su combinacion
        app.get("/id-ubicacion/{id_seccion}/{id_calle}", ubicacionController::getIdUbicacion);

        // Ruta para mostrar calles por seccion
        //Ejemplo
        app.get("/calles-por-seccion/{id_seccion}", ubicacionController::findCallesPorSeccion);
    }
}
