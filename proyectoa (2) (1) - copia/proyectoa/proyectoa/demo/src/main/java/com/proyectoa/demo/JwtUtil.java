package com.proyectoa.demo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

    private static final String SECRET_KEY = "Adri3003";

    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static String generateToken(Object username) {
        return null;
    }
}
