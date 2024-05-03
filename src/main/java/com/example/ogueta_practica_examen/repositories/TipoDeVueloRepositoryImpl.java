package com.example.ogueta_practica_examen.repositories;

import com.example.ogueta_practica_examen.entities.TipoDeVuelo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipoDeVueloRepositoryImpl implements TipoDeVueloRepository {
    private final JdbcTemplate jdbcTemplate;

    public TipoDeVueloRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TipoDeVuelo> getTiposDeVuelo() {
        String sql = "SELECT id, nombre, duracion_en_minutos, provincia_origen, localidad_origen, destino FROM tipo_de_vuelo";
        return jdbcTemplate.query(sql, (resultSet, rowNum) ->
                new TipoDeVuelo(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getInt("duracion_en_minutos"),
                        resultSet.getString("provincia_origen"),
                        resultSet.getString("localidad_origen"),
                        resultSet.getString("destino")
                )
        );
    }

    @Override
    public TipoDeVuelo getTipoDeVuelo(int id) {
        String sql = "SELECT id, nombre, duracion_en_minutos, provincia_origen, localidad_origen, destino FROM tipo_de_vuelo WHERE id = ?";
        List<TipoDeVuelo> tipoDeVuelos = jdbcTemplate.query(sql, new Object[]{id}, (resultSet, rowNum) ->
                new TipoDeVuelo(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getInt("duracion_en_minutos"),
                        resultSet.getString("provincia_origen"),
                        resultSet.getString("localidad_origen"),
                        resultSet.getString("destino")
                )
        );

        return tipoDeVuelos.isEmpty() ? null : tipoDeVuelos.get(0);
    }

    @Override
    public TipoDeVuelo crear(TipoDeVuelo tipoDeVuelo2) {

        String insertSql = "INSERT INTO tipo_de_vuelo (nombre, duracion_en_minutos, provincia_origen, localidad_origen, destino) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(insertSql,
                tipoDeVuelo2.getNombre(),
                tipoDeVuelo2.getDuracion_en_minutos(),
                tipoDeVuelo2.getProvincia_origen(),
                tipoDeVuelo2.getLocalidad_origen(),
                tipoDeVuelo2.getDestino());

        String selectSql = "SELECT * FROM tipo_de_vuelo ORDER BY id DESC LIMIT 1";
        List<TipoDeVuelo> tipoDeVuelos = jdbcTemplate.query(selectSql, (resultSet, rowNum) ->
                new TipoDeVuelo(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getInt("duracion_en_minutos"),
                        resultSet.getString("provincia_origen"),
                        resultSet.getString("localidad_origen"),
                        resultSet.getString("destino")
                )
        );

        return tipoDeVuelos.isEmpty() ? null : tipoDeVuelos.get(0);

    }

    @Override
    public TipoDeVuelo modificar(int id, TipoDeVuelo tipoDeVuelo2) {
        String updateSql = "UPDATE tipo_de_vuelo SET nombre = ?, duracion_en_minutos = ?, provincia_origen = ?, localidad_origen = ?, destino = ? WHERE id = ?";
        jdbcTemplate.update(updateSql,
                tipoDeVuelo2.getNombre(),
                tipoDeVuelo2.getDuracion_en_minutos(),
                tipoDeVuelo2.getProvincia_origen(),
                tipoDeVuelo2.getLocalidad_origen(),
                tipoDeVuelo2.getDestino(),
                id);

        String selectSql = "SELECT * FROM tipo_de_vuelo WHERE id = ?";
        List<TipoDeVuelo> tipoDeVuelos = jdbcTemplate.query(selectSql, new Object[]{id}, (resultSet, rowNum) ->
                new TipoDeVuelo(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getInt("duracion_en_minutos"),
                        resultSet.getString("provincia_origen"),
                        resultSet.getString("localidad_origen"),
                        resultSet.getString("destino")
                )
        );

        return tipoDeVuelos.isEmpty() ? null : tipoDeVuelos.get(0);
    }

    @Override
    public void eliminar(int id) {
        String eliminarSql = "DELETE FROM tipo_de_vuelo WHERE id=?";
        jdbcTemplate.update(eliminarSql,id);
    }
}
