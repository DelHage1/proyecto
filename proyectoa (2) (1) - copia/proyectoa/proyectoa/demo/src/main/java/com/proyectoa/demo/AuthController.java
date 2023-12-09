package com.proyectoa.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

// Importa la clase ModelAndView
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        try {
            // Autenticar al usuario
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            // Establecer la autenticación en el contexto de seguridad
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generar el token
            String token = JwtUtil.generateToken(authRequest.getUsername());

            // Devolver el token
            return "Token: " + token;
        } catch (AuthenticationException e) {
            // Manejar la autenticación fallida
            return "Error de autenticación: " + e.getMessage();
        }
    }

    // Método para mostrar el formulario de inicio de sesión
    @GetMapping("/login")
    public ModelAndView showLoginForm() {
        return new ModelAndView("login");
    }

    // Clase de solicitud para representar el cuerpo JSON de la solicitud de autenticación
    static class AuthRequest {
        private String username;
        private String password;

        // Getters y setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
