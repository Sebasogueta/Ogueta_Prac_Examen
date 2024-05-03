package com.example.ogueta_practica_examen.controllers;

import com.example.ogueta_practica_examen.entities.TipoDeVuelo;
import com.example.ogueta_practica_examen.services.TipoDeVueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vuelos")
public class TipoDeVueloController {
    private final TipoDeVueloService tipoDeVueloService;

    @Autowired
    public TipoDeVueloController(TipoDeVueloService tipoDeVueloService){
        this.tipoDeVueloService = tipoDeVueloService;
    }

    @GetMapping
    public List<TipoDeVuelo> obtenerTodos() {
        return tipoDeVueloService.getTiposDeVuelo();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDeVuelo> obtenerPorId(@PathVariable int id) {
        TipoDeVuelo tipoDeVuelo = tipoDeVueloService.getTipoDeVuelo(id);

        if (tipoDeVuelo != null) {
            return new ResponseEntity<>(tipoDeVuelo, HttpStatus.OK);
        } else {
            // Enviar una respuesta HTTP 404 si la tarea no se encuentra
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TipoDeVuelo> crearTipoDeVuelo(@RequestBody TipoDeVuelo tipoDeVuelo) {
        TipoDeVuelo tipoDeVuelo2 = tipoDeVueloService.crear(tipoDeVuelo);

        if (tipoDeVuelo2 != null) {
            return new ResponseEntity<>(tipoDeVuelo2, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoDeVuelo> modificarTipoDeVuelo(@PathVariable int id, @RequestBody TipoDeVuelo tipoDeVuelo) {
        TipoDeVuelo tipoDeVuelo2 = tipoDeVueloService.modificar(id, tipoDeVuelo);

        if (tipoDeVuelo2 != null) {
            return new ResponseEntity<>(tipoDeVuelo2, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void eliminarTipoDeVuelo(@PathVariable int id) {
        tipoDeVueloService.eliminar(id);
    }

}
