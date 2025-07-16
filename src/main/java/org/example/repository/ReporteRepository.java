package org.example.repository;

import org.example.config.DatabaseConfig;
import org.example.model.Reporte;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReporteRepository {

    public List<Reporte> findAll() throws SQLException {
        return findAll(false);
    }

    public List<Reporte> findAll(boolean soloActivos) throws SQLException {
        List<Reporte> reportes = new ArrayList<>();
        String query = "SELECT id_reporte, id_usuario, id_tipo, id_ubicacion, referencia, descripcion, id_estado, fecha_creacion, fecha_actualizacion, activo FROM reporte";

        if (soloActivos) {
            query += " WHERE activo = TRUE";
        }

        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                Reporte reporte = new Reporte();
                reporte.setId_reporte(rs.getInt("id_reporte"));
                reporte.setId_usuario(rs.getInt("id_usuario"));
                reporte.setid_tipo(rs.getInt("id_tipo"));
                reporte.setId_ubicacion(rs.getInt("id_ubicacion"));
                reporte.setReferencia(rs.getString("referencia"));
                reporte.setDescripcion(rs.getString("descripcion"));
                reporte.setId_estado(rs.getInt("id_estado"));
                reporte.setFecha_creacion(rs.getTimestamp("fecha_creacion"));
                reporte.setFecha_actualizacion(rs.getTimestamp("fecha_actualizacion"));
                reporte.setActivo(rs.getBoolean("activo"));
                reportes.add(reporte);
            }
        }
        return reportes;
    }

    public Reporte findById(int id_reporte) throws SQLException {
        Reporte reporte = null;
        String query = "SELECT id_reporte, id_usuario, id_tipo, id_ubicacion, referencia, descripcion, id_estado, fecha_creacion, fecha_actualizacion, activo FROM reporte WHERE id_reporte = ?";

        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query);
        ) {
            ps.setInt(1, id_reporte);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    reporte = new Reporte();
                    reporte.setId_reporte(rs.getInt("id_reporte"));
                    reporte.setId_usuario(rs.getInt("id_usuario"));
                    reporte.setid_tipo(rs.getInt("id_tipo"));
                    reporte.setId_ubicacion(rs.getInt("id_ubicacion"));
                    reporte.setReferencia(rs.getString("referencia"));
                    reporte.setDescripcion(rs.getString("descripcion"));
                    reporte.setId_estado(rs.getInt("id_estado"));
                    reporte.setFecha_creacion(rs.getTimestamp("fecha_creacion"));
                    reporte.setFecha_actualizacion(rs.getTimestamp("fecha_actualizacion"));
                    reporte.setActivo(rs.getBoolean("activo"));
                }
            }
        }
        return reporte;
    }

    public int save(Reporte reporte) {
        String query = "INSERT INTO reporte (id_usuario, id_tipo, id_ubicacion, referencia, descripcion) VALUES (?, ?, ?, ?, ?)";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setInt(1, reporte.getId_usuario());
            ps.setInt(2, reporte.getid_tipo());
            ps.setInt(3, reporte.getId_ubicacion());
            ps.setString(4, reporte.getReferencia());
            ps.setString(5, reporte.getDescripcion());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error insertando reporte: " + e.getMessage(), e);
        }
        return -1;
    }

    public boolean update(int id_reporte, Reporte reporte) {
        String query = "UPDATE reporte SET id_usuario = ?, id_tipo = ?, id_ubicacion = ?, referencia = ?, descripcion = ?, id_estado = ?, activo = ? WHERE id_reporte = ?";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query)
        ) {

            ps.setInt(1, reporte.getId_usuario());
            ps.setInt(2, reporte.getid_tipo());
            ps.setInt(3, reporte.getId_ubicacion());
            ps.setString(4, reporte.getReferencia());
            ps.setString(5, reporte.getDescripcion());
            ps.setInt(6, reporte.getId_estado());
            ps.setBoolean(7, reporte.isActivo());
            ps.setInt(8, id_reporte);

            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando reporte con ID " + id_reporte + ": " + e.getMessage(), e);
        }
    }



    public boolean updateActivoStatus(int id_reporte, boolean activo) {
        String query = "UPDATE reporte SET activo = ?, fecha_actualizacion = ? WHERE id_reporte = ?";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query)
        ) {
            ps.setBoolean(1, activo);
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis())); // Actualiza la fecha al cambiar el estado
            ps.setInt(3, id_reporte);

            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando estado 'activo' del reporte con ID " + id_reporte + ": " + e.getMessage(), e);
        }
    }

    public int getTotalReportes() throws SQLException {
        String sql = "SELECT COUNT(*) FROM reporte";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ) {
            rs.next();
            return rs.getInt(1);
        }
    }

    public int getTotalReportesResueltos() throws SQLException {
        String sql = "SELECT COUNT(*) FROM reporte WHERE id_estado = 3"; // Asegúrate que sea el nombre correcto del campo
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ) {
            rs.next();
            return rs.getInt(1);
        }
    }


    public double getTiempoPromedioResolucionHoras() throws SQLException {
        String sql = """
        SELECT AVG(TIMESTAMPDIFF(HOUR, fecha_creacion, fecha_actualizacion)) AS promedio_horas
        FROM reporte
        WHERE id_estado = 3 AND fecha_actualizacion IS NOT NULL
    """;
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ) {
            rs.next();
            return rs.getDouble("promedio_horas");
        }
    }

    public List<Reporte> findBySeccion(int seccionId) throws SQLException {
        List<Reporte> reportes = new ArrayList<>();
        String sql = """
        SELECT r.*
        FROM reporte r
        JOIN ubicacioncalleseccion ucs ON r.id_ubicacion = ucs.id_ubicacion
        WHERE ucs.id_seccion = ?
    """;

        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {
            ps.setInt(1, seccionId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Reporte reporte = new Reporte();
                    reporte.setId_reporte(rs.getInt("id_reporte"));
                    reporte.setId_usuario(rs.getInt("id_usuario"));
                    reporte.setid_tipo(rs.getInt("id_tipo"));
                    reporte.setId_ubicacion(rs.getInt("id_ubicacion"));
                    reporte.setReferencia(rs.getString("referencia"));
                    reporte.setDescripcion(rs.getString("descripcion"));
                    reporte.setId_estado(rs.getInt("id_estado"));
                    reporte.setFecha_creacion(rs.getTimestamp("fecha_creacion"));
                    reporte.setFecha_actualizacion(rs.getTimestamp("fecha_actualizacion"));
                    reporte.setActivo(rs.getBoolean("activo"));
                    reportes.add(reporte);
                }
            }
        }

        return reportes;
    }

    public List<Reporte> findByTipo(int idTipo) throws SQLException {
        List<Reporte> reportes = new ArrayList<>();
        String query = "SELECT id_reporte, id_usuario, id_tipo, id_ubicacion, referencia, descripcion, id_estado, fecha_creacion, fecha_actualizacion, activo FROM reporte WHERE id_tipo = ? AND activo = TRUE";

        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query)
        ) {
            ps.setInt(1, idTipo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Reporte reporte = new Reporte();
                    reporte.setId_reporte(rs.getInt("id_reporte"));
                    reporte.setId_usuario(rs.getInt("id_usuario"));
                    reporte.setid_tipo(rs.getInt("id_tipo"));
                    reporte.setId_ubicacion(rs.getInt("id_ubicacion"));
                    reporte.setReferencia(rs.getString("referencia"));
                    reporte.setDescripcion(rs.getString("descripcion"));
                    reporte.setId_estado(rs.getInt("id_estado"));
                    reporte.setFecha_creacion(rs.getTimestamp("fecha_creacion"));
                    reporte.setFecha_actualizacion(rs.getTimestamp("fecha_actualizacion"));
                    reporte.setActivo(rs.getBoolean("activo"));
                    reportes.add(reporte);
                }
            }
        }
        return reportes;
    }



    public List<Reporte> findByEstado(int idEstado) throws SQLException {
        List<Reporte> reportes = new ArrayList<>();
        String query = "SELECT id_reporte, id_usuario, id_tipo, id_ubicacion, referencia, descripcion, id_estado, fecha_creacion, fecha_actualizacion, activo FROM reporte WHERE id_estado = ? AND activo = TRUE";

        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query)
        ) {
            ps.setInt(1, idEstado);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Reporte reporte = new Reporte();
                    reporte.setId_reporte(rs.getInt("id_reporte"));
                    reporte.setId_usuario(rs.getInt("id_usuario"));
                    reporte.setid_tipo(rs.getInt("id_tipo"));
                    reporte.setId_ubicacion(rs.getInt("id_ubicacion"));
                    reporte.setReferencia(rs.getString("referencia"));
                    reporte.setDescripcion(rs.getString("descripcion"));
                    reporte.setId_estado(rs.getInt("id_estado"));
                    reporte.setFecha_creacion(rs.getTimestamp("fecha_creacion"));
                    reporte.setFecha_actualizacion(rs.getTimestamp("fecha_actualizacion"));
                    reporte.setActivo(rs.getBoolean("activo"));
                    reportes.add(reporte);
                }
            }
        }
        return reportes;
    }


    public List<Reporte> findByUsuario(int idUsuario) throws SQLException {
        List<Reporte> reportes = new ArrayList<>();
        String query = "SELECT id_reporte, id_usuario, id_tipo, id_ubicacion, referencia, descripcion, id_estado, fecha_creacion, fecha_actualizacion, activo FROM reporte WHERE id_usuario = ? AND activo = TRUE";

        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query)
        ) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Reporte reporte = new Reporte();
                    reporte.setId_reporte(rs.getInt("id_reporte"));
                    reporte.setId_usuario(rs.getInt("id_usuario"));
                    reporte.setid_tipo(rs.getInt("id_tipo"));
                    reporte.setId_ubicacion(rs.getInt("id_ubicacion"));
                    reporte.setReferencia(rs.getString("referencia"));
                    reporte.setDescripcion(rs.getString("descripcion"));
                    reporte.setId_estado(rs.getInt("id_estado"));
                    reporte.setFecha_creacion(rs.getTimestamp("fecha_creacion"));
                    reporte.setFecha_actualizacion(rs.getTimestamp("fecha_actualizacion"));
                    reporte.setActivo(rs.getBoolean("activo"));
                    reportes.add(reporte);
                }
            }
        }
        return reportes;
    }

    public List<Reporte> findAllWithPagination(int limit, int offset) throws SQLException {
        List<Reporte> reportes = new ArrayList<>();
        String sql = "SELECT * FROM reporte ORDER BY fecha_creacion DESC, id_reporte DESC LIMIT ? OFFSET ?";

        try (Connection c = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = c.prepareStatement(sql)) {

            stmt.setInt(1, limit);
            stmt.setInt(2, offset);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Reporte reporte = new Reporte();
                    reporte.setId_reporte(rs.getInt("id_reporte"));
                    reporte.setId_usuario(rs.getInt("id_usuario"));
                    reporte.setid_tipo(rs.getInt("id_tipo"));
                    reporte.setId_ubicacion(rs.getInt("id_ubicacion"));
                    reporte.setReferencia(rs.getString("referencia"));
                    reporte.setDescripcion(rs.getString("descripcion"));
                    reporte.setId_estado(rs.getInt("id_estado"));
                    reporte.setFecha_creacion(rs.getTimestamp("fecha_creacion"));
                    reporte.setFecha_actualizacion(rs.getTimestamp("fecha_actualizacion"));
                    reporte.setActivo(rs.getBoolean("activo"));
                    reportes.add(reporte);
                }
            }
        }

        return reportes;
    }





}