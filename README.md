# S25- Incorporando UI y BD

*Integrantes:*  
- Maria Daniela Orrego Potosi
- Juan David Arboleda Cortes
- Ivan David Gonzalez Mercado

*Programa:* Ingenieria en Software y datos
*Institución:* IU Digital de Antioquia  
*Asignatura:* Programación Orientada a Objetos II  

## 1. Descripción del proyecto

Este proyecto consiste en el desarrollo de una aplicación de escritorio en Java utilizando la librería gráfica Swing y la API JDBC. Su objetivo es permitir al usuario ingresar los parámetros de configuración de una base de datos (URL, usuario y contraseña) para validar la conexión en tiempo real, gestionando y mostrando de forma visual los mensajes de éxito o los errores técnicos generados por excepciones de JDBC.

## 2. Actividades desarrolladas

- *Interfaz gráfica (Swing):* diseño de la ventana principal (`JFrame`) organizada mediante `BorderLayout` y `GridLayout`, incorporando etiquetas (`JLabel`), campos de entrada (`JTextField` y `JPasswordField`), botón de ejecución (`JButton`) y área de salida (`JTextArea` dentro de un `JScrollPane`).
- *Lógica de conexión (JDBC):* implementación de la clase `ConexionBD` encargada de cargar el controlador `org.sqlite.JDBC` y establecer la conexión mediante `DriverManager.getConnection()`.
- *Tratamiento de excepciones:* captura controlada de `ClassNotFoundException` (cuando falta el driver) y `SQLException` (cuando falla la URL o las credenciales), mostrando alertas en color rojo con el detalle técnico (`e.getMessage()`) o en verde cuando la conexión es exitosa.
- *Gestión segura de recursos:* uso del bloque `try-with-resources` en la clase `ConexionTester` para garantizar el cierre automático de la instancia `Connection` al finalizar la prueba.

## 3. Herramientas utilizadas

- Java (JDK)
- Java Swing y AWT
- API JDBC
- Driver SQLite (`sqlite-jdbc-3.54.1.0.jar`)
- Visual Studio Code y Git


## 4. Ejecucion
#### Compilar proyecto
```bash
- javac -cp "src/lib/sqlite-jdbc-3.54.1.0.jar" -d bin src/ConexionBD.java src/ConexionTester.java
```
#### Ejecutar programa
```bash
- java -cp "bin;src/lib/sqlite-jdbc-3.54.1.0.jar" ConexionTester
```
