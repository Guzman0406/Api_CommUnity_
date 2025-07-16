package org.example.service;

import org.example.model.Seccion;
import org.example.repository.SeccionRepository;

import java.sql.SQLException;
import java.util.List;

public class SeccionService {

    private final SeccionRepository seccionRepository;

    public SeccionService(SeccionRepository seccionRepository) {
        this.seccionRepository = seccionRepository;
    }

    public List<Seccion> findAll() throws SQLException {
        return seccionRepository.findAll();
    }

    public void create(Seccion seccion) throws SQLException {
        seccionRepository.create(seccion);
    }


    public void update(int id, Seccion seccion) throws SQLException {
        seccionRepository.update(id, seccion);
    }


    public void delete(int id) throws SQLException {
        seccionRepository.delete(id);
    }
}
