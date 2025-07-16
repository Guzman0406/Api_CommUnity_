package org.example.controller;

import io.javalin.http.Context;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.example.model.Calle;
import org.example.model.UbicacionCalleSeccion;
import org.example.service.UbicacionCalleSeccionService;

public class UbicacionCalleSeccionController {
    private final UbicacionCalleSeccionService service;

    public UbicacionCalleSeccionController(UbicacionCalleSeccionService service) {
        this.service = service;
    }

    public void findAll(Context ctx) {
        try {
            List<UbicacionCalleSeccion> ubicaciones = this.service.findAll();
            ctx.json(ubicaciones);
        } catch (SQLException var3) {
            ctx.status(500).result("Error al obtener ubicaciones");
        }

    }

    public void create(Context ctx) {
        try {
            UbicacionCalleSeccion u = (UbicacionCalleSeccion)ctx.bodyAsClass(UbicacionCalleSeccion.class);
            this.service.create(u);
            ctx.status(201).result("Ubicación creada");
        } catch (SQLException var3) {
            ctx.status(500).result("Error al crear ubicación");
        }

    }

    public void update(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            UbicacionCalleSeccion u = (UbicacionCalleSeccion)ctx.bodyAsClass(UbicacionCalleSeccion.class);
            this.service.update(id, u);
            ctx.result("Ubicación actualizada");
        } catch (SQLException var4) {
            ctx.status(500).result("Error al actualizar ubicación");
        }

    }

    public void delete(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            this.service.delete(id);
            ctx.result("Ubicación eliminada");
        } catch (SQLException var3) {
            ctx.status(500).result("Error al eliminar ubicación");
        }
    }

    public void getIdUbicacion(Context ctx) {
        try {
            int idSeccion = Integer.parseInt(ctx.pathParam("id_seccion"));
            int idCalle = Integer.parseInt(ctx.pathParam("id_calle"));

            Integer idUbicacion = service.getIdUbicacion(idSeccion, idCalle);

            if (idUbicacion != null) {
                ctx.json(Map.of("id_ubicacion", idUbicacion));
            } else {
                ctx.status(404).result("Ubicación no encontrada.");
            }
        } catch (Exception e) {
            ctx.status(500).result("Error al obtener id_ubicacion: " + e.getMessage());
        }
    }


    public void findCallesPorSeccion(Context ctx) {
        try {
            int idSeccion = Integer.parseInt(ctx.pathParam("id_seccion"));
            List<Calle> calles = service.getCallesPorSeccion(idSeccion);
            ctx.json(calles);
        } catch (Exception e) {
            ctx.status(500).result("Error al obtener calles: " + e.getMessage());
        }
    }

}