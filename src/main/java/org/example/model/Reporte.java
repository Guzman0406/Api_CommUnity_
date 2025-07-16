package org.example.model;

import java.sql.Timestamp;

public class Reporte {

    private int id_reporte;
    private int id_usuario;
    private int id_tipo;
    private int id_ubicacion;
    private String referencia;
    private String descripcion;
    private int id_estado;
    private Timestamp fecha_creacion;
    private Timestamp fecha_actualizacion;
    private boolean activo;

    public Reporte() {
    }

    public Reporte(int id_reporte, int id_usuario, int id_tipo,
                   int id_ubicacion, String referencia, String descripcion,
                   int id_estado, Timestamp fecha_creacion,
                   Timestamp fecha_actualizacion, boolean activo) {
        this.id_reporte = id_reporte;
        this.id_usuario = id_usuario;
        this.id_tipo = id_tipo;
        this.id_ubicacion = id_ubicacion;
        this.referencia = referencia;
        this.descripcion = descripcion;
        this.id_estado = id_estado;
        this.fecha_creacion = fecha_creacion;
        this.fecha_actualizacion = fecha_actualizacion;
        this.activo = activo;
    }

    public int getId_reporte() {
        return id_reporte;
    }

    public void setId_reporte(int id_reporte) {
        this.id_reporte = id_reporte;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getid_tipo() {
        return id_tipo;
    }

    public void setid_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public int getId_ubicacion() {
        return id_ubicacion;
    }

    public void setId_ubicacion(int id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public Timestamp getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Timestamp fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Timestamp getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(Timestamp fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }


    public boolean isActivo() {
        return activo;
    }


    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}