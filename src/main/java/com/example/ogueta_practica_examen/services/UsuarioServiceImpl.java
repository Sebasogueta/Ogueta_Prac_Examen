package com.example.ogueta_practica_examen.services;

import com.example.ogueta_practica_examen.entities.Usuario;
import com.example.ogueta_practica_examen.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository  = usuarioRepository;
    }

    @Override
    public List<Usuario> getUsuarios() {
        return usuarioRepository.getUsuarios();
    }

    @Override
    public Usuario getUsuario(int id) {
        return usuarioRepository.getUsuario(id);
    }

    @Override
    public int getUsuario(String username) {
        return usuarioRepository.getUsuario(username);
    }

    @Override
    public Usuario crear(Usuario usuario) {
        return usuarioRepository.crear(usuario);
    }

    @Override
    public Usuario modificar(int id, String rol) {
        return usuarioRepository.modificar(id,rol);
    }

    @Override
    public void eliminar(int id) {
        usuarioRepository.eliminar(id);
    }
}
