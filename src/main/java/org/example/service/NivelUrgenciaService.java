package org.example.service;

import java.sql.SQLException;
import java.util.List;
import org.example.model.NivelUrgencia;
import org.example.repository.NivelUrgenciaRepository;

public class NivelUrgenciaService {
    private final NivelUrgenciaRepository nivelUrgenciaRepository;

    public NivelUrgenciaService(NivelUrgenciaRepository nivelUrgenciaRepository) {
        this.nivelUrgenciaRepository = nivelUrgenciaRepository;
    }

    public List<NivelUrgencia> findAll() throws SQLException {
        return this.nivelUrgenciaRepository.findAll();
    }

    public int save(NivelUrgencia nivelUrgencia) {
        return this.nivelUrgenciaRepository.save(nivelUrgencia);
    }

    public boolean update(int id, NivelUrgencia nivelUrgencia) {
        return this.nivelUrgenciaRepository.update(id, nivelUrgencia);
    }

    public boolean delete(int id) {
        return this.nivelUrgenciaRepository.delete(id);
    }
}