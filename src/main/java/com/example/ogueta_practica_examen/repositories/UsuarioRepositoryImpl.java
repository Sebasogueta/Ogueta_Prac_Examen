package com.example.ogueta_practica_examen.repositories;

import com.example.ogueta_practica_examen.entities.Rol;
import com.example.ogueta_practica_examen.entities.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {
    private final JdbcTemplate jdbcTemplate;

    public UsuarioRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Usuario> getUsuarios() {

        String sql = "SELECT id, nombre, apellidos, username, encrypted_password, rol FROM usuario";
        return jdbcTemplate.query(sql, (resultSet, rowNum) ->
                new Usuario(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("username"),
                        resultSet.getString("encrypted_password"),
                        Rol.valueOf(resultSet.getString("rol"))

                )
        );

    }

    @Override
    public Usuario getUsuario(int id) {

        String sql = "SELECT id, nombre, apellidos, username, encrypted_password, rol FROM usuario WHERE id = ?";
        List<Usuario> usuarios = jdbcTemplate.query(sql, new Object[]{id}, (resultSet, rowNum) ->
                new Usuario(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("username"),
                        resultSet.getString("encrypted_password"),
                        Rol.valueOf(resultSet.getString("rol"))
                )
        );

        return usuarios.isEmpty() ? null :  usuarios.get(0);
    }

    @Override
    public int getUsuario(String username) {

        String sql = "SELECT id FROM usuario WHERE username = ?";
        List<Integer> idList = jdbcTemplate.queryForList(sql, new Object[]{username}, Integer.class);

        return idList.get(0);
    }

    @Override
    public Usuario crear(Usuario usuario2) {

        String insertSql = "INSERT INTO usuario (nombre, apellidos, username, encrypted_password, rol) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(insertSql,
                usuario2.getNombre(),
                usuario2.getApellidos(),
                usuario2.getUsername(),
                usuario2.getEncrypted_password(),
                usuario2.getRol().getRol().toUpperCase() //metodo de Usuario y de Rol
        );

        String selectSql = "SELECT * FROM usuario ORDER BY id DESC LIMIT 1";
        List<Usuario> usuarios = jdbcTemplate.query(selectSql, (resultSet, rowNum) ->
                new Usuario(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("username"),
                        resultSet.getString("encrypted_password"),
                        Rol.valueOf(resultSet.getString("rol"))
                )
        );

        return usuarios.isEmpty() ? null : usuarios.get(0);


    }

    @Override
    public Usuario modificar(int id, String rol) {
        String updateSql = "UPDATE usuario SET rol = ? WHERE id = ?";
        jdbcTemplate.update(updateSql,
                rol,
                id);

        Usuario usuario = getUsuario(id);

        return usuario;
    }

    @Override
    public void eliminar(int id) {
        String eliminarSql = "DELETE FROM usuario WHERE id=?";
        jdbcTemplate.update(eliminarSql,id);
    }
}
