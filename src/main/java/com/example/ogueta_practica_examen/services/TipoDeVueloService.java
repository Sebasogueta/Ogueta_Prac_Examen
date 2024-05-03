package com.example.ogueta_practica_examen.services;

import com.example.ogueta_practica_examen.entities.TipoDeVuelo;
import java.util.List;


public interface TipoDeVueloService {
    List<TipoDeVuelo> getTiposDeVuelo();
    TipoDeVuelo getTipoDeVuelo(int id);
    TipoDeVuelo crear(TipoDeVuelo tipoDeVuelo);
    TipoDeVuelo modificar(int id, TipoDeVuelo tipoDeVuelo);
    void eliminar(int id);
}
