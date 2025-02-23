# 📦 API REST de Productos

Esta API REST permite gestionar productos en una base de datos. Incluye operaciones para listar, buscar, crear, actualizar y eliminar productos. 

## Arquitectura
La Arquitectura de software utilizada es la arquitectura de N-Capas, en el cual cada modulo realizara una tarea especifica.

- Entidad

- Repository

- Service

- ServiceImpl

- Controller

## 📌 Tecnologías utilizadas
- Java 17+
- Spring Boot
- Lombok
- JPA/Hibernate
- Base de datos (ej. MySQL, PostgreSQL, H2)
- Maven

## 🚀 Instalación y ejecución



### 2️⃣ Configurar la base de datos  
Editar el archivo `application.properties` para configurar la conexión a la base de datos.

### 3️⃣ Construir y ejecutar la aplicación
```
mvn spring-boot:run
```

La API estará disponible en `http://localhost:8080/api/v1/products`.

## 📡 Endpoints

### 🔍 Obtener productos
- **Todos los productos (activos e inactivos)**
  ```
  GET /api/v1/products
  ```
- **Solo productos activos**
  ```
  GET /api/v1/products/active
  ```

### 🔎 Buscar productos
- **Por ID (Path Variable)**
  ```
  GET /api/v1/products/{id}
  ```
- **Por ID (Request Param)**
  ```
  GET /api/v1/products/getProductByIdRP?id=1
  ```
- **Por nombre exacto**
  ```
  GET /api/v1/products/search/exact?name=ProductoEjemplo
  ```
- **Por coincidencia parcial en el nombre**
  ```
  GET /api/v1/products/search/partial?characters=prod
  ```

### ✨ Crear producto
```
POST /api/v1/products
Content-Type: application/json
{
  "name": "Nuevo Producto",
  "description": "Descripción del producto",
  "stock": 100,
  "precio": 19.99,
  "image": "url-de-la-imagen"
}
```

### 🔄 Actualizar producto
- **Actualizar completamente**
  ```
  PUT /api/v1/products/{id}
  ```
- **Actualizar parcialmente**
  ```
  PATCH /api/v1/products/{id}
  ```

### ❌ Eliminar producto
- **Eliminación física (Hard Delete)**
  ```
  DELETE /api/v1/products/{id}
  ```
- **Eliminación lógica (Soft Delete)**
  ```
  DELETE /api/v1/products/{id}/deactivate
  ```
