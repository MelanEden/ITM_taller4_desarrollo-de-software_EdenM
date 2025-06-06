# Diseño de Software ITM

[![Licencia GPLv3](https://img.shields.io/badge/Licencia-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0.html)  
[![Java 17](https://img.shields.io/badge/Java-17-red.svg)](https://www.oracle.com/java/)  
[![Spring Boot 3](https://img.shields.io/badge/Spring%20Boot-3.1.5-brightgreen.svg)](https://spring.io/projects/spring-boot)

---

## 📥 Clonar el Repositorio

```bash
git clone https://github.com/adolfo9castro/diseno_software_itm.git
cd diseno_software_itm
git checkout parcial_3 
```

- **O descarga la zip:** <https://github.com/adolfo9castro/diseno_software_itm/archive/refs/heads/parcial_3.zip>  
---

## 🛠 Requisitos Previos

- **Java JDK 17**: Descargar e instalar  
- **Maven 3.8+**: Instalar desde el sitio oficial  
- **Git**: Instalar desde el sitio oficial  
- **VS Code (opcional)**: Descargar desde el sitio oficial  

**Extensiones recomendadas para VS Code**  
- Spring Boot Extension Pack  
- Maven for Java  

---

## ⚙️ Instalación y Configuración

1. Instalar dependencias:

   ```bash
   mvn clean install
   ```

2. (Opcional) Configurar JDK en VS Code:

   - Abre la paleta de comandos (`Ctrl + Shift + P`).  
   - Selecciona **Java: Configure Java Runtime**.  
   - Elige el JDK 17 instalado.  

---

## 🚀 Ejecutar el Proyecto

```bash
mvn spring-boot:run
```

La API estará disponible en:  
<http://localhost:8080>  

> Presiona `Ctrl + C` para detenerla.

---

## 🔍 Probar la API

### 📌 Swagger UI

Accede a la documentación interactiva:  
<http://localhost:8080/swagger-ui.html>

**Ejemplo de uso**  
1. Expande el endpoint `/api/festivos`  
2. Ingresa `25/12/2024` en `TextoFecha`  
3. Haz clic en **Execute**

### 📌 Pruebas con `curl`

```bash
curl "http://localhost:8080/api/festivos?TextoFecha=25/12/2024"
```

**Respuesta esperada**  
```json
true
```


---

## 📂 Estructura del Proyecto

```
diseno_software_itm/
├── src/
│   ├── main/
│   │   ├── java/co/edu/itm/
│   │   │   ├── config/       # Configuraciones de Swagger
│   │   │   ├── controller/   # Endpoints REST
│   │   │   ├── model/        # Entidades de datos
│   │   │   ├── service/      # Lógica de negocio
│   │   │   └── util/         # Utilidades (cálculo de Pascua)
│   │   └── resources/        # Archivos de configuración
│   └── test/                 # Pruebas unitarias
├── pom.xml                   # Configuración de Maven
└── LICENSE                   # Licencia GPLv3
```

---

## 📜 Licencia

Este proyecto está bajo la **Licencia Pública General de GNU v3.0**.  
Consulta el archivo [LICENSE](./LICENSE) para más detalles.

```text

Adolfo Castro

Este programa es software libre: puede redistribuirlo y/o modificarlo
bajo los términos de la GNU General Public License publicada por
la Free Software Foundation, ya sea la versión 3 de la Licencia, o
(a su elección) cualquier versión posterior.
```

---

## ✉️ Contacto

- **Autor:** Adolfo Castro  
- **Correo Institucional:** jairocastro208461@correo.itm.edu.co
- **Repositorio:** <https://github.com/adolfo9castro/diseno_software_itm>  

---

## 🤝 Contribuciones

¿Encontraste un error o tienes una sugerencia?  
Abre un **issue** o envía un **pull request**.

---

