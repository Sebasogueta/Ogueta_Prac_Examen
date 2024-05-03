package com.example.ogueta_practica_examen.controllers;

import com.example.ogueta_practica_examen.entities.Usuario;
import com.example.ogueta_practica_examen.repositories.UsuarioRepository;
import com.example.ogueta_practica_examen.seguridad.JWTAuthenticationConfig;
import com.example.ogueta_practica_examen.seguridad.PasswordEncryptor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {


    @Autowired
    JWTAuthenticationConfig jwtAuthtenticationConfig;


    @Autowired
    UsuarioRepository usuarioRepository;


    @PostMapping("/login")
    public String login(
            @RequestParam("user") String username,
            @RequestParam("password") String password) throws BadRequestException {


        List<Usuario> usuarios = usuarioRepository.getUsuarios();


        Usuario usuarioEncontrado = null;


        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username.toLowerCase()) && PasswordEncryptor.decrypt(usuario.getEncrypted_password()).equals(password)) {
                usuarioEncontrado = usuario;
                break;
            }
        }


        if (usuarioEncontrado == null) {
            throw new BadRequestException();
        }

        String token = jwtAuthtenticationConfig.getJWTToken(usuarioEncontrado.getUsername(), usuarioEncontrado.getRol());

        return token;
    }
}
