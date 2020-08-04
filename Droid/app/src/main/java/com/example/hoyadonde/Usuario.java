package com.example.hoyadonde;

import java.io.Serializable;

public class Usuario implements Serializable {
    public int usuario_id;
    public String username;
    public String password;
    public String nombre;
    public int edad;
    public String ubicacion;

    public Usuario(){}

    public Usuario(int usuario_id, String username, String password, String nombre, int edad, String ubicacion) {
        this.usuario_id = usuario_id;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.edad = edad;
        this.ubicacion = ubicacion;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
