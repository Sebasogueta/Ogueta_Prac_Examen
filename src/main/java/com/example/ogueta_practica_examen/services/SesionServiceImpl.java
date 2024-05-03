package com.example.ogueta_practica_examen.services;

import com.example.ogueta_practica_examen.entities.Sesion;
import com.example.ogueta_practica_examen.entities.SesionDetalle;
import com.example.ogueta_practica_examen.repositories.SesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SesionServiceImpl implements SesionService {
    private final SesionRepository sesionRepository;

    @Autowired
    public SesionServiceImpl(SesionRepository sesionRepository){
        this.sesionRepository  = sesionRepository;
    }

    @Override
    public List<Map<String, Object>> getSesiones() {
        return sesionRepository.getSesiones();
    }

    @Override
    public Map<String, Object> getSesion(int id) {
        return sesionRepository.getSesion(id);
    }

    @Override
    public List<Map<String, Object>> getSesionesCreadas(int id_entrenador) {
        return sesionRepository.getSesionesCreadas(id_entrenador);
    }

    @Override
    public List<Map<String, Object>> getSesionesApuntadas(int id_usuario) {
        return sesionRepository.getSesionesApuntadas(id_usuario);
    }

    @Override
    public Map<String, Object> crear(Map<String, Object> datosEntrada, int id_entrenador) {
        return sesionRepository.crear(datosEntrada, id_entrenador);
    }

    @Override
    public Map<String, Object> modificar(int id, Map<String, Object> datosEntrada) {
        return sesionRepository.modificar(id, datosEntrada);
    }

    @Override
    public Map<String, Object> apuntarse(int id_sesion, int id_usuario) {
        return sesionRepository.apuntarse(id_sesion, id_usuario);
    }
    @Override
    public Map<String, Object> desapuntarse(int id_sesion) {
        return sesionRepository.desapuntarse(id_sesion);
    }
    @Override
    public void eliminar(int id) {
        sesionRepository.eliminar(id);
    }
}
