package com.example.ogueta_practica_examen.controllers;

import com.example.ogueta_practica_examen.entities.Rol;
import com.example.ogueta_practica_examen.entities.Usuario;
import com.example.ogueta_practica_examen.seguridad.PasswordEncryptor;
import com.example.ogueta_practica_examen.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioService.getUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable int id) {
        Usuario usuario = usuarioService.getUsuario(id);

        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            // Enviar una respuesta HTTP 404 si la tarea no se encuentra
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {

        usuario.setEncrypted_password(PasswordEncryptor.encrypt(usuario.getEncrypted_password()));
        Usuario usuario2 = usuarioService.crear(usuario);

        if (usuario2 != null) {
            return new ResponseEntity<>(usuario2, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> modificarUsuario(@PathVariable int id, @RequestParam String rol) {

        rol = rol.toUpperCase();

        boolean rolCorrecto = false;

        for (Rol rol2 : Rol.values()) {
            if (rol2.getRol().equals(rol)) {
                rolCorrecto = true;
            }
        }
        if(rolCorrecto) {
            Usuario usuario2 = usuarioService.modificar(id, rol);

            if (usuario2 != null) {
                return new ResponseEntity<>(usuario2, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable int id) {
        usuarioService.eliminar(id);
    }

}
