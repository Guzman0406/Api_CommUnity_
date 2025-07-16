package org.example.service;

import java.sql.SQLException;
import java.util.List;

import org.example.model.Calle;
import org.example.model.UbicacionCalleSeccion; // <-- Added this import
import org.example.repository.UbicacionCalleSeccionRepository;

public class UbicacionCalleSeccionService {
    private final UbicacionCalleSeccionRepository repository;

    public UbicacionCalleSeccionService(UbicacionCalleSeccionRepository repository) {
        this.repository = repository;
    }

    public List<UbicacionCalleSeccion> findAll() throws SQLException {
        return this.repository.findAll();
    }

    public void create(UbicacionCalleSeccion ubicacion) throws SQLException {
        this.repository.create(ubicacion);
    }

    public void update(int id, UbicacionCalleSeccion ubicacion) throws SQLException {
        this.repository.update(id, ubicacion);
    }

    public void delete(int id) throws SQLException {
        this.repository.delete(id);
    }

    public Integer getIdUbicacion(int idSeccion, int idCalle) throws SQLException {
        return repository.findIdUbicacion(idSeccion, idCalle);
    }

    public List<Calle> getCallesPorSeccion(int idSeccion) throws SQLException {return repository.findCallesBySeccion(idSeccion);}



}