package com.implemetacion.jwt.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JWTUtils {

    @Value("${spring.app.jwtSecret}")
    private String secretKey;

    @Value("${spring.app.jwtExpiraionMs}")
    private long expirationMs;

    // extraer el jwt del header de la solicitud http
    public String extractJwt(HttpServletRequest request) {
        var bearerToken = request.getHeader("Authorization"); // token en formato Bearer <token>

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // extraer el token "limpio" sin el prefijo "Bearer "
        }

        return null;
    }

    // generar jwt a partir de un usuario
    // en este caso se generara el token a partir del email
    public String genereteToken(UserDetails usuario) {
        String email = usuario.getUsername();
        return Jwts.builder()
                .subject(email) // sub: email del usuario
                .issuedAt(new Date()) // iat: fecha de creacion del token
                .expiration(new Date(new Date().getTime() + expirationMs)) // exp: fecha de expiracion del token
                .signWith(key()) // firma del token
                .compact();
    }

    // obtener email desde un token
    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // crear key para validar/firmar tokens
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    // validar token
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parseSignedClaims(authToken);
            return true;
        } catch (
                MalformedJwtException |
                ExpiredJwtException |
                UnsupportedJwtException |
                IllegalArgumentException e
        ) {
            return false;
        }
    }
}
