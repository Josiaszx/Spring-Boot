# Protocolo HTTP

**HTTP** (Hypertext Transfer Protocol o Protocolo de Transferencia de Hipertexto) es el protocolo fundamental de comunicación en la web, permitiendo la transferencia de información entre clientes y servidores.

## ¿Cómo funciona?

HTTP sigue un modelo **cliente-servidor** basado en solicitudes y respuestas:

1. El **cliente** envía una solicitud HTTP al servidor (request).
2. El **servidor** procesa la solicitud y devuelve una respuesta HTTP (response).
3. La conexión generalmente se cierra (aunque HTTP/1.1 introdujo conexiones persistentes)

HTTP es un protocolo sin estado, lo que significa que cada solicitud es independiente y el servidor no mantiene información sobre solicitudes anteriores (aunque esto se puede gestionar con cookies o sesiones).

## Métodos HTTP

Los métodos (o verbos) HTTP definen la acción que se desea realizar:

**GET** - Solicita datos de un recurso específico. Es el método más común y solo debe recuperar datos sin modificar nada en el servidor.

**POST** - Envía datos al servidor para crear un nuevo recurso, como enviar un formulario o subir un archivo.

**PUT** - Actualiza o reemplaza completamente un recurso existente con los datos enviados.

**DELETE** - Elimina un recurso específico del servidor.

**PATCH** - Realiza modificaciones parciales a un recurso existente.

**HEAD** - Similar a GET, pero solo solicita los encabezados de la respuesta, sin el cuerpo del mensaje.

**OPTIONS** - Solicita información sobre los métodos de comunicación disponibles para un recurso.

**CONNECT** - Establece un túnel hacia el servidor, usado principalmente para conexiones HTTPS a través de proxies.

**TRACE** - Realiza una prueba de bucle de retorno del mensaje hacia el recurso de destino.

## Códigos de Estado HTTP

Los códigos de estado indican el resultado de la solicitud HTTP. Se dividen en cinco categorías:

**1xx - Respuestas informativas**

- 100 Continue: El servidor ha recibido los encabezados y el cliente puede proceder a enviar el cuerpo
- 101 Switching Protocols: El servidor acepta cambiar el protocolo

**2xx - Respuestas exitosas**

- 200 OK: La solicitud fue exitosa
- 201 Created: Se creó un nuevo recurso exitosamente
- 204 No Content: La solicitud fue exitosa pero no hay contenido para devolver

**3xx - Redirecciones**

- 301 Moved Permanently: El recurso se ha movido permanentemente a una nueva URL
- 302 Found: Redirección temporal
- 304 Not Modified: El recurso no ha sido modificado desde la última solicitud

**4xx - Errores del cliente**

- 400 Bad Request: La solicitud tiene sintaxis incorrecta
- 401 Unauthorized: Se requiere autenticación
- 403 Forbidden: El servidor entiende la solicitud pero se niega a autorizarla
- 404 Not Found: El recurso solicitado no existe
- 429 Too Many Requests: El cliente ha enviado demasiadas solicitudes

**5xx - Errores del servidor**

- 500 Internal Server Error: Error genérico del servidor
- 502 Bad Gateway: El servidor actuando como gateway recibió una respuesta inválida
- 503 Service Unavailable: El servidor no está disponible temporalmente

## Estructura de una solicitud HTTP

Una solicitud típica contiene:

- **Línea de solicitud**: método, URL y versión HTTP
- **Encabezados**: información adicional (tipo de contenido, cookies, autenticación, etc.)
- **Cuerpo** (opcional): datos enviados con POST o PUT

## Estructura de una respuesta HTTP

Una respuesta incluye:

- **Línea de estado**: versión HTTP, código de estado y mensaje
- **Encabezados**: información sobre el servidor y el recurso
- **Cuerpo**: el contenido solicitado (HTML, JSON, imágenes, etc.)

## HTTPS

**HTTPS** (HTTP Secure) es HTTP sobre una capa de cifrado SSL/TLS, que proporciona autenticación del servidor, confidencialidad e integridad de los datos intercambiados.