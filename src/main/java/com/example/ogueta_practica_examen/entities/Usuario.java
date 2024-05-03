package com.example.ogueta_practica_examen.entities;

public class Usuario {

    private int id_usuario;
    private String nombre;
    private String apellidos;
    private String username;
    private String encrypted_password;
    private Rol rol;

    public Usuario(int id_usuario, String nombre, String apellidos, String username, String encrypted_password, Rol rol) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.username = username;
        this.encrypted_password = encrypted_password;
        this.rol = rol;
    }

    public Usuario(String nombre, String apellidos, String username, String encrypted_password, Rol rol) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.username = username;
        this.encrypted_password = encrypted_password;
        this.rol = rol;
    }

    public Usuario() {
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncrypted_password() {
        return encrypted_password;
    }

    public void setEncrypted_password(String encrypted_password) {
        this.encrypted_password = encrypted_password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id_usuario=" + id_usuario +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", username='" + username + '\'' +
                ", encrypted_password='" + encrypted_password + '\'' +
                ", rol=" + rol +
                '}';
    }


}
