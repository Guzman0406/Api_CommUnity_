package org.example.controller;

import io.javalin.http.Context;
import org.example.model.Reporte;
import org.example.service.ReporteService;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    public void getAllReportes(Context ctx) throws SQLException {
        List<Reporte> reportes = reporteService.findActiveReports();

        if (reportes.isEmpty()) {
            ctx.status(204);
            return;
        }
        ctx.json(reportes);
    }

    public void getReporteById(Context ctx) throws SQLException {
        int id_reporte = ctx.pathParamAsClass("id", Integer.class).get();
        Reporte reporte = reporteService.findById(id_reporte);
        if (reporte != null) {
            ctx.json(reporte);
        } else {
            ctx.status(404);
        }
    }

    public void createReporte(Context ctx) {
        Reporte nuevoReporte = ctx.bodyAsClass(Reporte.class);
        int idGenerado = reporteService.save(nuevoReporte);
        if (idGenerado != -1) {
            nuevoReporte.setId_reporte(idGenerado);
            ctx.status(201).json(nuevoReporte);
        } else {
            ctx.status(500);
        }
    }

    public void updateReporte(Context ctx) {
        int id_reporte = ctx.pathParamAsClass("id", Integer.class).get();
        Reporte reporteActualizado = ctx.bodyAsClass(Reporte.class);

        reporteActualizado.setId_reporte(id_reporte);

        boolean exito = reporteService.update(id_reporte, reporteActualizado);
        if (exito) {
            ctx.status(200).json(reporteActualizado);
        } else {
            ctx.status(404);
        }
    }


    public void resolverReporte(Context ctx) {
        int id_reporte = ctx.pathParamAsClass("id", Integer.class).get();
        boolean exito = reporteService.resolverReporte(id_reporte);
        if (exito) {
            ctx.status(200);
        } else {
            ctx.status(404);
        }
    }

    public void getTotalReportes(Context ctx) throws SQLException {
        int total = reporteService.getTotalReportes();
        ctx.json(Map.of("total", total));
    }

    public void getTotalReportesResueltos(Context ctx) throws SQLException {
        int totalResueltos = reporteService.getTotalReportesResueltos();
        ctx.json(Map.of("resueltos", totalResueltos));
    }

    public void getTiempoPromedioResolucion(Context ctx) throws SQLException {
        double promedioHoras = reporteService.getTiempoPromedioResolucionHoras();
        ctx.json(Map.of("promedio_horas", promedioHoras));
    }

    public void findBySeccion(Context ctx) {
        String seccionParam = ctx.pathParam("id_seccion");
        if (seccionParam == null || seccionParam.isEmpty()) {
            ctx.status(400).result("Falta parámetro 'id_seccion'");
            return;
        }

        try {
            int seccionId = Integer.parseInt(seccionParam);
            List<Reporte> reportes = reporteService.findBySeccion(seccionId);
            ctx.json(reportes);
        } catch (NumberFormatException e) {
            ctx.status(400).result("Parámetro 'seccion' inválido");
        } catch (SQLException e) {
            ctx.status(500).result("Error al buscar reportes por sección: " + e.getMessage());
        }
    }

    public void findByTipo(Context ctx) {
        try {
            String param = ctx.queryParam("id_tipo");
            if (param == null) {
                ctx.status(400).result("Falta el parámetro 'id_tipo'");
                return;
            }
            int idTipo = Integer.parseInt(param);
            List<Reporte> reportes = reporteService.findByTipo(idTipo);
            ctx.json(reportes);
        } catch (NumberFormatException e) {
            ctx.status(400).result("Parámetro 'id_tipo' inválido");
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener reportes por tipo: " + e.getMessage());
        }
    }

    public void findByEstado(Context ctx) {
        try {
            String param = ctx.queryParam("id_estado");
            if (param == null) {
                ctx.status(400).result("Falta el parámetro 'id_estado'");
                return;
            }

            int idEstado = Integer.parseInt(param);
            List<Reporte> reportes = reporteService.findByEstado(idEstado);
            ctx.json(reportes);

        } catch (NumberFormatException e) {
            ctx.status(400).result("Parámetro 'id_estado' inválido. Debe ser un número.");
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener reportes por estado: " + e.getMessage());
        }
    }


    public void findByUsuario(Context ctx) {
        try {
            String param = ctx.queryParam("id_usuario");
            if (param == null) {
                ctx.status(400).result("Falta el parámetro 'id_usuario'");
                return;
            }

            int idUsuario = Integer.parseInt(param);
            List<Reporte> reportes = reporteService.findByUsuario(idUsuario);
            ctx.json(reportes);

        } catch (NumberFormatException e) {
            ctx.status(400).result("Parámetro 'id_usuario' inválido. Debe ser un número.");
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener reportes por usuario: " + e.getMessage());
        }
    }

    public void getReportesPaginados(Context ctx) {
        try {
            String pageParam = ctx.queryParam("page");
            int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1;

            int limit = 9;

            List<Reporte> reportes = reporteService.getAllReportesPaginados(page, limit);
            ctx.json(reportes);

        } catch (NumberFormatException e) {
            ctx.status(400).result("Parámetro 'page' inválido");
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener reportes paginados");
        }
    }








}