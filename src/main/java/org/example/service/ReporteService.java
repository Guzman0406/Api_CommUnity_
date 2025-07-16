package org.example.service;

import org.example.model.Reporte;
import org.example.repository.ReporteRepository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class ReporteService {

    private final ReporteRepository reporteRepository;

    public ReporteService(ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    public List<Reporte> findAll() throws SQLException {
        return reporteRepository.findAll();
    }

    public Reporte findById(int id_reporte) throws SQLException {
        return reporteRepository.findById(id_reporte);
    }

    public int save(Reporte reporte) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        reporte.setFecha_creacion(now);
        reporte.setFecha_actualizacion(now);
        reporte.setActivo(true);
        return reporteRepository.save(reporte);
    }

    public boolean update(int id_reporte, Reporte reporte) {
        reporte.setFecha_actualizacion(new Timestamp(System.currentTimeMillis()));
        return reporteRepository.update(id_reporte, reporte);
    }


    public boolean resolverReporte(int id_reporte) {
        return reporteRepository.updateActivoStatus(id_reporte, false);
    }


    public boolean activarReporte(int id_reporte) {
        return reporteRepository.updateActivoStatus(id_reporte, true);
    }

    public List<Reporte> findActiveReports() throws SQLException {
        return reporteRepository.findAll(true);
    }

    public int getTotalReportes() throws SQLException {
        return reporteRepository.getTotalReportes();
    }

    public int getTotalReportesResueltos() throws SQLException {
        return reporteRepository.getTotalReportesResueltos();
    }

    public double getTiempoPromedioResolucionHoras() throws SQLException {
        return reporteRepository.getTiempoPromedioResolucionHoras();
    }

    public List<Reporte> findBySeccion(int seccionId) throws SQLException {
        return reporteRepository.findBySeccion(seccionId);
    }

    public List<Reporte> findByTipo(int idTipo) throws SQLException {
        return reporteRepository.findByTipo(idTipo);
    }


    public List<Reporte> findByEstado(int idEstado) throws SQLException {
        return reporteRepository.findByEstado(idEstado);
    }


    public List<Reporte> findByUsuario(int idUsuario) throws SQLException {
        return reporteRepository.findByUsuario(idUsuario);
    }

    public List<Reporte> getAllReportesPaginados(int page, int limit) throws SQLException {
        int offset = (page - 1) * limit;
        return reporteRepository.findAllWithPagination(limit, offset);
    }






}