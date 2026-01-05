package com.implemetacion.jwt.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/*
    Esta clase representa un servicio que contiene varios metodos utiles para trabajar con tokens JWT.
*/

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

        // agregar claims al token
        var claims = new HashMap<String, Object>();

        // agregar roles al token para autorizacion
        usuario.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList()
                .forEach(role -> claims.put("roles", role));

        return Jwts.builder()
                .subject(email) // sub: email del usuario
                .claims(claims) // agregar claims al token
                .issuedAt(new Date()) // iat: fecha de creacion del token
                .expiration(new Date(new Date().getTime() + expirationMs)) // exp: fecha de expiracion del token
                .signWith(key()) // firma del token
                .compact();
    }

    // extraer email desde un token
    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // extraer claims de un token
    public List<String> getRolesFromToken(String token) {
        var roles = Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("roles", List.class);

        return roles;
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
