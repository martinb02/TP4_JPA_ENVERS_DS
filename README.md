# TP4 JPA Envers - Trabajo practico de Hibernate Envers

Este proyecto demuestra el uso de **Hibernate Envers** para auditar cambios en un sistema simple de facturación. Se muestra cómo las entidades como `Factura`, `Cliente`, `Artículo` y `DetalleFactura` se persisten, actualizan y auditan utilizando Envers.

## Características

- **Entidades JPA**: Las entidades como `Factura`, `Cliente`, `Artículo` y `DetalleFactura` están modeladas y mapeadas usando anotaciones de JPA.
- **Hibernate Envers**: Se implementan capacidades de auditoría que permiten rastrear los cambios en nuestras entidades a lo largo del tiempo.
- **Relaciones Many-to-One y One-to-Many**: El proyecto ilustra cómo modelar relaciones complejas entre entidades usando JPA.
- **Operaciones de Auditoría**: Se crean auditorías automáticamente al crear, actualizar y eliminar entidades, lo que permite recuperar estados anteriores de los datos.

## Tecnologías Utilizadas

- **Java 11** o superior
- **Maven**: Para la gestión y construcción del proyecto.
- **Hibernate ORM y Envers**: Para la gestión de datos y la auditoría.
- **JPA (Java Persistence API)**: Para gestionar datos relacionales en la aplicación.
- **Base de datos H2**: Base de datos en memoria para pruebas (puede reemplazarse con MySQL, PostgreSQL, etc.).
- **Lombok**: Para reducir el código repetitivo (constructores, getters, setters, etc.).

## Estructura del Proyecto

- **Clases de Entidad**:  
  - `Articulo`: Representa un artículo o producto en el sistema.
  - `Categoria`: Representa las categorías para los artículos.
  - `Cliente`: Representa a los clientes del sistema.
  - `Domicilio`: Representa la dirección de un cliente.
  - `DetalleFactura`: Representa los detalles o artículos en una factura.
  - `Factura`: Representa la factura en sí.

- **Clases de Auditoría**:
  - `Revision`: Rastrea la información de la revisión para la auditoría.
  - `CustomRevisionListener`: Un listener que gestiona acciones personalizadas de auditoría.

- **Configuración**:
  - `persistence.xml`: Archivo de configuración que establece la conexión con la base de datos y habilita Hibernate Envers.

## Instalación y Configuración

1. **Clona el repositorio**:

   ```bash
   git clone https://github.com/tuusuario/factura-envers.git
   cd factura-envers
2. Construye el proyecto usando Maven:

  mvn clean install

3. Ejecuta la aplicación:

Puedes ejecutar la aplicación usando un IDE o desde la línea de comandos:

  mvn exec:java -Dexec.mainClass="org.example.Main"

Asegúrate de que las configuraciones necesarias para la base de datos estén proporcionadas en el archivo persistence.xml si estás utilizando una base de datos diferente.
