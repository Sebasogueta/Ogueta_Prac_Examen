package com.example.ogueta_practica_examen.services;

import com.example.ogueta_practica_examen.entities.Usuario;

import java.util.List;


public interface UsuarioService {
    List<Usuario> getUsuarios();
    Usuario getUsuario(int id);
    int getUsuario(String username);
    Usuario crear(Usuario usuario);
    Usuario modificar(int id, String rol);
    void eliminar(int id);
}
