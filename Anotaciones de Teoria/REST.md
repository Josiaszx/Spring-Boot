# REST

REST (Representational State Transfer) es un conjunto de principios arquitectónicos que define cómo deben comunicarse los sistemas distribuidos a través de la web. No es un protocolo ni un estándar, sino más bien una filosofía de diseño que aprovecha las características existentes del protocolo HTTP.

## Principios fundamentales

**Arquitectura cliente-servidor**: Separa las responsabilidades entre el cliente (interfaz de usuario) y el servidor (almacenamiento de datos), permitiendo que evolucionen independientemente.

**Sin estado (Stateless)**: Cada petición del cliente al servidor debe contener toda la información necesaria para entenderla. El servidor no guarda ningún contexto de sesión entre peticiones, lo que mejora la escalabilidad.

**Cacheable**: Las respuestas deben indicar explícitamente si pueden ser almacenadas en caché o no, mejorando el rendimiento y reduciendo la carga del servidor.

**Sistema de capas**: La arquitectura puede estar compuesta por capas jerárquicas (balanceadores de carga, proxies, gateways) sin que el cliente necesite saber con qué está interactuando directamente.

**Interfaz uniforme**: Este es el principio más distintivo. Incluye la identificación de recursos mediante URLs, la manipulación de recursos a través de representaciones, mensajes auto-descriptivos y HATEOAS (hypermedia as the engine of application state).

## Funcionamiento

REST utiliza los métodos HTTP estándar para realizar operaciones sobre recursos:

**GET**: Obtiene información de un recurso. Por ejemplo, `GET /usuarios/123` recupera los datos del usuario con ID 123.

**POST**: Crea un nuevo recurso. Por ejemplo, `POST /usuarios` con datos en el cuerpo de la petición crea un nuevo usuario.

**PUT**: Actualiza completamente un recurso existente. Por ejemplo, `PUT /usuarios/123` reemplaza todos los datos del usuario 123.

**PATCH**: Actualiza parcialmente un recurso. Por ejemplo, `PATCH /usuarios/123` modifica solo ciertos campos del usuario.

**DELETE**: Elimina un recurso. Por ejemplo, `DELETE /usuarios/123` elimina el usuario con ID 123.

## Recursos y URLs

En REST, todo es un recurso: usuarios, productos, pedidos, etc. Cada recurso se identifica mediante una URL única. Las URLs deben ser intuitivas y seguir una estructura jerárquica, como `/tiendas/5/productos/42` para el producto 42 de la tienda 5.

## Representaciones

Un mismo recurso puede tener múltiples representaciones (JSON, XML, HTML). El cliente especifica el formato deseado mediante el header `Accept`, y el servidor indica el formato enviado con `Content-Type`.

## Buenas Practicas de diseño

- Los recursos en el endpoint de una API siempre deben estar en plural y si necesitamos una instancia de dicho recurso, lo pasamos mediante la URL.

  Ejemplo:

    - `GET /cuentas` → Obtener todas las cuentas
    - `GET /cuentas/1` → Obtener la cuenta con id=1
    - `DELETE /cuentas/2` → Eliminar la cuenta con id=2
- En caso de recursos anidados (recursos dentro de otros recursos):
    - `GET /cuentas/5/pagos/32` → Obtener el pago numero 32 de la cuenta con id=5

## Ejemplo de diseño de endpoints con algunos métodos HTTP

| Recurso | GET | POST | PUT | DELETE |
| --- | --- | --- | --- | --- |
| /cuentas | Obtener todas las cuentas | Crear una cuenta nueva | Actualizar todas las cuentas | Eliminar todas las cuentas |
| /cuentas/1 | Obtenr la cuenta con id=1 | ERROR | Actualizar la cuenta con id=1 | Eliminar la cuenta con id=1 |
| /cuentas/1/pagos | Obtener todos los pagos de la cuenta con id=1 | Crear un nuevo pago para la cuenta con id=1 | Actualizar todos los pagos de la cuenta con id=1 | Eliminar todos los pagos de la cuenta con id=1 |