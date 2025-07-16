package org.example.service;

import org.example.model.Calle;
import org.example.repository.CalleRepository;

import java.sql.SQLException;
import java.util.List;

public class CalleService {

    private final CalleRepository calleRepository;

    public CalleService(CalleRepository calleRepository) {
        this.calleRepository = calleRepository;
    }

    public List<Calle> findAll() throws SQLException {
        return calleRepository.findAll();
    }

    public int save(Calle calle) {
        return calleRepository.save(calle);
    }

    public boolean update(int id, Calle calle) {
        return calleRepository.update(id, calle);
    }

    public boolean delete(int id) {
        return calleRepository.delete(id);
    }
}
