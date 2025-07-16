package org.example.repository;

import org.example.config.DatabaseConfig;
import org.example.model.EstadoReporte;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstadoReporteRepository {

    public List<EstadoReporte> findAll() throws SQLException {
        List<EstadoReporte> estados = new ArrayList<>();
        String query = "SELECT id_estado_reporte, nombre_estado FROM estadoreporte";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                EstadoReporte estado = new EstadoReporte();
                estado.setId_estado_reporte(rs.getInt("id_estado_reporte"));
                estado.setNombre_estado(rs.getString("nombre_estado"));
                estados.add(estado);
            }
        }
        return estados;
    }

    public int save(EstadoReporte estado) {
        String query = "INSERT INTO estadoreporte (nombre_estado) VALUES (?)";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, estado.getNombre_estado());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error insertando estado reporte", e);
        }
        return -1;
    }

    public boolean update(int id_estado_reporte, EstadoReporte estado) {
        String query = "UPDATE estadoreporte SET nombre_estado = ? WHERE id_estado_reporte = ?";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query)
        ) {
            ps.setString(1, estado.getNombre_estado());
            ps.setInt(2, id_estado_reporte);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando estado reporte", e);
        }
    }

    public boolean delete(int id_estado_reporte) {
        String query = "DELETE FROM estadoreporte WHERE id_estado_reporte = ?";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query)
        ) {
            ps.setInt(1, id_estado_reporte);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando estado reporte", e);
        }
    }
}