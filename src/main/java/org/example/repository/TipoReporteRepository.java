package org.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.config.DatabaseConfig;
import org.example.model.TipoReporte;

public class TipoReporteRepository {
    public List<TipoReporte> findAll() throws SQLException {
        List<TipoReporte> tipos = new ArrayList();
        String query = "SELECT * FROM tiporeporte";

        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement ps = c.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
        ) {
            while(rs.next()) {
                TipoReporte tipo = new TipoReporte();
                tipo.setId_tipo_reporte(rs.getInt("id_tipo"));
                tipo.setNombre_tipo(rs.getString("nombre_tipo"));
                tipo.setId_nivel_urgencia(rs.getInt("id_nivel_urgencia"));
                tipos.add(tipo);
            }
        }

        return tipos;
    }

    public int save(TipoReporte tipoReporte) {
        String query = "INSERT INTO tipo_reporte (nombre_tipo, id_nivel_urgencia) VALUES (?, ?)";

        try {
            try (
                    Connection c = DatabaseConfig.getDataSource().getConnection();
                    PreparedStatement ps = c.prepareStatement(query, 1);
            ) {
                ps.setString(1, tipoReporte.getNombre_tipo());
                ps.setInt(2, tipoReporte.getId_nivel_urgencia());
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int var6 = rs.getInt(1);
                        return var6;
                    }
                }
            }

            return -1;
        } catch (SQLException e) {
            throw new RuntimeException("Error insertando tipo de reporte", e);
        }
    }

    public boolean update(int id, TipoReporte tipoReporte) {
        String query = "UPDATE tipo_reporte SET nombre_tipo = ?, id_nivel_urgencia = ? WHERE id_tipo = ?";

        try {
            boolean var6;
            try (
                    Connection c = DatabaseConfig.getDataSource().getConnection();
                    PreparedStatement ps = c.prepareStatement(query);
            ) {
                ps.setString(1, tipoReporte.getNombre_tipo());
                ps.setInt(2, tipoReporte.getId_nivel_urgencia());
                ps.setInt(3, id);
                var6 = ps.executeUpdate() == 1;
            }

            return var6;
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando tipo de reporte", e);
        }
    }

    public boolean delete(int id) {
        String query = "DELETE FROM tipo_reporte WHERE id_tipo = ?";

        try {
            boolean var5;
            try (
                    Connection c = DatabaseConfig.getDataSource().getConnection();
                    PreparedStatement ps = c.prepareStatement(query);
            ) {
                ps.setInt(1, id);
                var5 = ps.executeUpdate() == 1;
            }

            return var5;
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando tipo de reporte", e);
        }
    }
}