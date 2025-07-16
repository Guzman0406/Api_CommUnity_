package org.example.repository;

import org.example.config.DatabaseConfig;
import org.example.model.Calle;
import org.example.model.UbicacionCalleSeccion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UbicacionCalleSeccionRepository {

    public List<UbicacionCalleSeccion> findAll() throws SQLException {
        List<UbicacionCalleSeccion> lista = new ArrayList<>();
        String query = "SELECT id_ubicacion, id_seccion, id_calle FROM ubicacioncalleseccion";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                UbicacionCalleSeccion u = new UbicacionCalleSeccion();
                u.setId_ubicacion(rs.getInt("id_ubicacion"));
                u.setId_seccion(rs.getInt("id_seccion"));
                u.setId_calle(rs.getInt("id_calle"));
                lista.add(u);
            }
        }
        return lista;
    }

    public void create(UbicacionCalleSeccion ubicacion) throws SQLException {
        String query = "INSERT INTO ubicacioncalleseccion (id_seccion, id_calle) VALUES (?, ?)";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                // Consider Statement.RETURN_GENERATED_KEYS if id_ubicacion is auto-incremented and needed
                PreparedStatement ps = c.prepareStatement(query)
        ) {
            ps.setInt(1, ubicacion.getId_seccion());
            ps.setInt(2, ubicacion.getId_calle());
            ps.executeUpdate();
        }
    }

    public void update(int id, UbicacionCalleSeccion ubicacion) throws SQLException {
        String query = "UPDATE ubicacioncalleseccion SET id_seccion = ?, id_calle = ? WHERE id_ubicacion = ?";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query)
        ) {
            ps.setInt(1, ubicacion.getId_seccion());
            ps.setInt(2, ubicacion.getId_calle());
            ps.setInt(3, id);
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String query = "DELETE FROM ubicacioncalleseccion WHERE id_ubicacion = ?";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query)
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Integer findIdUbicacion(int idSeccion, int idCalle) throws SQLException {
        String query = "SELECT id_ubicacion FROM ubicacioncalleseccion WHERE id_seccion = ? AND id_calle = ?";

        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query)
        ) {
            ps.setInt(1, idSeccion);
            ps.setInt(2, idCalle);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_ubicacion");
                }
            }
        }
        return null;
    }

    public List<Calle> findCallesBySeccion(int idSeccion) throws SQLException {
        List<Calle> calles = new ArrayList<>();
        String query = "SELECT c.id_calle, c.nombre_calle FROM calle c " +
                "JOIN ubicacioncalleseccion ucs ON c.id_calle = ucs.id_calle " +
                "WHERE ucs.id_seccion = ?";
        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query)
        ) {
            ps.setInt(1, idSeccion);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Calle calle = new Calle();
                    calle.setId_calle(rs.getInt("id_calle"));
                    calle.setNombre_calle(rs.getString("nombre_calle"));
                    calles.add(calle);
                }
            }
        }
        return calles;
    }
}

