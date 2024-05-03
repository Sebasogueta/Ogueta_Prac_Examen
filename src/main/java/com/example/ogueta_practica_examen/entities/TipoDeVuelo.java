package com.example.ogueta_practica_examen.entities;

public class TipoDeVuelo {

    private int id_tipo_de_vuelo;
    private String nombre;
    private int duracion_en_minutos;
    private String provincia_origen;
    private String localidad_origen;
    private String destino;

    public TipoDeVuelo(int id_tipo_de_vuelo, String nombre, int duracion_en_minutos, String provincia_origen, String localidad_origen, String destino) {
        this.id_tipo_de_vuelo = id_tipo_de_vuelo;
        this.nombre = nombre;
        this.duracion_en_minutos = duracion_en_minutos;
        this.provincia_origen = provincia_origen;
        this.localidad_origen = localidad_origen;
        this.destino = destino;
    }

    public TipoDeVuelo() {
    }

    public int getId_tipo_de_vuelo() {
        return id_tipo_de_vuelo;
    }

    public void setId_tipo_de_vuelo(int id_tipo_de_vuelo) {
        this.id_tipo_de_vuelo = id_tipo_de_vuelo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion_en_minutos() {
        return duracion_en_minutos;
    }

    public void setDuracion_en_minutos(int duracion_en_minutos) {
        this.duracion_en_minutos = duracion_en_minutos;
    }

    public String getProvincia_origen() {
        return provincia_origen;
    }

    public void setProvincia_origen(String provincia_origen) {
        this.provincia_origen = provincia_origen;
    }

    public String getLocalidad_origen() {
        return localidad_origen;
    }

    public void setLocalidad_origen(String localidad_origen) {
        this.localidad_origen = localidad_origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}
