package org.example.service;

import org.example.model.EstadoReporte;
import org.example.repository.EstadoReporteRepository;

import java.sql.SQLException;
import java.util.List;

public class EstadoReporteService {

    private final EstadoReporteRepository estadoReporteRepository;

    public EstadoReporteService(EstadoReporteRepository estadoReporteRepository) {
        this.estadoReporteRepository = estadoReporteRepository;
    }

    public List<EstadoReporte> findAll() throws SQLException {
        return estadoReporteRepository.findAll();
    }

    public int save(EstadoReporte estado) {
        return estadoReporteRepository.save(estado);
    }

    public boolean update(int id, EstadoReporte estado) {
        return estadoReporteRepository.update(id, estado);
    }

    public boolean delete(int id) {
        return estadoReporteRepository.delete(id);
    }
}
