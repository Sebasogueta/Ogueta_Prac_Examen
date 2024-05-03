package com.example.ogueta_practica_examen.repositories;

import com.example.ogueta_practica_examen.entities.Sesion;
import com.example.ogueta_practica_examen.entities.SesionDetalle;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Repository
public class SesionRepositoryImpl implements SesionRepository {
    private final JdbcTemplate jdbcTemplate;

    public SesionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Map<String, Object>> getSesiones() {

        String sql = "SELECT sesion.id, sesion.nombre, sesion.fecha, sesion.hora, tipo_de_vuelo.nombre AS tipo_vuelo, entrenador.username AS entrenador, COALESCE(usuario2.username, 'Nadie apuntado') AS usuario " +
                "FROM sesion " +
                "INNER JOIN sesion_detalle detalle ON sesion.id = detalle.id_sesion " +
                "INNER JOIN tipo_de_vuelo ON detalle.id_tipo_de_vuelo = tipo_de_vuelo.id " +
                "INNER JOIN usuario entrenador ON detalle.id_entrenador = entrenador.id " +
                "LEFT JOIN usuario usuario2 ON detalle.id_usuario = usuario2.id";

        List<Map<String, Object>> lista = jdbcTemplate.queryForList(sql);

        return lista.isEmpty() ? null : lista;

    }


    @Override
    public Map<String, Object> getSesion(int id) {

        String sql = "SELECT sesion.id, sesion.nombre, sesion.fecha, sesion.hora, tipo_de_vuelo.nombre AS tipo_vuelo, entrenador.username AS entrenador, COALESCE(usuario2.username, 'Nadie apuntado') AS usuario " +
                "FROM sesion " +
                "INNER JOIN sesion_detalle detalle ON sesion.id = detalle.id_sesion " +
                "INNER JOIN tipo_de_vuelo ON detalle.id_tipo_de_vuelo = tipo_de_vuelo.id " +
                "INNER JOIN usuario entrenador ON detalle.id_entrenador = entrenador.id " +
                "LEFT JOIN usuario usuario2 ON detalle.id_usuario = usuario2.id " +
                "WHERE sesion.id = ?";
        List<Map<String, Object>> lista = jdbcTemplate.queryForList(sql,id);

        return lista.isEmpty() ? null : lista.get(0);

    }

    @Override
    public List<Map<String, Object>> getSesionesCreadas(int id_entrenador) {
        String sql = "SELECT sesion.id, sesion.nombre, sesion.fecha, sesion.hora, tipo_de_vuelo.nombre AS tipo_vuelo, entrenador.username AS entrenador, COALESCE(usuario2.username, 'Nadie apuntado') AS usuario " +
                "FROM sesion " +
                "INNER JOIN sesion_detalle detalle ON sesion.id = detalle.id_sesion " +
                "INNER JOIN tipo_de_vuelo ON detalle.id_tipo_de_vuelo = tipo_de_vuelo.id " +
                "INNER JOIN usuario entrenador ON detalle.id_entrenador = entrenador.id " +
                "LEFT JOIN usuario usuario2 ON detalle.id_usuario = usuario2.id " +
                "WHERE detalle.id_entrenador = ?";
        List<Map<String, Object>> lista = jdbcTemplate.queryForList(sql,id_entrenador);
        return lista.isEmpty() ? null : lista;
    }

    @Override
    public List<Map<String, Object>> getSesionesApuntadas(int id_usuario) {
        String sql = "SELECT sesion.id, sesion.nombre, sesion.fecha, sesion.hora, tipo_de_vuelo.nombre AS tipo_vuelo, entrenador.username AS entrenador, COALESCE(usuario2.username, 'Nadie apuntado') AS usuario " +
                "FROM sesion " +
                "INNER JOIN sesion_detalle detalle ON sesion.id = detalle.id_sesion " +
                "INNER JOIN tipo_de_vuelo ON detalle.id_tipo_de_vuelo = tipo_de_vuelo.id " +
                "INNER JOIN usuario AS entrenador ON detalle.id_entrenador = entrenador.id " +
                "LEFT JOIN usuario AS usuario2 ON detalle.id_usuario = usuario2.id " +
                "WHERE detalle.id_usuario = ?";
        List<Map<String, Object>> lista =  jdbcTemplate.queryForList(sql, id_usuario);

        return lista.isEmpty() ? null : lista;
    }



    @Override
    public Map<String, Object> crear(Map<String, Object> datosEntrada, int id_entrenador) {

        Sesion sesion = new Sesion();
        sesion.setNombre((String) datosEntrada.get("nombre"));
        sesion.setFecha(LocalDate.parse((String) datosEntrada.get("fecha")));
        sesion.setHora(LocalTime.parse((String) datosEntrada.get("hora")));

        Integer idTipoDeVuelo = (Integer) datosEntrada.get("id_tipo_de_vuelo");

        String insertSql1 = "INSERT INTO sesion (nombre, fecha, hora) VALUES (?,?,?)";
        String insertSql2 = "INSERT INTO sesion_detalle (id_sesion, id_tipo_de_vuelo, id_entrenador) VALUES (?,?,?)";
        String selectSql1 = "SELECT id FROM sesion ORDER BY id DESC LIMIT 1";


        jdbcTemplate.update(insertSql1,
                sesion.getNombre(),
                sesion.getFecha(),
                sesion.getHora()
        );

        int sesionId = jdbcTemplate.queryForObject(selectSql1, Integer.class);

        jdbcTemplate.update(insertSql2,
                sesionId,
                idTipoDeVuelo,
                id_entrenador
        );

        Map<String, Object> sesionCreada = getSesion(sesionId);

        return sesionCreada;
    }

    @Override
    public Map<String, Object> modificar(int id, Map<String, Object> datosEntrada) {
        String updateSql = "UPDATE sesion SET nombre = ?, fecha = ?, hora = ? WHERE id = ?";
        String updateSql2 = "UPDATE sesion_detalle SET id_tipo_de_vuelo = ? WHERE id_sesion = ?";

        jdbcTemplate.update(updateSql,
                datosEntrada.get("nombre"),
                datosEntrada.get("fecha"),
                datosEntrada.get("hora"),
                id);

        jdbcTemplate.update(updateSql2,
                datosEntrada.get("id_tipo_de_vuelo"),
                id);

        Map<String, Object> sesionModificada = getSesion(id);

        return sesionModificada;
    }

    @Override
    public Map<String, Object> apuntarse(int id_sesion, int id_usuario) {

        String updateSql = "UPDATE sesion_detalle SET id_usuario = ? WHERE id_sesion = ?";
        jdbcTemplate.update(updateSql,
                id_usuario,
                id_sesion
        );

        Map<String, Object> sesionApuntado = getSesion(id_sesion);

        return sesionApuntado;
    }

    @Override
    public Map<String, Object> desapuntarse(int id_sesion) {

        String updateSql = "UPDATE sesion_detalle SET id_usuario = null WHERE id_sesion = ?";
        jdbcTemplate.update(updateSql,
                id_sesion
        );

        Map<String, Object> sesionDesapuntado = getSesion(id_sesion);

        return sesionDesapuntado;
    }

    @Override
    public void eliminar(int id) {
        String eliminarSql = "DELETE FROM sesion WHERE id=?";
        String eliminarSql2 = "DELETE FROM sesion_detalle WHERE id_sesion=?";
        jdbcTemplate.update(eliminarSql,id);
        jdbcTemplate.update(eliminarSql2,id);
    }


}
