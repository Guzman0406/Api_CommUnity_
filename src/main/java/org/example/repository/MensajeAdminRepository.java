package org.example.repository;

import org.example.config.DatabaseConfig;
import org.example.model.MensajeAdmin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MensajeAdminRepository {

    public List<MensajeAdmin> findAll() throws SQLException {
        List<MensajeAdmin> mensajes = new ArrayList<>();

        String query = "SELECT id_mensaje, id_admin, titulo, contenido, fecha_creacion FROM mensajeadmin";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                MensajeAdmin mensaje = new MensajeAdmin();
                mensaje.setId_mensaje(rs.getInt("id_mensaje"));
                mensaje.setId_admin(rs.getInt("id_admin"));
                mensaje.setTitulo(rs.getString("titulo"));
                mensaje.setContenido(rs.getString("contenido"));
                // Usar 'fecha_creacion' para obtener el valor del ResultSet
                mensaje.setFecha_creacion(rs.getTimestamp("fecha_creacion"));
                mensajes.add(mensaje);
            }
        }
        return mensajes;
    }

    public int save(MensajeAdmin mensaje) {

        String query = "INSERT INTO mensajeadmin (id_admin, titulo, contenido) VALUES (?, ?, ?)";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setInt(1, mensaje.getId_admin());
            ps.setString(2, mensaje.getTitulo());
            ps.setString(3, mensaje.getContenido());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error insertando mensaje admin", e);
        }
        return -1;
    }

    public boolean update(int id_mensaje, MensajeAdmin mensaje) {
        // Usar 'fecha_creacion' en la consulta para que coincida con el modelo
        String query = "UPDATE mensajeadmin SET id_admin = ?, titulo = ?, contenido = ?  WHERE id_mensaje = ?";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query)
        ) {
            ps.setInt(1, mensaje.getId_admin());
            ps.setString(2, mensaje.getTitulo());
            ps.setString(3, mensaje.getContenido());

            ps.setInt(5, id_mensaje);

            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando mensaje admin", e);
        }
    }

    public boolean delete(int id_mensaje) {
        String query = "DELETE FROM mensajeadmin WHERE id_mensaje = ?";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query)
        ) {
            ps.setInt(1, id_mensaje);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando mensaje admin", e);
        }
    }

    public MensajeAdmin findById(int id) throws SQLException {
        String query = "SELECT id_mensaje, id_admin, titulo, contenido, fecha_creacion FROM mensajeadmin WHERE id_mensaje = ?";
        MensajeAdmin aviso = null;

        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query)
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    aviso = new MensajeAdmin();
                    aviso.setId_mensaje(rs.getInt("id_mensaje"));
                    aviso.setId_admin(rs.getInt("id_admin"));
                    aviso.setTitulo(rs.getString("titulo"));
                    aviso.setContenido(rs.getString("contenido"));
                    aviso.setFecha_creacion(rs.getTimestamp("fecha_creacion"));
                }
            }
        }

        return aviso;
    }

    public List<MensajeAdmin> findAvisosUltimos7Dias() throws SQLException {
        List<MensajeAdmin> avisos = new ArrayList<>();
        String sql = "SELECT * FROM mensajeadmin WHERE fecha_creacion >= NOW() - INTERVAL 7 DAY";

        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                MensajeAdmin aviso = new MensajeAdmin();
                aviso.setId_mensaje(rs.getInt("id_mensaje"));
                aviso.setId_admin(rs.getInt("id_admin"));
                aviso.setTitulo(rs.getString("titulo"));
                aviso.setContenido(rs.getString("contenido"));
                aviso.setFecha_creacion(rs.getTimestamp("fecha_creacion"));
                avisos.add(aviso);
            }
        }
        return avisos;
    }

    public List<MensajeAdmin> findAvisosMayoresA7Dias() throws SQLException {
        List<MensajeAdmin> avisos = new ArrayList<>();
        String sql = "SELECT * FROM mensaje_admin WHERE fecha_creacion < NOW() - INTERVAL 7 DAY";

        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                MensajeAdmin aviso = new MensajeAdmin();
                aviso.setId_mensaje(rs.getInt("id_mensaje"));
                aviso.setId_admin(rs.getInt("id_admin"));
                aviso.setTitulo(rs.getString("titulo"));
                aviso.setContenido(rs.getString("contenido"));
                aviso.setFecha_creacion(rs.getTimestamp("fecha_creacion"));
                avisos.add(aviso);
            }
        }

        return avisos;
    }

}
