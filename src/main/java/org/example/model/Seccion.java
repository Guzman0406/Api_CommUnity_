package org.example.model;

public class Seccion {

    private int id_seccion;
    private String nombre_seccion;

    public Seccion() {
    }

    public Seccion(int id_seccion, String nombre_seccion) {
        this.id_seccion = id_seccion;
        this.nombre_seccion = nombre_seccion;
    }

    public int getId_seccion() {
        return id_seccion;
    }

    public void setId_seccion(int id_seccion) {
        this.id_seccion = id_seccion;
    }

    public String getNombre_seccion() {
        return nombre_seccion;
    }

    public void setNombre_seccion(String nombre_seccion) {
        this.nombre_seccion = nombre_seccion;
    }
}