# PetroServ - Taller Práctico 1 (Unidad 2)

Proyecto académico para practicar **conexión a MySQL** y operaciones **CRUD** con Spring Boot.

## Requisitos
- Java 17
- Maven
- MySQL 8.x (Workbench opcional)

## Base de datos
1. Crear la base:
   ```sql
   CREATE DATABASE petroserv_db;
   ```
2. Verifica tus credenciales en `src/main/resources/application.properties`.

> Nota: el proyecto usa `spring.jpa.hibernate.ddl-auto=update`, por lo que las tablas se crean/actualizan al iniciar.

## Ejecutar el proyecto
```bash
mvn spring-boot:run
```

## Vistas (MVC - Thymeleaf)
- Inicio: `http://localhost:8080/`
- Lista de personas: `http://localhost:8080/personas`
- Registro de persona: `http://localhost:8080/personas/nuevo`

Incluye validación básica (regex) en el formulario de Persona:
- Cédula: 10 dígitos
- Celular: `09xxxxxxxx`

## API (para Postman)
- `GET http://localhost:8080/api/personas`
- `GET http://localhost:8080/api/personas/{id}`
- `POST http://localhost:8080/api/personas`
- `PUT http://localhost:8080/api/personas/{id}`
- `DELETE http://localhost:8080/api/personas/{id}`

> La API se mantiene para probar el CRUD con Postman, tal como se revisó en clases.