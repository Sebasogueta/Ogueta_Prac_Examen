package com.example.ogueta_practica_examen.entities;

public class SesionDetalle {

    private int id_sesion;
    private int id_tipo_de_vuelo;
    private int id_entrenador;
    private int id_usuario;

    public SesionDetalle(int id_sesion, int id_tipo_de_vuelo, int id_entrenador) {
        this.id_sesion = id_sesion;
        this.id_tipo_de_vuelo = id_tipo_de_vuelo;
        this.id_entrenador = id_entrenador;
    }

    public SesionDetalle() {
    }

    public int getId_sesion() {
        return id_sesion;
    }

    public void setId_sesion(int id_sesion) {
        this.id_sesion = id_sesion;
    }

    public int getId_tipo_de_vuelo() {
        return id_tipo_de_vuelo;
    }

    public void setId_tipo_de_vuelo(int id_tipo_de_vuelo) {
        this.id_tipo_de_vuelo = id_tipo_de_vuelo;
    }

    public int getId_entrenador() {
        return id_entrenador;
    }

    public void setId_entrenador(int id_entrenador) {
        this.id_entrenador = id_entrenador;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
}
