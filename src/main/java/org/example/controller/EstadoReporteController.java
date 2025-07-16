package org.example.controller;

import io.javalin.http.Context;
import org.example.model.EstadoReporte;
import org.example.service.EstadoReporteService;

import java.sql.SQLException;
import java.util.List;

public class EstadoReporteController {

    private final EstadoReporteService estadoReporteService;

    public EstadoReporteController(EstadoReporteService estadoReporteService) {
        this.estadoReporteService = estadoReporteService;
    }

    public void findAll(Context ctx) {
        try {
            List<EstadoReporte> estados = estadoReporteService.findAll();
            ctx.json(estados);
        } catch (SQLException e) {
            e.printStackTrace();  // <---- Aquí agregas esta línea
            ctx.status(500).result("Error al obtener estados de reporte: " + e.getMessage());  // Cambia para mostrar mensaje del error
        }
    }

    public void create(Context ctx) {
        EstadoReporte estado = ctx.bodyAsClass(EstadoReporte.class);
        int id = estadoReporteService.save(estado);
        ctx.status(201).json(id);
    }

    public void update(Context ctx) {
        int id = ctx.pathParamAsClass("id", Integer.class).get();
        EstadoReporte estado = ctx.bodyAsClass(EstadoReporte.class);
        if (estadoReporteService.update(id, estado)) {
            ctx.status(204);
        } else {
            ctx.status(404).result("Estado reporte no encontrado");
        }
    }

    public void delete(Context ctx) {
        int id = ctx.pathParamAsClass("id", Integer.class).get();
        if (estadoReporteService.delete(id)) {
            ctx.status(204);
        } else {
            ctx.status(404).result("Estado reporte no encontrado");
        }
    }
}
