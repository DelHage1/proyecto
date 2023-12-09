package com.proyectoa.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.proyectoa.Usuario;

class DatabaseServiceTest {

    private DatabaseService databaseService;
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate = mock(JdbcTemplate.class);
        databaseService = new DatabaseService(jdbcTemplate);
    }


    @Test
    void testGetAllUsuarios() {
        // ... (mocking and setup code)
    
        // Calling the actual method to be tested
        List<Usuario> actualUsuarios = databaseService.getAllUsuarios();
    
        // Creating the expected result
        List<Usuario> expectedUsuarios = new ArrayList<>();
        expectedUsuarios.add(new Usuario(1, "User 1", "Lastname 1", "user1@example.com", "description 1", "High", "2023-12-31"));
        expectedUsuarios.add(new Usuario(2, "User 2", "Lastname 2", "user2@example.com", "description 2", "Medium", "2023-12-15"));
    
        // Asserting that the actual result matches the expected result
        assertEquals(expectedUsuarios, actualUsuarios);
    }
    

    @Test
    void testGetUsuario() {
        // Mocking the behavior of jdbcTemplate.queryForObject()
        when(jdbcTemplate.queryForObject(
                eq("SELECT * FROM usuario WHERE id_usuario = ?"),
                any(RowMapper.class), // Use any() for RowMapper
                eq(1)
        )).thenReturn(new Usuario(1, "user 1", "description 1"));

        // Calling the actual method to be tested
        Usuario actualUsuario = databaseService.getUsuario(1);

        // Creating the expected result
        Usuario expectedUsuario = new Usuario(1, "user 1", "description 1");

        // Asserting that the actual result matches the expected result
        assertEquals(expectedUsuario, actualUsuario);
    }

    @Test
    void testInsertUsuario() {
        // Mocking the behavior of jdbcTemplate.update()
        when(jdbcTemplate.update(
                "INSERT usuario SET Nombre = ?, Descripcion = ?",
                "User Test", "Description Test")).thenReturn(1);

        // Calling the actual method to be tested
        Usuario usuarioToInsert = new Usuario(0, "user test", "description test");
        databaseService.insertUsuario(usuarioToInsert);

        // Verify that jdbcTemplate.update() was called with the expected SQL and parameters
        verify(jdbcTemplate).update(
                "INSERT usuario SET Nombre = ?, Descripcion = ?",
                "User Test",
                "Description Test"
        );
    }

    // Additional tests for updateUsuario and deleteUsuario can be added similarly
}
