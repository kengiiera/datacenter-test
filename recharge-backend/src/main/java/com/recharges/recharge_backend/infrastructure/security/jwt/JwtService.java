package com.recharges.recharge_backend.infrastructure.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

/**
 * Servicio para generar y validar tokens JWT.
 */
@Service
public class JwtService {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expirationMs = 3600000; // 1 hora

    /**
     * Genera un token JWT para el username dado.
     *
     * @param username El nombre de usuario.
     * @return El token JWT.
     */
    public String generateToken(String username) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(secretKey)
                .compact();
    }

    /**
     * Obtiene el username del token JWT.
     *
     * @param token El token JWT.
     * @return El nombre de usuario.
     */
    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Valida el token JWT.
     *
     * @param token El token JWT.
     * @return true si es válido, false en caso contrario.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
