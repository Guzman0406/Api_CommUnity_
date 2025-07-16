package org.example.controller;

import io.javalin.http.Context;
import org.example.model.Usuario;
import org.example.service.UsuarioService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void findAllUsuarios(Context ctx) {
        try {
            List<Usuario> usuarios = usuarioService.findAll();
            if (usuarios.isEmpty()) {
                ctx.status(204);
                return;
            }
            ctx.json(usuarios);
        } catch (SQLException e) {
            e.printStackTrace();
            ctx.status(500).result("Error interno del servidor al obtener usuarios: " + e.getMessage());
        }
    }

    public void createUsuario(Context ctx) {
        try {
            Usuario usuario = ctx.bodyAsClass(Usuario.class);
            int idGenerado = usuarioService.save(usuario);
            if (idGenerado != -1) {

                ctx.status(201).result("Usuario creado exitosamente.");
            } else {
                ctx.status(500).result("Error interno al crear el usuario.");
            }
        } catch (Exception e) {
            ctx.status(400).result("Datos de usuario inválidos: " + e.getMessage());
        }
    }

    public void updateUsuario(Context ctx) {
        try {
            int id = ctx.pathParamAsClass("id", Integer.class).get();
            Usuario usuario = ctx.bodyAsClass(Usuario.class);
            usuario.setId_usuario(id);

            if (usuarioService.update(id, usuario)) {
                ctx.status(200).json(usuario);
            } else {
                ctx.status(404).result("Usuario con ID " + id + " no encontrado o no se pudo actualizar.");
            }
        } catch (Exception e) {
            ctx.status(400).result("Datos de actualización de usuario inválidos: " + e.getMessage());
        }
    }

    public void deleteUsuario(Context ctx) {
        try {
            int id = ctx.pathParamAsClass("id", Integer.class).get();
            if (usuarioService.delete(id)) {
                ctx.status(204);
            } else {
                ctx.status(404).result("Usuario con ID " + id + " no encontrado.");
            }
        } catch (Exception e) {
            ctx.status(500).result("Error interno al eliminar el usuario: " + e.getMessage());
        }
    }

    public void login(Context ctx) {
        try {
            Map<String, String> datos = ctx.bodyAsClass(Map.class);
            String correo = datos.get("correo");
            String contraseña = datos.get("contraseña");

            Usuario usuario = usuarioService.authenticate(correo, contraseña);

            if (usuario != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("id", usuario.getId_usuario());
                response.put("nombre", usuario.getNombre());
                response.put("es_admin", usuario.isEs_admin());

                ctx.json(response);
            } else {
                ctx.status(401).result("Correo o contraseña incorrectos");
            }
        } catch (Exception e) {
            ctx.status(500).result("Error en el login");
        }
    }


}