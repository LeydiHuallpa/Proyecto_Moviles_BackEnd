package com.example.app_servicios.model;

public class Servicio {

    private String Servicio_id;

    public Servicio(String servicio_id, String servicio_nombre, String servicio_descripcion, String servicio_precio, String servicio_categoria, String servicio_estado, String servicio_latitud, String servicio_longitud) {
        Servicio_id = servicio_id;
        Servicio_nombre = servicio_nombre;
        Servicio_descripcion = servicio_descripcion;
        Servicio_precio = servicio_precio;
        Servicio_categoria = servicio_categoria;
        Servicio_estado = servicio_estado;
        Servicio_latitud = servicio_latitud;
        Servicio_longitud = servicio_longitud;
    }

    private String Servicio_nombre;
    private String Servicio_descripcion;
    private String Servicio_precio;
    private String Servicio_categoria;
    private String Servicio_estado;

    public String getServicio_estado() {
        return Servicio_estado;
    }

    public void setServicio_estado(String servicio_estado) {
        Servicio_estado = servicio_estado;
    }



    public String getServicio_latitud() {
        return Servicio_latitud;
    }

    public void setServicio_latitud(String servicio_latitud) {
        Servicio_latitud = servicio_latitud;

    }

    public String getServicio_longitud() {
        return Servicio_longitud;
    }

    public void setServicio_longitud(String servicio_longitud) {
        Servicio_longitud = servicio_longitud;
    }

    private String Servicio_latitud;
    private String Servicio_longitud;



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
