package org.example.routes;

import io.javalin.Javalin;
import org.example.di.AppModule;
import org.example.controller.ReporteController; // Asegúrate de que esta importación exista

public class ReporteRoutes {

    public static void registerRoutes(Javalin app) {
        ReporteController reporteController = AppModule.getReporteController();

// total de reportes (número)
        app.get("/reportes/total", reporteController::getTotalReportes);

//los reportes de 9 en 9 dependiendo de su paginacion
        // EJEMPLO: http://localhost:7001/reportes?page=1
        app.get("/reportes", reporteController::getReportesPaginados);

// total de reportes resueltos
        app.get("/reportes/resueltos/total", reporteController::getTotalReportesResueltos);

// tiempo promedio en resolver
        app.get("/reportes/tiempo-promedio", reporteController::getTiempoPromedioResolucion);

// Filtra reportes por sección
// Ejemplo: /reportes/seccion/3
        app.get("/reportes/seccion/{id_seccion}", reporteController::findBySeccion);

// Filtra reportes por tipo de incidente
// Ejemplo:  /reportes/tipo?id_tipo=2
        app.get("/reportes/tipo", reporteController::findByTipo);

// Obtiene todos los reportes
/* Ejemplo:  /reportes
        app.get("/reportes", reporteController::getAllReportes);*/

// Crea un nuevo reporte los datos son desde usuario hasta descripcion, lo demas se agrega en automatico
// Ejemplo: /reportes
        app.post("/reportes", reporteController::createReporte);

// Actualiza un reporte específico identificado por su id
// Ejemplo:  /reportes/1
        app.put("/reportes/{id}", reporteController::updateReporte);

// Marca un reporte específico como resuelto o cambia su estado a resuelto
// Ejemplo:  /reportes/{id}/resolver
        app.post("/reportes/{id}/resolver", reporteController::resolverReporte);


        // obtiene los reportes dependiendo del estado
        // Ejemplo: /reportes/estado?id_estado=1
        app.get("/reportes/estado", reporteController::findByEstado);


//obtiene todos los reportes de un solo usuario
//reportes/usuario?id_usuario=5
        app.get("/reportes/usuario", reporteController::findByUsuario);




// Obtiene un reporte específico por su id, este va al final pq luego afecta a los demas
// Ejemplo: /reportes/1
        app.get("/reportes/{id}", reporteController::getReporteById);


    }
}