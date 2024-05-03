package com.example.ogueta_practica_examen.services;

import com.example.ogueta_practica_examen.entities.TipoDeVuelo;
import com.example.ogueta_practica_examen.repositories.TipoDeVueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoDeVueloServiceImpl implements TipoDeVueloService {
    private final TipoDeVueloRepository tipoDeVueloRepository;

    @Autowired
    public TipoDeVueloServiceImpl(TipoDeVueloRepository tipoDeVueloRepository){
        this.tipoDeVueloRepository  = tipoDeVueloRepository;
    }

    @Override
    public List<TipoDeVuelo> getTiposDeVuelo() {
        return tipoDeVueloRepository.getTiposDeVuelo();
    }

    @Override
    public TipoDeVuelo getTipoDeVuelo(int id) {
        return tipoDeVueloRepository.getTipoDeVuelo(id);
    }

    @Override
    public TipoDeVuelo crear(TipoDeVuelo tipoDeVuelo) {
        return tipoDeVueloRepository.crear(tipoDeVuelo);
    }

    @Override
    public TipoDeVuelo modificar(int id, TipoDeVuelo tipoDeVuelo) {
        return tipoDeVueloRepository.modificar(id, tipoDeVuelo);
    }

    @Override
    public void eliminar(int id) {
        tipoDeVueloRepository.eliminar(id);
    }
}
