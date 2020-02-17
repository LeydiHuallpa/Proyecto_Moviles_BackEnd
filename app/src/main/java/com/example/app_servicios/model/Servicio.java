package com.example.app_servicios.model;

public class Servicio {

    private String Servicio_id;
    private String Servicio_nombre;
    private String Servicio_descripcion;
    private String Servicio_precio;
    private String Servicio_categoria;


    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double latitud;
    public Double longitud;


    public Servicio(){

    }

    public String getServicio_id() {
        return Servicio_id;
    }

    public void setServicio_id(String servicio_id) {
        Servicio_id = servicio_id;
    }

    public String getServicio_nombre() {
        return Servicio_nombre;
    }

    public void setServicio_nombre(String servicio_nombre) {
        Servicio_nombre = servicio_nombre;
    }

    public String getServicio_descripcion() {
        return Servicio_descripcion;
    }

    public void setServicio_descripcion(String servicio_descripcion) {
        Servicio_descripcion = servicio_descripcion;
    }

    public String getServicio_precio() {
        return Servicio_precio;
    }

    public void setServicio_precio(String servicio_precio) {
        Servicio_precio = servicio_precio;
    }

    public String getServicio_categoria() {
        return Servicio_categoria;
    }

    public void setServicio_categoria(String servicio_categoria) {
        Servicio_categoria = servicio_categoria;
    }

    @Override
    public String toString() {
        return Servicio_nombre;
    }
}
