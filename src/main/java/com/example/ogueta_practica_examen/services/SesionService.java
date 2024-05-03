package com.example.ogueta_practica_examen.services;

import com.example.ogueta_practica_examen.entities.Sesion;
import com.example.ogueta_practica_examen.entities.SesionDetalle;

import java.util.List;
import java.util.Map;


public interface SesionService {
    List<Map<String, Object>> getSesiones();
    Map<String, Object> getSesion(int id);
    List<Map<String, Object>> getSesionesCreadas(int id_entrenador);
    List<Map<String, Object>> getSesionesApuntadas(int id_usuario);
    Map<String, Object> crear(Map<String, Object> datosEntrada, int id_entrenador);
    Map<String, Object> modificar(int id, Map<String, Object> datosEntrada);
    Map<String, Object> apuntarse(int id_sesion, int id_usuario);
    Map<String, Object> desapuntarse(int id_sesion);
    void eliminar(int id);
}
