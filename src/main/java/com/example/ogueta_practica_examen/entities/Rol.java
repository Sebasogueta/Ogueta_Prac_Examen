package com.example.ogueta_practica_examen.entities;

public enum Rol {
    ADMIN("ADMIN"),
    ENTRENADOR("ENTRENADOR"),
    USER("USER");


    private final String rol;


    Rol(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }

}


