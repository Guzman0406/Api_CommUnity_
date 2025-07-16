package org.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.config.DatabaseConfig;
import org.example.model.Seccion;

public class SeccionRepository {
    public List<Seccion> findAll() throws SQLException {
        List<Seccion> secciones = new ArrayList();
        String query = "SELECT id_seccion, nombre_seccion FROM seccion";

        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
        ) {
            while(rs.next()) {
                Seccion seccion = new Seccion();
                seccion.setId_seccion(rs.getInt("id_seccion"));
                seccion.setNombre_seccion(rs.getString("nombre_seccion"));
                secciones.add(seccion);
            }
        }

        return secciones;
    }

    public void create(Seccion seccion) throws SQLException {
        String query = "INSERT INTO seccion(nombre_seccion) VALUES (?)";

        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query);
        ) {
            ps.setString(1, seccion.getNombre_seccion());
            ps.executeUpdate();
        }

    }

    public void update(int id, Seccion seccion) throws SQLException {
        String query = "UPDATE seccion SET nombre_seccion = ? WHERE id_seccion = ?";

        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query);
        ) {
            ps.setString(1, seccion.getNombre_seccion());
            ps.setInt(2, id);
            ps.executeUpdate();
        }

    }

    public void delete(int id) throws SQLException {
        String query = "DELETE FROM seccion WHERE id_seccion = ?";

        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query);
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }

    }
}
