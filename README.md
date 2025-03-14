# 🏗️ EmployeeApp - Spring Boot WAR Deployment 🚀

[![Java](https://img.shields.io/badge/Java-17-blue)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.3-brightgreen)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-Enabled-blue)](https://www.docker.com/)
[![Kubernetes](https://img.shields.io/badge/Kubernetes-Deployable-blue)](https://kubernetes.io/)

## Advertencia ⚠️
El backend de esta aplicación consume el servicio `http://dummy.restapiexample.com/api/v1/employees`, el cual ha estado presentando problemas de acceso y, en ocasiones, retorna el código `429 - Too Many Requests`. Esto puede afectar la funcionalidad de la aplicación y generar errores intermitentes en las solicitudes.

## 📌 Descripción
EmployeeApp es una API REST construida con **Spring Boot**, empaquetada como un **WAR**, y desplegada en **Tomcat** dentro de **Docker** y **Kubernetes**.  
Este proyecto implementa **CI/CD con GitHub Actions** para generar el WAR y publicarlo como una imagen Docker en Docker Hub.

---

## 🏗️ **Tecnologías Usadas**
- **Java 17**
- **Spring Boot 3.4.3**
- **Tomcat 10**
- **Gradle**
- **Docker**
- **Kubernetes**
- **GitHub Actions CI/CD**

---


## 📌 **Principios SOLID Implementados**
- **SRP (Single Responsibility Principle)** → Separación clara entre Modelo, Servicio y DTO.  
- **OCP (Open/Closed Principle)** → `EmployeeDTO` permite extender la salida sin modificar el modelo.  
- **LSP (Liskov Substitution Principle)** → Se pueden intercambiar implementaciones sin romper el código.  
- **ISP (Interface Segregation Principle)** → `IEmployeeRepository` y `IEmployeeService` están bien definidos.  
- **DIP (Dependency Inversion Principle)** → `EmployeeService` no depende directamente de `EmployeeRepository`.
---

## 📌 Cómo Ejecutar el Proyecto
Para ejecutar el proyecto localmente, usa el siguiente comando:

1. Descarga el repo BackEnd
```sh
git clone https://github.com/jfsolarte/SOLID
```
2. inicia con gradle o .gradlew

```sh
gradle bootRun
```
Esto iniciará la aplicación en `http://localhost:8080`.

3. Ya teniendo el backEnd funcionando podemos continuar con FrontEnd

```sh
git clone https://github.com/jfsolarte/employee-ui
cd employee-ui

```
Modificar `src/environments/environment.ts` para establecer la API base:
```ts
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api/employees'
};
```
Dentro de la carpeta con node 18 

```sh
 ng serve
```
La aplicación estará disponible en `http://localhost:4200/`.


---

## 📌 Despliegue en Wildfly
Si deseas desplegar la aplicación en un servidor **Wildfly**, sigue estos pasos:
1. Compila el proyecto con:
   ```sh
   gradle build
   ```
2. Copia el archivo WAR generado en `build/libs/` a la carpeta `standalone/deployments/` de Wildfly.
3. Inicia Wildfly con:
   ```sh
   ./standalone.sh
   ```
4. Accede a la aplicación en `http://localhost:8080/EmployeeApp`.

---
## 📌 Despliegue en Wildfly

## 📌 **Estructura del Proyecto**
```plaintext
EmployeeApp
│── src/main/java/com/example/EmployeeApp
│   ├── config/
│   │   ├── AppConfig.java                 # Configuración de la aplicación
│   │
│   ├── controller/
│   │   ├── EmployeeController.java        # Controlador REST
│   │
│   ├── dto/
│   │   ├── EmployeeDTO.java               # DTO de empleado
│   │   ├── EmployeeListResponseDTO.java   # DTO de lista de empleados
│   │   ├── EmployeeObjectResponseDTO.java # DTO de respuesta de empleado
│   │
│   ├── exception/
│   │   ├── EntityNotFoundException.java   # Excepción para entidad no encontrada
│   │   ├── GlobalExceptionHandler.java    # Manejador global de excepciones
│   │   ├── ServiceException.java          # Excepción de servicio
│   │
│   ├── model/
│   │   ├── Employee.java                  # Modelo de empleado
│   │
│   ├── repository/
│   │   ├── EmployeeRepository.java        # Implementación del repositorio
│   │   ├── IEmployeeRepository.java       # Interfaz del repositorio
│   │
│   ├── service/
│   │   ├── EmployeeService.java           # Implementación del servicio
│   │   ├── IEmployeeService.java          # Interfaz del servicio
│   │   ├── MessageService.java            # Servicio de mensajería
│   │
│   ├── EmployeeAppApplication.java        # Clase principal
│   ├── ServletInitializer.java            # Inicializador de Servlet
│
│── src/main/resources/
│   ├── static/                            # Archivos estáticos
│   ├── templates/                         # Plantillas
│   ├── application.properties             # Configuración de la aplicación
│   ├── logging.properties                 # Configuración de logs
│   ├── messages.properties                # Mensajes de la aplicación
│
│── src/test/java/com/example/EmployeeApp/
│   ├── service/
│   │   ├── EmployeeServiceTest.java       # Pruebas unitarias del servicio
│   ├── EmployeeAppApplicationTests.java   # Pruebas de la aplicación
│── Dockerfile                        # Configuración de Docker
│── envirinment/k8/deployment.yaml    # Configuración de Kubernetes
│── envirinment/k8/service.yaml    # Configuración de Kubernetes
│── envirinment/docker-compose.yml    # Configuración de Docker Compose
│── .github/workflows/ci.yml          # CI/CD con GitHub Actions
│── build.gradle                      # Configuración de Gradle
│── README.md                         # Documentación del proyecto


```
# 📌 Endpoints de la API

## 👤 Empleado (EmployeeController)
Maneja las operaciones de consulta sobre los empleados.

---

### 📌 Obtener todos los empleados
```http
GET /api/employees
```

- 📄 **Descripción:** Obtiene una lista de todos los empleados.
- ✅ **Código de éxito:** 200 OK
- 📬 **Respuesta:**

```json
[
  {
    "id": 1,
    "employeeName": "John Doe",
    "employeeSalary": 5000,
    "employeeAge": 30,
    "profileImage": "url_imagen",
    "employeeAnualSalary": 60000
  },
  {
    "id": 2,
    "employeeName": "Jane Smith",
    "employeeSalary": 4500,
    "employeeAge": 28,
    "profileImage": "url_imagen",
    "employeeAnualSalary": 54000
  }
]
```

---

### 📌 Obtener empleado por ID
```http
GET /api/employees/{id}
```

- 📄 **Descripción:** Obtiene los datos de un empleado específico por su ID.
- ✅ **Código de éxito:** 200 OK
- ❌ **Código de error:** 404 Not Found (si el empleado no existe)
- 📬 **Respuesta:**

```json
{
  "id": 1,
  "employeeName": "John Doe",
  "employeeSalary": 5000,
  "employeeAge": 30,
  "profileImage": "url_imagen",
  "employeeAnualSalary": 60000
}
```

---

## 🚀 Compilar el proyecto
Para compilar el proyecto con Gradle, ejecuta el siguiente comando:

```sh
gradle build
```

## 🎮 Ejecutar las pruebas
Para ejecutar las pruebas, usa el siguiente comando:

```sh
gradle test
```
### 🎮 Escribir pruebas unitarias con JUnit 5
Las pruebas se encuentran en `src/test/java/com/example/EmployeeApp/service/EmployeeServiceTest.java`. Ejemplo de prueba para `EmployeeService`:


---


## 🛠️ Construcción de la imagen Docker
Para construir la imagen Docker, ejecuta:

```sh
docker build -t employee-app .
```

## 💀 Ejecutar con Docker Run
Para ejecutar el contenedor manualmente:

```sh
docker run -p 8080:8080 employee-app
curl http://localhost:8080/api/employees
```

## 🌐 Ejecutar con Docker Compose
Para levantar la aplicación con `docker-compose`:

```sh
docker-compose up -d
```

## 🌟 GitHub Actions CI/CD
Este proyecto usa **GitHub Actions** para automatizar la construcción y despliegue. El pipeline realiza:
1. Compilación del proyecto
2. Generación del WAR
3. Construcción de la imagen Docker
4. Subida de la imagen a Docker Hub

## 🛡️ Despliegue en Kubernetes
Para desplegar en Kubernetes:

```sh
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
```

Para ver los pods ejecutándose:
```sh
kubectl get pods
```

Para acceder a la API desde Kubernetes:
```sh
kubectl get services employee-app-service
```

💪 ¡Listo! Ahora tienes una guía completa de uso y despliegue. 🚀


## 📌 Futuras Mejoras
En futuras versiones se implementara:
- Probar k8
- Probar docker-compose.yml
- Probar github Actions
- Cobertura de pruebas 
- Pruebas Funcionales
- Pruebas de estrés

---

## 💡 Contribución
¡Las contribuciones son bienvenidas! Si tienes una mejora o corrección, sigue estos pasos:
1. Haz un fork del repositorio.
2. Crea una rama para tu característica: `git checkout -b feature/nueva-caracteristica`.
3. Realiza los cambios y haz un commit: `git commit -m 'Añadir nueva característica'`.
4. Envía tus cambios a tu fork y crea un Pull Request.

---
## 📌 Autor
**Desarrollado por:** Don Jairo Fernando Solarte

---



🚀 ¡Gracias por usar EmployeeApp! 🚀


