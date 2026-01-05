# Autorizacion con JWT

Para manejar autorizaciones de acceso a endpoints mediante jwt, teniendo en cuenta la implementación de autenticación con jwt anterior, podemos hacer lo siguiente:

## 1. Retornar roles en el UserDetails

En nuestra implementación de user details implementamos el método `getAuthorities()`:

```java
public class UserDetailsImpl implements UserDetails {

    // entidad que representa al usuario
    private UserEntity user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // en este caso devolvemos un unico rol porque definimos a cada usuario con un solo rol
        GrantedAuthority grantedAuthority = () -> user.getRolDeUsuario().getRole();
        return List.of(grantedAuthority);
    }

    // implementar demas metodos ...
}

```

## 2. Modificar JwtUtils

En la clase `JWTUtils` Implementamos lo siguiente:

1. En el método `generateToken()`:
    
    ```java
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
    ```
    
2. Agregamos un nuevo metodo `getRolesFromToken()`:
    
    ```java
    public List<String> getRolesFromToken(String token) {
        var roles = Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("roles", List.class);
    
        return roles;
    }
    ```
    

## Configurar acceso en el security filter chain

En la clase de configuración de seguridad, dentro del método `securityFilterChain()`:

```java
.authorizeHttpRequests(auth -> {
    auth.requestMatchers("/api/auth/signin").permitAll(); // la ruta /api/auth/signin es publica
    auth.requestMatchers(HttpMethod.GET, "/api/authorization/admin").hasRole("ADMIN"); // la ruta /api/authorization/ es solo accesible para usuarios con el rol ROLE_ADMIN
    auth.requestMatchers(HttpMethod.GET, "/api/authorization/user").hasAnyRole("ADMIN", "USER"); // la ruta /api/users es solo accesible para usuarios con el rol ROLE_ADMIN o ROLE_USER
    auth.anyRequest().authenticated();
})
```