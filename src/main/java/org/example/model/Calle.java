package org.example.model;

public class Calle {
    private int id_calle;
    private String nombre_calle;

    public Calle() {
    }

    public Calle(int id_calle, String nombre_calle) {
        this.id_calle = id_calle;
        this.nombre_calle = nombre_calle;
    }

    public int getId_calle() {
        return id_calle;
    }

    public void setId_calle(int id_calle) {
        this.id_calle = id_calle;
    }

    public String getNombre_calle() {
        return nombre_calle;
    }

    public void setNombre_calle(String nombre_calle) {
        this.nombre_calle = nombre_calle;
    }
}