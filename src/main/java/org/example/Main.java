package org.example;

import io.javalin.Javalin;

import org.example.routes.*;


public class Main {
    public static void main(String[] args) {

        System.out.println(">>> [DEBUG] Iniciando la configuración de Javalin...");

        Javalin app = Javalin.create(config -> {
            config.http.defaultContentType = "application/json";
            System.out.println(">>> [DEBUG] Configurando tipo de contenido por defecto a application/json.");
        });

        System.out.println(">>> [DEBUG] Configuración CORS manual...");
        app.before(ctx -> {
            ctx.header("Access-Control-Allow-Origin", "*");
            ctx.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            ctx.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
        });

        app.options("/*", ctx -> ctx.status(204));

        System.out.println(">>> [DEBUG] Registrando rutas...");
        // Registrar rutas
        UsuarioRoutes.registerRoutes(app);
        TipoReporteRoutes.registerRoutes(app);
        SeccionRoutes.registerRoutes(app);
        ReporteRoutes.registerRoutes(app);
        NivelUrgenciaRoutes.registerRoutes(app);
        MensajeAdminRoutes.registerRoutes(app);
        EstadoReporteRoutes.registerRoutes(app);
        CalleRoutes.registerRoutes(app);
        UbicacionCalleSeccionRoutes.registerUbicacionCalleSeccion(app);


        app.get("/", ctx -> ctx.result("API CommUnity funcionando"));

        System.out.println(">>> [DEBUG] Rutas registradas.");



        // Iniciar servidor
        try {
            int port = 7001;
            app.start(port);
            System.out.println(">>> [INFO] Servidor corriendo exitosamente en http://localhost:" + port);

        } catch (Exception e) {
            System.err.println("!!! [ERROR] El servidor no pudo iniciar. Posiblemente el puerto ya esté en uso o haya otro problema.");
            System.err.println("!!! [ERROR] Detalles: " + e.getMessage());
            e.printStackTrace();
        }
    }
}