# Organización de proyectos

## Por capas

La estructura más común y recomendada:

```
src/main/java/com/ejemplo/

├── controller/                // controladores
│   ├── UserController.java
│   └── ProductController.java
|
├── service/                  // servicios 
│   ├── UserService.java
│   └── ProductService.java
|
├── repository/               // repositorios
│   ├── UserRepository.java
│   └── ProductRepository.java
|
├── model/                    // modelado de datos
│   ├── entity/   
│   │   ├── User.java
│   │   └── Product.java
|		|
│   └── dto/       
│       ├── UserRequest.java
│       └── UserResponse.java
|
└── config/
    ├── SecurityConfig.java
    └── SwaggerConfig.java
```

## Por módulos

Agrupa todo relacionado con una funcionalidad:

```
src/main/java/com/ejemplo/

├── user/                 // todo lo relacionado con el usuario
│   ├── UserController.java
│   ├── UserService.java
│   ├── UserRepository.java
│   ├── User.java
│   └── UserDTO.java
|
├── product/              // todo lo relacionado con el producto
│   ├── ProductController.java
│   ├── ProductService.java
│   ├── ProductRepository.java
│   ├── Product.java
│   └── ProductDTO.java
|
└── config/
    ├── SecurityConfig.java
    └── DatabaseConfig.java
```

## Arquitectura hexagonal

Para aplicaciones mas complejas:

```
src/main/java/com/ejemplo/

├── application/
│   ├── service/        // Lógica de negocio
│   ├── port/           // Interfaces (puertos)
│   └── dto/            // Objetos de transferencia
|
├── domain/
│   ├── model/          // Entidades de dominio
│   ├── service/        // Servicios de dominio
│   └── repository/     // Interfaces de repositorio
|
├── infrastructure/
│   ├── persistence/    // Implementaciones JPA
│   ├── web/            // Controladores REST
│   └── config/         // Configuraciones
|
└── Application.java
```