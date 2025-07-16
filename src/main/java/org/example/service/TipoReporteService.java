package org.example.service;

import java.sql.SQLException;
import java.util.List;
import org.example.model.TipoReporte;
import org.example.repository.TipoReporteRepository;

public class TipoReporteService {
    private final TipoReporteRepository tipoReporteRepository;

    public TipoReporteService(TipoReporteRepository tipoReporteRepository) {
        this.tipoReporteRepository = tipoReporteRepository;
    }

    public List<TipoReporte> findAll() throws SQLException {
        return this.tipoReporteRepository.findAll();
    }

    public int save(TipoReporte tipoReporte) {
        return this.tipoReporteRepository.save(tipoReporte);
    }

    public boolean update(int id, TipoReporte tipoReporte) {
        return this.tipoReporteRepository.update(id, tipoReporte);
    }

    public boolean delete(int id) {
        return this.tipoReporteRepository.delete(id);
    }
}