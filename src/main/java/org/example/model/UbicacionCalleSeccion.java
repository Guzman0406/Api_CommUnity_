package org.example.model;

public class UbicacionCalleSeccion {
    private int id_ubicacion;
    private int id_seccion;
    private int id_calle;

    public UbicacionCalleSeccion(){
    }

    public UbicacionCalleSeccion (int id_ubicacion, int id_seccion, int id_calle){
        this.id_ubicacion = id_ubicacion;
        this.id_seccion = id_seccion;
        this.id_calle = id_calle;
    }

    public int getId_ubicacion() {
        return id_ubicacion;

    }

    public void setId_ubicacion(int id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }

    public int getId_seccion() {
        return id_seccion;
    }

    public void setId_seccion(int id_seccion) {
        this.id_seccion = id_seccion;
    }

    public int getId_calle() {
        return id_calle;
    }

    public void setId_calle(int id_calle) {
        this.id_calle = id_calle;
    }
}
