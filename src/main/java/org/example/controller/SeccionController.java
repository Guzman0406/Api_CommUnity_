package org.example.controller;

import io.javalin.http.Context;
import java.sql.SQLException;
import java.util.List;
import org.example.model.Seccion;
import org.example.service.SeccionService;

public class SeccionController {
    private final SeccionService seccionService;

    public SeccionController(SeccionService seccionService) {
        this.seccionService = seccionService;
    }

    public void findAllSeccion(Context ctx) {
        try {
            List<Seccion> secciones = this.seccionService.findAll();
            ctx.json(secciones);
        } catch (SQLException var3) {
            ctx.status(500).result("Error al obtener secciones");
        }

    }

    public void createSeccion(Context ctx) {
        try {
            Seccion seccion = (Seccion)ctx.bodyAsClass(Seccion.class);
            this.seccionService.create(seccion);
            ctx.status(201).result("Sección creada correctamente");
        } catch (SQLException var3) {
            ctx.status(500).result("Error al crear sección");
        }

    }

    public void updateSeccion(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Seccion seccion = (Seccion)ctx.bodyAsClass(Seccion.class);
            this.seccionService.update(id, seccion);
            ctx.result("Sección actualizada correctamente");
        } catch (SQLException var4) {
            ctx.status(500).result("Error al actualizar sección");
        } catch (NumberFormatException var5) {
            ctx.status(400).result("ID inválido");
        }

    }

    public void deleteSeccion(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            this.seccionService.delete(id);
            ctx.result("Sección eliminada correctamente");
        } catch (SQLException var3) {
            ctx.status(500).result("Error al eliminar sección");
        } catch (NumberFormatException var4) {
            ctx.status(400).result("ID inválido");
        }

    }
}