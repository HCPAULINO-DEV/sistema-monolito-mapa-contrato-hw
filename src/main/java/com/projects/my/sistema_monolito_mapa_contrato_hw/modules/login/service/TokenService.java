package com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.service;

import com.projects.my.sistema_monolito_mapa_contrato_hw.modules.login.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("People Management API")
                    .withSubject(user.getUsername())
                    .withExpiresAt(getExpiration())
                    .withClaim("id", user.getId().toString())
                    .withClaim("role", user.getRole().name())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error generating token", exception);
        }
    }

    private Instant getExpiration() {
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }

    public String extractSubject(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("People Management API")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid or expired token");
        }
    }

    // ✅ NOVO MÉTODO
    public boolean isTokenValid(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            JWT.require(algorithm)
                    .withIssuer("People Management API")
                    .build()
                    .verify(tokenJWT);

            return true;

        } catch (JWTVerificationException exception) {
            return false;
        }
    }
}