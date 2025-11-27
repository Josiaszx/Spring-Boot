# Clases DTO

Una clase DTO (Data Transfer Object), es una clase diseñada para transferir datos entre diferentes capas de una aplicación sin contener lógica de negocios. Estas clases no contienen métodos aparte de los getters y setters. Sirven principalmente para ocultar información de entidades de una BD para mostrar solo los que nos interesa mostrar.

⚠️ Para que spring pueda acceder a las propiedades de este DTO es necesario definir sus respectivos getters.

**Ejemplo:**

Si tenemos una entidad usuario de una base de datos, cuyos atributos son:

- ID
- Nombre
- Apellido
- Correo

Y necesitamos pasar a la vista solo el nombre y el correo, creamos un DTO cuyos atributos serán los datos que necesitamos pasar, luego instanciamos un objeto de la clase DTO cargándole los valores de la entidad para finalmente retornar dicho objeto.