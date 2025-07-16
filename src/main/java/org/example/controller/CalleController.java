package org.example.controller;

import io.javalin.http.Context;
import org.example.model.Calle;
import org.example.service.CalleService;

import java.sql.SQLException;
import java.util.List;

public class CalleController {

    private final CalleService calleService;

    public CalleController(CalleService calleService) {
        this.calleService = calleService;
    }

    public void findAll(Context ctx) {
        try {
            List<Calle> calles = calleService.findAll();
            ctx.json(calles);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener las calles");
        }
    }

    public void create(Context ctx) {
        Calle calle = ctx.bodyAsClass(Calle.class);
        int id = calleService.save(calle);
        ctx.status(201).json(id);
    }

    public void update(Context ctx) {
        int id = ctx.pathParamAsClass("id", Integer.class).get();
        Calle calle = ctx.bodyAsClass(Calle.class);
        if (calleService.update(id, calle)) {
            ctx.status(204);
        } else {
            ctx.status(404).result("Calle no encontrada");
        }
    }

    public void delete(Context ctx) {
        int id = ctx.pathParamAsClass("id", Integer.class).get();
        if (calleService.delete(id)) {
            ctx.status(204);
        } else {
            ctx.status(404).result("Calle no encontrada");
        }
    }
}
