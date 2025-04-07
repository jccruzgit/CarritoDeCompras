
# 🛒 Carrito de Compras - Microservicios con Spring Boot

Este repositorio contiene una solución basada en microservicios desarrollada con Spring Boot que simula el flujo completo de un carrito de compras, incluyendo autenticación con JWT, gestión de productos, órdenes y pagos simulados.

---

## 📦 Estructura del Proyecto

```
shopping-cart/
├── auth-service/        # Servicio de autenticación y autorización con JWT
├── product-service/    # Proxy hacia fakestoreapi.com para consultar productos
├── order-service/      # Gestión de órdenes, integración con pago
├── payment-service/     # Simulación de procesamiento de pagos
```

---

## 🚀 Requisitos

- Java 17
- Maven
- Postman (opcional para pruebas)
- Docker (opcional para despliegue futuro)

---

## ▶️ Cómo ejecutar localmente

Cada servicio puede ejecutarse por separado. Asegúrate de que los puertos no se crucen.

1. Clona el proyecto:

```bash
git clone https://github.com/jccruzgit/CarritoDeCompras.git
cd CarritoDeCompras
```

2. Entra a cada microservicio y ejecútalo:

```bash
cd auth-service
mvn spring-boot:run
```

Haz lo mismo para `product-service`, `order-service` y `payment-service`.

---

## 🧪 Circuito de prueba

### 1. Registro de usuario (auth-service)

`POST http://localhost:8084/api/auth/register`

```json
{
  "username": "cliente1",
  "password": "clave123"
}
```

---

### 2. Login de usuario (auth-service)

`POST http://localhost:8084/api/auth/login`

Guarda el token JWT de la respuesta.

---

### 3. Usar el token en los otros servicios

En Swagger o Postman, usar:

```
Authorization: Bearer <token_jwt>
```

---

### 4. Consultar productos (product-service)

`GET http://localhost:8081/api/products`

---

### 5. Crear orden con pago (order-service)

`POST http://localhost:8082/api/orders/with-payment`

```json
{
  "productIds": [1, 2],
  "totalAmount": 150.0
}
```

---

### 6. Ver pagos (opcional, usando base de datos H2)

Accede a `http://localhost:8083/h2-console` con:

- JDBC URL: `jdbc:h2:mem:paymentdb`
- User: `sa`

---

## 🔐 Seguridad

Todos los microservicios están protegidos con JWT emitidos por `auth-service`. Solo usuarios autenticados pueden acceder a los endpoints.

---

## 🛠️ Créditos y herramientas usadas

- Spring Boot 3
- Spring Security 6
- Spring Data JPA
- Spring Web / WebClient
- Springdoc OpenAPI (Swagger)
- JJWT
- H2 Database

---
Author: Juan Carlos Rivera Cruz
Fecha: 06/04/2025
Proyecto de prueba técnica backend developer
