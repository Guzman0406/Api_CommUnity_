package org.example.repository;

import org.example.config.DatabaseConfig;
import org.example.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    public List<Usuario> findAll() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM usuario";



        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement stmt = c.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido_paterno(rs.getString("apellido_paterno"));
                usuario.setApellido_materno(rs.getString("apellido_materno"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContraseña(rs.getString("contraseña")); // agregado
                usuario.setEs_admin(rs.getBoolean("es_admin"));
                usuario.setId_ubicacion(rs.getInt("id_ubicacion"));

                usuarios.add(usuario);
            }
        }

        return usuarios;
    }

    public int save(Usuario usuario) {
        String query = "INSERT INTO usuario (nombre, apellido_paterno, apellido_materno, correo, contraseña, es_admin, id_ubicacion) VALUES (?,?, ?, ?, ?, ?, ?)";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido_paterno());
            ps.setString(3, usuario.getApellido_materno());
            ps.setString(4, usuario.getCorreo());
            ps.setString(5, usuario.getContraseña());
            ps.setBoolean(6, usuario.isEs_admin());
            ps.setInt( 7, usuario.getId_ubicacion());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting usuario", e);
        }
        return -1;
    }

    public boolean update(int id_usuario, Usuario usuario) {
        String query = "UPDATE usuario SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, correo = ?, contraseña = ?, es_admin = ?, id_ubicacion = ? WHERE id_usuario = ?";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query)
        ) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido_paterno());
            ps.setString(3, usuario.getApellido_materno());
            ps.setString(4, usuario.getCorreo());
            ps.setString(5, usuario.getContraseña());
            ps.setBoolean(6, usuario.isEs_admin());
            ps.setInt(7, usuario.getId_ubicacion());
            ps.setInt(8, id_usuario);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating usuario", e);
        }
    }

    public boolean delete(int id_usuario) {
        String query = "DELETE FROM usuario WHERE id_usuario = ?";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query)
        ) {
            ps.setInt(1, id_usuario);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting usuario", e);
        }
    }

    public List<Usuario> findByAdminStatus(boolean es_admin) throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM usuario WHERE es_admin = ?";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query)
        ) {
            ps.setBoolean(1, es_admin);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido_paterno(rs.getString("apellido_paterno"));
                usuario.setApellido_materno(rs.getString("apellido_materno"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContraseña(rs.getString("contraseña")); // agregado
                usuario.setEs_admin(rs.getBoolean("es_admin"));
                usuario.setId_ubicacion(rs.getInt("id_ubicacion"));

                usuarios.add(usuario);
            }
        }
        return usuarios;
    }
    public Usuario findByCorreo(String correo) throws SQLException {
        Usuario usuario = null;
        String query = "SELECT id_usuario, nombre, apellido_paterno, apellido_materno, correo, contraseña, es_admin, id_ubicacion FROM usuario WHERE correo = ?";

        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query);
        ) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId_usuario(rs.getInt("id_usuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellido_paterno(rs.getString("apellido_paterno"));
                    usuario.setApellido_materno(rs.getString("apellido_materno"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setContraseña(rs.getString("contraseña"));
                    usuario.setEs_admin(rs.getBoolean("es_admin"));
                    usuario.setId_ubicacion(rs.getInt("id_ubicacion"));

                }
            }
        }
        return usuario;
    }
    public Usuario login(String correo, String contraseña) throws SQLException {
        String query = "SELECT * FROM usuario WHERE correo = ? AND contraseña = ?";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query)
        ) {
            ps.setString(1, correo);
            ps.setString(2, contraseña);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId_usuario(rs.getInt("id_usuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellido_paterno(rs.getString("apellido_paterno"));
                    usuario.setApellido_materno(rs.getString("apellido_materno"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setContraseña(rs.getString("contraseña"));
                    usuario.setEs_admin(rs.getBoolean("es_admin"));
                    usuario.setId_ubicacion(rs.getInt("id_ubicacion"));

                    return usuario;
                }
            }
        }
        return null;
    }

}
