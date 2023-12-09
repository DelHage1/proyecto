package com.proyectoa.demo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.proyectoa.Usuario;

@Service
public class DatabaseService {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(DatabaseService.class);

    @Autowired
    public DatabaseService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Usuario> getAllUsuarios() {
        try {
            String query = "SELECT * FROM proyectonuevo.usuario ORDER BY idusuario";
            logger.info("Consulta SQL: " + query);

            List<Map<String, Object>> resultUsuarios = jdbcTemplate.queryForList(query);

            List<Usuario> usuarios = new ArrayList<>();

            for (Map<String, Object> row : resultUsuarios) {
                int id_usuario = (int) row.get("idusuario");
                String nombre = (String) row.get("nombre");
                String apellido = (String) row.get("apellido");
                String email = (String) row.get("email");
                String descripcion = (String) row.get("descripcion");
                String prioridad = (String) row.get("prioridad");

                // Use LocalDate for fechadevencimiento instead of String
                Date sqlDate = (Date) row.get("fechadevencimiento");
                LocalDate fechadevencimiento = sqlDate.toLocalDate();

                Usuario usuario = new Usuario(id_usuario, nombre, apellido, email, descripcion, prioridad,
                        fechadevencimiento);

                usuarios.add(usuario);
            }

            return usuarios;
        } catch (Exception e) {
            logger.error("Error al obtener usuarios", e);
            return null;
        }
    }
    public Usuario getUsuario(int id) {
        try {
            String query = "SELECT * FROM usuario WHERE id_usuario = ? ORDER BY idusuario";
            logger.info("Consulta SQL: " + query + " con parámetro id = " + id);

            return jdbcTemplate.queryForObject(query, (rs, rowNum) -> {
                int id_usuario = rs.getInt("idusuario");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String descripcion = rs.getString("descripcion");
                String prioridad = rs.getString("prioridad");
                Date fechadevencimiento = rs.getDate("fechadevencimiento");

                return new Usuario(id_usuario, nombre, apellido, email, descripcion, prioridad,
                        fechadevencimiento.toLocalDate());
            }, id);
        } catch (Exception e) {
            logger.error("Error al obtener usuario con id " + id, e);
            return null;
        }
    }

    public void updateUsuario(Usuario usuario) {
        try {
            String query = "UPDATE usuario SET nombre = ?, descripcion = ? WHERE idusuario = ?";
            jdbcTemplate.update(query, usuario.getNombre(), usuario.getDescripcion(), usuario.getIdusuario());
        } catch (Exception e) {
            logger.error("Error al actualizar usuario con id " + usuario.getIdusuario(), e);
        }
    }

    public void insertUsuario(Usuario usuario) {
        try {
            String query = "INSERT INTO usuario (nombre, descripcion) VALUES (?, ?)";
            jdbcTemplate.update(query, usuario.getNombre(), usuario.getDescripcion());
        } catch (Exception e) {
            logger.error("Error al insertar usuario", e);
        }
    }

    public int deleteUsuario(int id) {
        try {
            String query = "DELETE FROM usuario WHERE idusuario = ?";
            jdbcTemplate.update(query, id);
            return 1;
        } catch (Exception e) {
            logger.error("Error al eliminar usuario con id " + id, e);
            return 0;
        }
    }

    public Usuario authenticateUsuario(String username, String password) {
        // Implement authentication logic here if needed
        return null;
    }
}
