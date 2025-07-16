package org.example.repository;

import org.example.config.DatabaseConfig;
import org.example.model.Calle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalleRepository {

    public List<Calle> findAll() throws SQLException {
        List<Calle> calles = new ArrayList<>();
        String query = "SELECT id_calle, nombre_calle FROM calle";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Calle calle = new Calle();
                calle.setId_calle(rs.getInt("id_calle"));
                calle.setNombre_calle(rs.getString("nombre_calle"));
                calles.add(calle);
            }
        }
        return calles;
    }

    public int save(Calle calle) {
        String query = "INSERT INTO calle (nombre_calle) VALUES (?)";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, calle.getNombre_calle());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error insertando calle", e);
        }
        return -1;
    }

    public boolean update(int id_calle, Calle calle) {
        String query = "UPDATE calle SET nombre_calle = ? WHERE id_calle = ?";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query)
        ) {
            ps.setString(1, calle.getNombre_calle());
            ps.setInt(2, id_calle);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando calle", e);
        }
    }

    public boolean delete(int id_calle) {
        String query = "DELETE FROM calle WHERE id_calle = ?";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query)
        ) {
            ps.setInt(1, id_calle);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando calle", e);
        }
    }
}
