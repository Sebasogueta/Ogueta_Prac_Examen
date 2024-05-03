package com.example.ogueta_practica_examen.entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Sesion {

    private int id_sesion;
    private String nombre;
    private LocalDate fecha;
    private LocalTime hora;

    public Sesion(int id_sesion, String nombre, LocalDate fecha, LocalTime hora, int id_tipo_de_vuelo, int id_entrenador) {
        this.id_sesion = id_sesion;
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;

    }

    public Sesion(int id_sesion, String nombre, LocalDate fecha, LocalTime hora, int id_tipo_de_vuelo, int id_entrenador, int id_usuario) {
        this.id_sesion = id_sesion;
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;

    }

    public Sesion() {
    }

    public int getId_sesion() {
        return id_sesion;
    }

    public void setId_sesion(int id_sesion) {
        this.id_sesion = id_sesion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }


}
