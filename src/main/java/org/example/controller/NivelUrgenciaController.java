package org.example.controller;

import io.javalin.http.Context;
import java.sql.SQLException;
import java.util.List;
import org.example.model.NivelUrgencia;
import org.example.service.NivelUrgenciaService;

public class NivelUrgenciaController {
    private final NivelUrgenciaService nivelUrgenciaService;

    public NivelUrgenciaController(NivelUrgenciaService nivelUrgenciaService) {
        this.nivelUrgenciaService = nivelUrgenciaService;
    }

    public void findAll(Context ctx) {
        try {
            List<NivelUrgencia> niveles = this.nivelUrgenciaService.findAll();
            ctx.json(niveles);
        } catch (SQLException var3) {
            ctx.status(500).result("Error al obtener niveles de urgencia");
        }

    }

    public void create(Context ctx) {
        NivelUrgencia nivel = (NivelUrgencia)ctx.bodyAsClass(NivelUrgencia.class);
        int id = this.nivelUrgenciaService.save(nivel);
        ctx.status(201).json(id);
    }

    public void update(Context ctx) {
        int id = (Integer)ctx.pathParamAsClass("id", Integer.class).get();
        NivelUrgencia nivel = (NivelUrgencia)ctx.bodyAsClass(NivelUrgencia.class);
        if (this.nivelUrgenciaService.update(id, nivel)) {
            ctx.status(204);
        } else {
            ctx.status(404).result("Nivel de urgencia no encontrado");
        }

    }

    public void delete(Context ctx) {
        int id = (Integer)ctx.pathParamAsClass("id", Integer.class).get();
        if (this.nivelUrgenciaService.delete(id)) {
            ctx.status(204);
        } else {
            ctx.status(404).result("Nivel de urgencia no encontrado");
        }

    }
}