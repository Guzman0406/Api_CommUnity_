package org.example.model;

public class EstadoReporte {
    private int id_estado_reporte;
    private String nombre_estado;

    public EstadoReporte() {
    }

    public EstadoReporte(int id_estado_reporte, String nombre_estado) {
        this.id_estado_reporte = id_estado_reporte;
        this.nombre_estado = nombre_estado;
    }

    public int getId_estado_reporte() {
        return id_estado_reporte;
    }

    public void setId_estado_reporte(int id_estado_reporte) {
        this.id_estado_reporte = id_estado_reporte;
    }

    public String getNombre_estado() {
        return nombre_estado;
    }

    public void setNombre_estado(String nombre_estado) {
        this.nombre_estado = nombre_estado;
    }
}
