# JWT

JWT (JSON Web Token) es un estándar abierto (RFC 7519) que define una forma compacta y autónoma de transmitir información de forma segura entre dos partes como un objeto JSON. Esta información puede ser verificada y confiable porque está firmada digitalmente.

## Estructura de un JWT

Un JWT consta de tres partes separadas por puntos, cada parte representa información acerca del usuario codificada en base 64.

```
xxxxx.yyyyy.zzzzz
// partes: header.payload.signature
```

- **Header (Encabezado)**: Contiene el tipo de token y el algoritmo de encriptación
    
    ```json
    // ejemplo decodificado
    {
      "alg": "HS256", // algoritmo de encriptacion
      "typ": "JWT" // tipo de token
    }
    ```
    
- **Payload (Carga útil)**: Contiene los claims (declaraciones) sobre el usuario y otros datos adicionales.
    
    ```json
    // ejemplo decodificado
    {
      "sub": "1234567890",    // Subject (identificador de usuario)
      "name": "John Doe",
      "iat": 1516239022,      // momento de emision
      "exp": 1516242622       // expiracion del token
    }
    ```
    
- **Signature (Firma)**: Verifica que el token no ha sido alterado. Se crea combinando:
    - Header codificado en Base64
    - Payload codificado en Base64
    - Una clave secreta
    - Algoritmo especificado en el header
    
    ```
    // ejemplo decodificado
    HMACSHA256(
      base64UrlEncode(header) + "." +
      base64UrlEncode(payload),
      secret-key
    )
    ```
    

## Funcionamiento

El flujo típico de autenticación es:

1. El usuario envía credenciales (usuario y contraseña) al servidor
2. El servidor valida las credenciales
3. Si son válidas, el servidor crea el payload con información del usuario
4. El servidor genera el header especificando el algoritmo
5. Se codifican header y payload en Base64URL
6. Se crea la firma usando el algoritmo especificado
7. Se concatenan las tres partes: `header.payload.signature`
8. El JWT resultante se envía al cliente

### Uso del token en peticiones posteriores

1. El cliente almacena el JWT (típicamente en localStorage, sessionStorage o cookies)
2. En cada petición, el cliente envía el token en el header:

```
Authorization: Bearer <token>
```

1. El servidor recibe la petición con el token

### Verificación del token

1. El servidor extrae el token del header
2. Separa las tres partes del token
3. Decodifica el header y payload de Base64URL
4. Verifica la firma:
    - Toma el header y payload recibidos
    - Genera una nueva firma usando la clave secreta/pública
    - Compara esta firma con la firma recibida en el header del token
5. Si las firmas coinciden, el token es válido y no ha sido alterado
6. Verifica los claims temporales (`exp`, `nbf`, `iat`)
7. Si todo es válido, extrae la información del payload
8. El servidor procesa la petición con los datos del usuario del payload