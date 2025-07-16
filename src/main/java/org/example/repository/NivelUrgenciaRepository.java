package org.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.config.DatabaseConfig;
import org.example.model.NivelUrgencia;

public class NivelUrgenciaRepository {
    public List<NivelUrgencia> findAll() throws SQLException {
        List<NivelUrgencia> niveles = new ArrayList();
        String query = "SELECT * FROM nivelurgencia";

        try (
                Connection c = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement stmt = c.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
        ) {
            while(rs.next()) {
                NivelUrgencia nivel = new NivelUrgencia();
                nivel.setId_nivel_urgencia(rs.getInt("id_nivel_urgencia"));
                nivel.setNombre_nivel(rs.getString("nombre_nivel"));
                niveles.add(nivel);
            }
        }

        return niveles;
    }

    public int save(NivelUrgencia nivel) {
        String query = "INSERT INTO niveles_urgencia (nombre_nivel) VALUES (?)";

        try {
            try (
                    Connection c = DatabaseConfig.getDataSource().getConnection();
                    PreparedStatement ps = c.prepareStatement(query, 1);
            ) {
                ps.setString(1, nivel.getNombre_nivel());
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
            throw new RuntimeException("Error insertando nivel de urgencia", e);
        }
    }

    public boolean update(int id_nivel_urgencia, NivelUrgencia nivel) {
        String query = "UPDATE niveles_urgencia SET nombre_nivel = ? WHERE id_nivel_urgencia = ?";

        try {
            boolean var6;
            try (
                    Connection c = DatabaseConfig.getDataSource().getConnection();
                    PreparedStatement ps = c.prepareStatement(query);
            ) {
                ps.setString(1, nivel.getNombre_nivel());
                ps.setInt(2, id_nivel_urgencia);
                var6 = ps.executeUpdate() == 1;
            }

            return var6;
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando nivel de urgencia", e);
        }
    }

    public boolean delete(int id_nivel_urgencia) {
        String query = "DELETE FROM niveles_urgencia WHERE id_nivel_urgencia = ?";

        try {
            boolean var5;
            try (
                    Connection c = DatabaseConfig.getDataSource().getConnection();
                    PreparedStatement ps = c.prepareStatement(query);
            ) {
                ps.setInt(1, id_nivel_urgencia);
                var5 = ps.executeUpdate() == 1;
            }

            return var5;
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando nivel de urgencia", e);
        }
    }
}