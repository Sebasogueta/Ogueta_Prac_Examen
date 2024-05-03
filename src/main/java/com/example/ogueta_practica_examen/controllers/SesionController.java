package com.example.ogueta_practica_examen.controllers;

import com.example.ogueta_practica_examen.services.SesionService;
import com.example.ogueta_practica_examen.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sesiones")
public class SesionController {
    private final SesionService sesionService;
    private final UsuarioService usuarioService;

    @Autowired
    public SesionController(SesionService sesionService, UsuarioService usuarioService){
        this.sesionService = sesionService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Map<String, Object>> obtenerTodos() {
        return sesionService.getSesiones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerPorId(@PathVariable int id) {
        Map<String, Object> sesion = sesionService.getSesion(id);

        if (sesion != null) {
            return new ResponseEntity<>(sesion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/mis-sesiones") //Sesiones creadas por el entrenador autenticado
    public List<Map<String, Object>> misSesiones() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int id_entrenador = usuarioService.getUsuario(username);

        return sesionService.getSesionesCreadas(id_entrenador);

    }

    @GetMapping("/apuntadas") //Sesiones en las que se apuntó el usuario autenticado
    public List<Map<String, Object>> sesionesApuntadas() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int id_usuario = usuarioService.getUsuario(username);

        return sesionService.getSesionesApuntadas(id_usuario);

    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearSesion(@RequestBody Map<String, Object> datos) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int id_entrenador = usuarioService.getUsuario(username);

        Map<String, Object> sesion2 = sesionService.crear(datos, id_entrenador);

        if (sesion2 != null) {
            return new ResponseEntity<>(sesion2, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")  // modificar una sesión (solo la puede modificar el entrenador que la creó. No puede modificar el id, ni usuarios apuntados, ni id_entrenador)
    public ResponseEntity<Map<String, Object>> modificarSesion(@PathVariable int id, @RequestBody Map<String, Object> datosEntrada) {

        List<Map<String, Object>> sesionesList = misSesiones(); //sesiones creadas por el entrenador

        boolean encontrado = false;

        if(sesionesList != null) {
            for (Map<String, Object> sesion2 : sesionesList) { // recorre las sesiones creadas por el entrenador autenticado
                if ((Integer) sesion2.get("id") == id) { // si la sesion que el entrenador intenta modificar fue creada por él, la modifica
                    encontrado = true;
                }
            }
        }

        if(encontrado) {
            Map<String, Object> sesion2 = sesionService.modificar(id, datosEntrada);
            if (sesion2 != null) {
                return new ResponseEntity<>(sesion2, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/{id}/apuntarse") //apuntarse en x sesion (si es posible)
    public ResponseEntity<Map<String, Object>> apuntarse(@PathVariable int id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int id_usuario = usuarioService.getUsuario(username);
        Map<String, Object> sesion = sesionService.getSesion(id);

        if(sesion != null) {

            if (((String)sesion.get("usuario")).equals("Nadie apuntado")) {

                Map<String, Object> sesion2 = sesionService.apuntarse(id, id_usuario);

                if (sesion2 != null) {
                    return new ResponseEntity<>(sesion2, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }

            }
            else{
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}/desapuntarse") // desapuntarse de x sesion (si es posible)
    public ResponseEntity<Map<String, Object>> desapuntarse(@PathVariable int id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> sesion = sesionService.getSesion(id);

        if(sesion != null) {

            if (((String)sesion.get("usuario")).equals(username)) {

                Map<String, Object> sesion2 = sesionService.desapuntarse(id);

                if (sesion2 != null) {
                    return new ResponseEntity<>(sesion2, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
            else{
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}") // eliminar una sesión (solo la puede eliminar el entrenador que la creó)
    public void eliminarSesion(@PathVariable int id) {

        List<Map<String, Object>> sesionesList = misSesiones(); //sesiones creadas por el entrenador

        if(sesionesList != null) {
            for (Map<String, Object> sesion : sesionesList) { // recorre las sesiones creadas por el entrenador autenticado
                if ((Integer) sesion.get("id") == id) { // si la sesion que el entrenador intenta eliminar fue creada por él, la elimina
                    sesionService.eliminar(id);
                    break;
                }
            }
        }

    }


}
