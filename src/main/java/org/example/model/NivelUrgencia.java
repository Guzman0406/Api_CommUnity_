package org.example.model;

public class NivelUrgencia {
    private int id_nivel_urgencia;
    private String nombre_nivel;

    public NivelUrgencia() {
    }

    public NivelUrgencia(int id_nivel_urgencia, String nombre_nivel) {
        this.id_nivel_urgencia = id_nivel_urgencia;
        this.nombre_nivel = nombre_nivel;
    }

    // Getters y Setters
    public int getId_nivel_urgencia() {
        return id_nivel_urgencia;
    }

    public void setId_nivel_urgencia(int id_nivel_urgencia) {
        this.id_nivel_urgencia = id_nivel_urgencia;
    }

    public String getNombre_nivel() {
        return nombre_nivel;
    }

    public void setNombre_nivel(String nombre_nivel) {
        this.nombre_nivel = nombre_nivel;
    }
}