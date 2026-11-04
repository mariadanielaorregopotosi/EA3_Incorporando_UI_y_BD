
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    // Método encargado de establecer la conexión.
    public Connection conectar(String url, String usuario, String clave)
            throws ClassNotFoundException, SQLException {

        // Cargar el controlador JDBC de SQLite.
        Class.forName("org.sqlite.JDBC");

        // Establecer la conexión utilizando los datos ingresados.
        return DriverManager.getConnection(url, usuario, clave);
    }
}
