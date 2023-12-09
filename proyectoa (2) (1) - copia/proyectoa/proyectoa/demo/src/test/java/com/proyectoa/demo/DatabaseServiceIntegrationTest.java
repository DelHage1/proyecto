package com.proyectoa.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE) // Disable the default behavior of replacing the database with an embedded one
@ActiveProfiles("test")
class DatabaseServiceIntegrationTest<Usuario> {

    @Autowired
    private DatabaseService databaseService;

    @Test
    void testGetAllUsuariosFromDatabase() {
        // Perform the actual database call
        List<com.proyectoa.Usuario> actualUsuarios = databaseService.getAllUsuarios();

        // You can now assert the result based on the actual database state
        // Add assertions based on the actual state of your test database
        assertTrue(actualUsuarios.size() >= 2, "Expected at least 2 records in the database, but found: " + actualUsuarios.size());
    }

    @Test
    void testAuthenticateUsuarioFromDatabase() {
        // Perform the actual database call for user authentication
        String username = "user1";
        String password = "password1";
        com.proyectoa.Usuario authenticatedUsuario = databaseService.authenticateUsuario(username, password);

        // Assert that the user is not null, indicating successful authentication
        assertNotNull(authenticatedUsuario, "Authentication successful for user: " + username);
    }

    @Test
    void testFailedAuthenticateUsuarioFromDatabase() {
        // Perform the actual database call for user authentication
        String username = "user112";
        String password = "password1";
        com.proyectoa.Usuario authenticatedUsuario = databaseService.authenticateUsuario(username, password);

        // Assert that the user is null, indicating failed authentication
        assertNull(authenticatedUsuario, "Authentication failed for user: " + username);
    }

    private void assertNull(com.proyectoa.Usuario authenticatedUsuario, String string) {
    }

}
