# WebSockets

Un WebSocket es un protocolo de comunicación que permite una conexión persistente y bidireccional entre cliente y servidor a través de una sola conexión TCP, a diferencia de HTTP donde se hace una solicitud donde el servidor devuelve una respuesta y la conexión se termina.

Implementacion básica de WebSockets con Spring Boot: [springboot-websockets](https://github.com/Josiaszx/springboot-websockets)

**Con HTTP:**

1. El cliente envía una petición.
2. El servidor responde.
3. La conexión se cierra.

Si el cliente necesita datos nuevos, tiene que volver a pedirlos.

**Con WebSocket:**

- Se abre una sola conexión.
- Cliente y servidor pueden enviarse datos en cualquier momento.
- La conexión queda abierta mientras dure la sesión.

El servidor y el cliente se pueden comunicar todo lo que necesiten mientras la conexión dure.

## Usos

Se usa cuando necesitamos datos en tiempo real o comunicación continua:

- Chats en tiempo real
- Videojuegos online
- Dashboards con datos en vivo (precios, métricas)
- Aplicaciones de colaboración o streaming
- Notificaciones instantáneas

## Ejemplo

Si queremos desarrollar una app de mensajería, implementaríamos WebSockets de la siguiente manera:

1. El navegador abre un WebSocket con el servidor.
2. Cuando un usuario envía un mensaje:
    - el cliente lo manda al servidor
3. El servidor lo envía inmediatamente a los demás clientes conectados.

Sin WebSocket, el cliente tendría que preguntar cada segundo si hay mensajes nuevos (lo que se llama polling).

## Detalles

- Empieza como una petición HTTP con un handshake (solicitud inicial del cliente para iniciar una conexión de WebSocket).
- Luego la conexión se “actualiza” a WebSocket.
- Usa normalmente:
    - `ws://` (sin cifrado)
    - `wss://` (cifrado, equivalente a HTTPS)

**Ejemplo de URL:**

```
wss://example.com/chat
```

## Handshake

El handshake es el proceso inicial donde el cliente y el servidor se ponen de acuerdo para empezar una comunicación usando WebSocket.

**Proceso de inicio de conexión:**

### 1 - El cliente envía una petición HTTP

Se realiza una petition inicial mediante HTTP para solicitar la apertura de una nueva conexión.

**Ejemplo:**

```
GET /chat HTTP/1.1
Host: ejemplo.com
Upgrade: websocket
Connection: Upgrade
Sec-WebSocket-Key: x3JJHMbDL1EzLkh9GBhXDw==
Sec-WebSocket-Version: 13
```

- `Host: ejemplo.com`: Dominio o servidor al que se conecta.
- `Upgrade: websocket`: El cliente solicita abrir una conexión con WebSocket.
- `Connection: Upgrade`: Indica que el header `Upgrade` debe aplicarse a esta conexión.
- `Sec-WebSocket-Key: x3JJH…`: Clave aleatoria generada por el cliente para que luego el servidor pueda indicar que entiende el protocolo.
- `Sec-WebSocket-Version: 13`: Version del protocolo.

---

### **2 - El servidor acepta el cambio**

Si el servidor soporta WebSocket responde algo así:

```
HTTP/1.1 101 Switching Protocols
Upgrade: websocket
Connection: Upgrade
Sec-WebSocket-Accept: HSmrc0sMlYUkAGmm5OPpG2HaGWk=
```

- `HTTP/1.1 101 Switching Protocols`: El servidor acepta cambiar a WebSocket
- `Upgrade: websocket`: Confirma que el protocolo nuevo será WebSocket.
- `Sec-WebSocket-Accept: HSmr…`: Respuesta criptográfica al `Sec-WebSocket-Key` del cliente. Verifica que el servidor entendió correctamente el protocolo WebSocket.

### **3 - La conexión queda abierta**

Después del handshake:

- ya no se usa HTTP
- se empieza a usar WebSocket directamente
- cliente y servidor pueden enviar datos en cualquier momento
