package org.example.controller;

import io.javalin.http.Context;
import org.example.model.MensajeAdmin;
import org.example.service.MensajeAdminService;

import java.sql.SQLException;
import java.util.List;

public class MensajeAdminController {

    private final MensajeAdminService mensajeAdminService;

    public MensajeAdminController(MensajeAdminService mensajeAdminService) {
        this.mensajeAdminService = mensajeAdminService;
    }

    public void findAll(Context ctx) {
        try {
            List<MensajeAdmin> mensajes = mensajeAdminService.findAll();
            ctx.json(mensajes);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener mensajes de admin");
        }
    }

    public void create(Context ctx) {
        try {
            MensajeAdmin mensaje = ctx.bodyAsClass(MensajeAdmin.class);

            // Validación de campos obligatorios
            if (mensaje.getTitulo() == null || mensaje.getTitulo().trim().isEmpty()) {
                ctx.status(400).result("El título no puede estar vacío");
                return;
            }
            if (mensaje.getContenido() == null || mensaje.getContenido().trim().isEmpty()) {
                ctx.status(400).result("El contenido no puede estar vacío");
                return;
            }

            mensajeAdminService.save(mensaje);
            ctx.status(201).json(mensaje);
        } catch (Exception e) {
            ctx.status(500).result("Error al crear el aviso");
        }
    }


    public void update(Context ctx) {
        int id = ctx.pathParamAsClass("id", Integer.class).get();
        MensajeAdmin mensaje = ctx.bodyAsClass(MensajeAdmin.class);
        if (mensajeAdminService.update(id, mensaje)) {
            ctx.status(204);
        } else {
            ctx.status(404).result("Mensaje admin no encontrado");
        }
    }

    public void delete(Context ctx) {
        int id = ctx.pathParamAsClass("id", Integer.class).get();
        if (mensajeAdminService.delete(id)) {
            ctx.status(204);
        } else {
            ctx.status(404).result("Mensaje admin no encontrado");
        }
    }

    public void findById(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            MensajeAdmin aviso = mensajeAdminService.findById(id);

            if (aviso != null) {
                ctx.json(aviso);
            } else {
                ctx.status(404).result("Aviso no encontrado");
            }

        } catch (NumberFormatException e) {
            ctx.status(400).result("ID inválido");
        } catch (SQLException e) {
            ctx.status(500).result("Error al buscar aviso: " + e.getMessage());
        }
    }


    public void findAvisosUltimos7Dias(Context ctx) {
        try {
            List<MensajeAdmin> avisos = mensajeAdminService.getAvisosUltimos7Dias();
            ctx.json(avisos);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener avisos recientes: " + e.getMessage());
        }
    }


    public void findAvisosMayoresA7Dias(Context ctx) {
        try {
            List<MensajeAdmin> avisos = mensajeAdminService.getAvisosMayoresA7Dias();
            ctx.json(avisos);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener avisos mayores a 7 días: " + e.getMessage());
        }
    }



}
