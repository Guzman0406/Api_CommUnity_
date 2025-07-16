package org.example.model;

public class TipoReporte {

    private int id_tipo;
    private String nombre_tipo;
    private int id_nivel_urgencia;

    public TipoReporte() {
    }

    public TipoReporte(int id_tipo, String nombre_tipo, int id_nivel_urgencia) {
        this.id_tipo = id_tipo;
        this.nombre_tipo = nombre_tipo;
        this.id_nivel_urgencia = id_nivel_urgencia;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getNombre_tipo() {
        return nombre_tipo;
    }

    public void setNombre_tipo(String nombre_tipo) {
        this.nombre_tipo = nombre_tipo;
    }

    public int getId_nivel_urgencia() {
        return id_nivel_urgencia;
    }

    public void setId_nivel_urgencia(int id_nivel_urgencia) {
        this.id_nivel_urgencia = id_nivel_urgencia;
    }

    public void setId_tipo_reporte(int idTipo) {
    }
}
