package com.example.ogueta_practica_examen.controllers;

import com.example.ogueta_practica_examen.entities.Rol;
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
public class SignUpController {


    @Autowired
    JWTAuthenticationConfig jwtAuthtenticationConfig;


    @Autowired
    UsuarioRepository usuarioRepository;


    @PostMapping("/signup")
    public String signup( @RequestParam("nombre") String nombre,
                          @RequestParam("apellidos") String apellidos,
                          @RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("rol") String rol) {

        rol = rol.toUpperCase();
        username = username.toLowerCase();

        boolean rolCorrecto = false;

        for (Rol rol2 : Rol.values()) {
            if (rol2.getRol().equals(rol)) {
                rolCorrecto = true;
            }
        }

        if(rolCorrecto) {

            if (rol.equals(Rol.ADMIN.toString())) {
                return "No puedes registrarte como administrador";
            }

            List<Usuario> usuarios = usuarioRepository.getUsuarios();

            for (Usuario usuario : usuarios) {
                if (username.equals(usuario.getUsername())) {
                    return "Username en uso";
                }
            }

            Usuario usuarioRegistrado = usuarioRepository.crear(
                    new Usuario(
                            nombre,
                            apellidos,
                            username,
                            PasswordEncryptor.encrypt(password),
                            Rol.valueOf(rol)
                    )
            );


            return "Usuario " + usuarioRegistrado.getUsername() + " registrado correctamente";
        }
        return "Rol inexistente";
    }
}
