# 🏥 Patient Care Track – Backend

Backend REST desarrollado con **Spring Boot** para la gestión de pacientes, registros diarios de cuidados y **alertas automáticas** (incidencias y control de comidas), con autenticación **JWT** y roles **USER / ADMIN**.

El sistema está pensado para un entorno sociosanitario, donde los cuidadores registran la actividad diaria de los pacientes y los administradores (doctores / supervisores) reciben alertas y las gestionan.

---

## 🧩 Funcionalidades principales

- 🔐 Autenticación JWT (login)
- 👥 Roles:
    - **USER** → cuidadores
    - **ADMIN** → supervisores / doctores
- 🧓 Gestión de pacientes
- 📝 Registros diarios:
    - Comidas (desayuno, comida, cena)
    - Líquidos
    - Deposición
    - Aseo
    - Incidencias
- 🚨 Alertas automáticas:
    - **INCIDENCIA** (cuando el cuidador lo marca)
    - **NO_MEALS** (cuando no hay comidas registradas en un día)
- 🧠 Dedupe de alertas (no se repiten)
- 📊 Panel de alertas para ADMIN
- 📘 Swagger / OpenAPI

---

## 💻 Stack tecnológico

- **Java**: 21
- **Framework**: Spring Boot 3
- **Build**: Maven
- **Base de datos**: MySQL (Docker-ready)
- **ORM**: Spring Data JPA / Hibernate
- **Seguridad**: Spring Security + JWT
- **Documentación**: Swagger / OpenAPI
- **Validación**: Jakarta Validation
- **Lombok**

---

## 📁 Arquitectura del proyecto

Arquitectura limpia por capas y contextos:

```
patientcaretrackbackend
├── shared
│   ├── security
│   ├── exception
│   └── web
├── registry        (usuarios, roles, auth)
├── patients
│   ├── domain
│   │   ├── model
│   │   └── port
│   ├── application
│   │   ├── service
│   │   └── usecase
│   └── infrastructure
│       ├── web
│       └── persistence
```

- **Domain**: reglas de negocio
- **Application**: casos de uso
- **Infrastructure**: controllers, JPA, mappers
- **Shared**: seguridad, errores globales

---

## 🚀 Cómo arrancar el proyecto

1. Levanta la base de datos (MySQL en Docker o local).
2. Arranca el backend desde tu IDE o con Maven:

```bash
mvn spring-boot:run
```

3. Accede a Swagger:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🔐 Autenticación (JWT)

### Login
**POST** `/auth/login`

```json
{
  "username": "user1",
  "password": "password"
}
```

Respuesta:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

⚠️ **IMPORTANTE**  
Para todas las llamadas protegidas añade el header:

```
Authorization: Bearer <JWT_TOKEN>
```

---

## 🧑‍⚕️ Flujo USER (cuidador)

### 1️⃣ Ver pacientes asignados
**GET** `/me/patients`

### 2️⃣ Crear registros diarios

#### Ejemplo: líquidos + incidencia
**POST** `/me/patients/{patientId}/records`

```json
{
  "tipo": "LIQUIDOS",
  "liquidosMl": 200,
  "comentario": "Se atraganta",
  "notificarIncidencia": true
}
```

✔️ Crea el registro  
🚨 Genera automáticamente una **alerta INCIDENCIA**

---

### 📌 Nota importante sobre `"realizado"`

En los registros de comidas **NO se usan campos tipo `hizoCena` en el JSON**.

👉 El DTO correcto es:

```json
{
  "tipo": "COMIDA",
  "realizado": true
}
```

- `realizado = true` → comida hecha
- `realizado = false` → comida NO hecha

Internamente:
- Se mapea a `hizoDesayuno`, `hizoComida` o `hizoCena` según el `tipo`.

✅ **Esto es correcto y está bien diseñado**.

---

## 🚨 Alertas automáticas

### 🔴 INCIDENCIA
Se crea cuando:
```json
"notificarIncidencia": true
```

### 🍽️ NO_MEALS
Se crea cuando:
- En un día concreto **no hay desayuno, comida ni cena** para un paciente.

Se evalúa desde un endpoint ADMIN.

---

## 🛠️ Flujo ADMIN (supervisor)

### 1️⃣ Ver alertas abiertas
**GET** `/admin/alerts`

Resultado:
```json
[
  {
    "id": 5,
    "type": "INCIDENCIA",
    "status": "OPEN",
    "pacienteId": 1,
    "message": "Incidencia registrada: Se atraganta",
    "createdAt": "2025-12-31T08:52:05Z"
  }
]
```

---

### 2️⃣ Resolver alerta
**PATCH** `/admin/alerts/{alertId}/resolve`

✔️ Marca la alerta como `RESOLVED`

---

### 3️⃣ Evaluar NO_MEALS
**POST** `/admin/alerts/evaluate/no-meals`

```json
{
  "date": "2025-12-31"
}
```

✔️ Crea alertas `NO_MEALS` para pacientes sin comidas ese día  
✔️ No duplica si ya existe

---

## 📋 Resumen de endpoints principales

| Contexto | Método | Endpoint | Rol |
|--------|-------|---------|----|
| Auth | POST | `/auth/login` | PUBLIC |
| Pacientes (USER) | GET | `/me/patients` | USER |
| Registros | POST | `/me/patients/{id}/records` | USER |
| Alertas | GET | `/admin/alerts` | ADMIN |
| Resolver alerta | PATCH | `/admin/alerts/{id}/resolve` | ADMIN |
| Evaluar NO_MEALS | POST | `/admin/alerts/evaluate/no-meals` | ADMIN |

---

## 🧠 Seguridad

- JWT con `BCryptPasswordEncoder`
- Stateless (`SessionCreationPolicy.STATELESS`)
- Filtro JWT personalizado
- Manejo de errores:
    - `401 UNAUTHORIZED`
    - `403 FORBIDDEN`
- Logging configurable por `application.properties`

---

## 📌 Estado del proyecto

### ✅ Implementado
- Login JWT
- Roles USER / ADMIN
- Registros diarios
- Alertas automáticas
- Resolución de alertas
- Swagger completo

### 🔜 Posibles mejoras
- Notificaciones en tiempo real (WebSocket)
- Historial de alertas
- Exportación de informes

---

## 👏 Conclusión

