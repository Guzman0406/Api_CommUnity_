package org.example.controller;

import io.javalin.http.Context;
import java.sql.SQLException;
import java.util.List;
import org.example.model.TipoReporte;
import org.example.service.TipoReporteService;

public class TipoReporteController {
    private final TipoReporteService tipoReporteService;

    public TipoReporteController(TipoReporteService tipoReporteService) {
        this.tipoReporteService = tipoReporteService;
    }

    public void getAll(Context ctx) {
        try {
            List<TipoReporte> tipos = this.tipoReporteService.findAll();
            ctx.json(tipos);
        } catch (SQLException var3) {
            ctx.status(500).result("Error al obtener tipos de reporte");
        }

    }

    public void create(Context ctx) {
        TipoReporte tipo = (TipoReporte)ctx.bodyAsClass(TipoReporte.class);
        int id = this.tipoReporteService.save(tipo);
        ctx.status(201).json(id);
    }

    public void update(Context ctx) {
        int id = (Integer)ctx.pathParamAsClass("id", Integer.class).get();
        TipoReporte tipo = (TipoReporte)ctx.bodyAsClass(TipoReporte.class);
        boolean actualizado = this.tipoReporteService.update(id, tipo);
        if (actualizado) {
            ctx.status(204);
        } else {
            ctx.status(404).result("Tipo de reporte no encontrado");
        }

    }

    public void delete(Context ctx) {
        int id = (Integer)ctx.pathParamAsClass("id", Integer.class).get();
        boolean eliminado = this.tipoReporteService.delete(id);
        if (eliminado) {
            ctx.status(204);
        } else {
            ctx.status(404).result("Tipo de reporte no encontrado");
        }

    }
}