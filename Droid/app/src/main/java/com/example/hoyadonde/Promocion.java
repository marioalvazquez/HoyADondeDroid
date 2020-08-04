package com.example.hoyadonde;

import java.io.Serializable;

public class Promocion implements Serializable {
    public int promocion_id;
    public String nombre;
    public String descripcion;
    public String horario;
    public String url;
    public String empresa;
    public String fechas;
    public String ubicacion;

    public  Promocion() {

    }

    public Promocion(int promocion_id, String nombre, String descripcion, String horario, String url, String empresa, String fechas, String ubicacion) {
        this.promocion_id = promocion_id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.horario = horario;
        this.url = url;
        this.empresa = empresa;
        this.fechas = fechas;
        this.ubicacion = ubicacion;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public int getPromocion_id() {
        return promocion_id;
    }

    public void setPromocion_id(int promocion_id) {
        this.promocion_id = promocion_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getFechas() {
        return fechas;
    }

    public void setFechas(String fechas) {
        this.fechas = fechas;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}

