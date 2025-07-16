package org.example.model;

import java.sql.Timestamp;

public class MensajeAdmin {

    private int id_mensaje;
    private int id_admin;
    private String titulo;
    private String contenido;
    private Timestamp fecha_creacion;

    public MensajeAdmin() {
    }

    public MensajeAdmin(int id_mensaje, int id_admin, String titulo, String contenido, Timestamp fecha_creacion) {
        this.id_mensaje = id_mensaje;
        this.id_admin = id_admin;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha_creacion = fecha_creacion;
    }

    public int getId_mensaje() {
        return id_mensaje;
    }

    public void setId_mensaje(int id_mensaje) {
        this.id_mensaje = id_mensaje;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Timestamp getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Timestamp fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
}
