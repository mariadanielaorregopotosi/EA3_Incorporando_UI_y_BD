# S25 - Incorporando UI y BD

*Integrantes:*  
- Maria Daniela Orrego Potosi
- Juan David Arboleda Cortes
- Ivan David Gonzalez Mercado

*Programa:* Ingeniería en Software y Datos  
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

- Java (JDK 17 o superior; recomendado JDK 21)
- Java Swing y AWT
- API JDBC
- Driver SQLite (`sqlite-jdbc-3.54.1.0.jar`, incluido en `src/lib/`)
- Visual Studio Code y Git

## 4. Estructura del proyecto

```text
EA3_Incorporando_UI_y_BD/
├── src/
│   ├── ConexionBD.java          # Carga el driver y abre la conexión JDBC
│   ├── ConexionTester.java      # Ventana Swing y manejo de excepciones (contiene main)
│   └── lib/
│       └── sqlite-jdbc-3.54.1.0.jar
├── bin/                         # Clases compiladas (.class)
├── inventario.db                # Base de datos SQLite usada por defecto
└── README.md
```

## 5. Requisitos previos

1. Tener instalado un **JDK 17 o superior** (no basta con un JRE).
2. Verificar que `java` y `javac` pertenezcan **a la misma versión**:

   ```bash
   java -version
   javac -version
   ```

   Si `java` muestra una versión anterior (por ejemplo `1.8`), el programa fallará al ejecutarse. Ver la sección [Solución de problemas](#8-solución-de-problemas).

## 6. Ejecución

Todos los comandos se ejecutan desde la carpeta raíz del proyecto.

### Compilar

```bash
javac -encoding UTF-8 -cp "src/lib/sqlite-jdbc-3.54.1.0.jar" -d bin src/ConexionBD.java src/ConexionTester.java
```

> La opción `-encoding UTF-8` garantiza que las tildes y la ñ de los mensajes de la interfaz se muestren correctamente, sin importar la configuración regional del sistema.

### Ejecutar

En **Windows** (el separador del classpath es `;`):

```bash
java -cp "bin;src/lib/sqlite-jdbc-3.54.1.0.jar" ConexionTester
```

En **Linux / macOS** (el separador del classpath es `:`):

```bash
java -cp "bin:src/lib/sqlite-jdbc-3.54.1.0.jar" ConexionTester
```

## 7. Uso de la aplicación

1. Al abrir la ventana, el campo **URL de conexión** viene precargado con `jdbc:sqlite:inventario.db`.
2. Los campos **Usuario** y **Contraseña** pueden dejarse vacíos: SQLite no utiliza credenciales.
3. Presionar **Probar conexión** y revisar el mensaje en el área de resultados.

Casos de prueba sugeridos:

| Caso | Dato ingresado | Resultado esperado |
|------|----------------|--------------------|
| Conexión exitosa | URL `jdbc:sqlite:inventario.db` | Mensaje verde: **¡CONEXIÓN EXITOSA!** |
| URL vacía | Campo URL en blanco | Mensaje rojo: *debes ingresar la URL de conexión* |
| URL inválida | `jdbc:mysql://localhost/prueba` | Mensaje rojo `SQLException`: *No suitable driver found…* |
| Driver ausente | Ejecutar sin el `.jar` en el classpath | Mensaje rojo `ClassNotFoundException`: *No se encontró el controlador JDBC* |

> Nota: SQLite crea el archivo de base de datos si no existe, por lo que cualquier URL `jdbc:sqlite:<ruta>` con una carpeta válida se conectará correctamente.

## 8. Solución de problemas

| Problema | Causa | Solución |
|----------|-------|----------|
| `UnsupportedClassVersionError ... class file version 65.0` | Se está ejecutando con una versión de Java más antigua que la usada para compilar (por ejemplo Java 8). | Ejecutar con el mismo JDK con el que se compiló, o recompilar con el JDK disponible. En Windows, revisar que la carpeta `bin` del JDK nuevo esté antes en la variable `PATH` que `C:\Program Files (x86)\Common Files\Oracle\Java\java8path`. |
| `Error: Could not find or load main class ConexionTester` | Falta la carpeta `bin` en el classpath o no se ha compilado. | Compilar primero y ejecutar desde la carpeta raíz del proyecto. |
| Tildes mostradas como `Ã³`, `Ã©` en la ventana | Se compiló sin indicar la codificación de los archivos fuente. | Compilar con `-encoding UTF-8`. |
| Tildes mal mostradas al leer este README en PowerShell | Windows PowerShell 5.1 lee los archivos como ANSI por defecto. | El archivo está en UTF-8; abrirlo en VS Code o usar `Get-Content README.md -Encoding UTF8`. |
