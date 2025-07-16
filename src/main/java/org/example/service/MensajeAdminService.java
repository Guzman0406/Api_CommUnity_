package org.example.service;

import org.example.model.MensajeAdmin;
import org.example.repository.MensajeAdminRepository;

import java.sql.SQLException;
import java.util.List;

public class MensajeAdminService {

    private final MensajeAdminRepository mensajeAdminRepository;

    public MensajeAdminService(MensajeAdminRepository mensajeAdminRepository) {
        this.mensajeAdminRepository = mensajeAdminRepository;
    }

    public List<MensajeAdmin> findAll() throws SQLException {
        return mensajeAdminRepository.findAll();
    }

    public int save(MensajeAdmin mensaje) {
        return mensajeAdminRepository.save(mensaje);
    }

    public boolean update(int id, MensajeAdmin mensaje) {
        return mensajeAdminRepository.update(id, mensaje);
    }

    public boolean delete(int id) {
        return mensajeAdminRepository.delete(id);
    }

    public MensajeAdmin findById(int id) throws SQLException {return mensajeAdminRepository.findById(id);}

    public List<MensajeAdmin> getAvisosUltimos7Dias() throws SQLException {return mensajeAdminRepository.findAvisosUltimos7Dias();}

    public List<MensajeAdmin> getAvisosMayoresA7Dias() throws SQLException {return mensajeAdminRepository.findAvisosMayoresA7Dias();}



}
