
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionTester extends JFrame {

    // Componentes de la interfaz gráfica.
    private JTextField txtUrl;
    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JTextArea txtResultado;
    private JButton btnConectar;

    // Objeto encargado de la conexión.
    private ConexionBD conexionBD;

    // Constructor de la ventana.
    public ConexionTester() {

        conexionBD = new ConexionBD();

        setTitle("Sistema de prueba de conexión JDBC");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        crearInterfaz();
    }

    // Método para construir la interfaz.
    private void crearInterfaz() {

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        // Panel del formulario.
        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel lblUrl = new JLabel("URL de conexión:");
        txtUrl = new JTextField("jdbc:sqlite:inventario.db");

        JLabel lblUsuario = new JLabel("Usuario:");
        txtUsuario = new JTextField();

        JLabel lblClave = new JLabel("Contraseña:");
        txtClave = new JPasswordField();

        btnConectar = new JButton("Probar conexión");

        panelFormulario.add(lblUrl);
        panelFormulario.add(txtUrl);

        panelFormulario.add(lblUsuario);
        panelFormulario.add(txtUsuario);

        panelFormulario.add(lblClave);
        panelFormulario.add(txtClave);

        panelFormulario.add(new JLabel(""));
        panelFormulario.add(btnConectar);

        // Área donde se muestran los resultados.
        txtResultado = new JTextArea(10, 40);
        txtResultado.setEditable(false);
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(txtResultado);

        JLabel lblResultado = new JLabel("Resultado de la conexión:");

        JPanel panelResultados = new JPanel(new BorderLayout(5, 5));
        panelResultados.add(lblResultado, BorderLayout.NORTH);
        panelResultados.add(scroll, BorderLayout.CENTER);

        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);
        panelPrincipal.add(panelResultados, BorderLayout.CENTER);

        add(panelPrincipal);

        // Evento del botón.
        btnConectar.addActionListener(e -> probarConexion());
    }

    // Método para validar la conexión.
    private void probarConexion() {

        String url = txtUrl.getText().trim();
        String usuario = txtUsuario.getText().trim();
        String clave = new String(txtClave.getPassword());

        if (url.isEmpty()) {
            mostrarResultado(
                    "Error: debes ingresar la URL de conexión.",
                    Color.RED
            );
            return;
        }

        try (Connection conexion =
                conexionBD.conectar(url, usuario, clave)) {

            mostrarResultado(
                    "¡CONEXIÓN EXITOSA!\n\n"
                    + "La conexión con la base de datos se realizó "
                    + "correctamente.\n"
                    + "URL utilizada: " + url,
                    new Color(0, 128, 0)
            );

        } catch (ClassNotFoundException e) {

            mostrarResultado(
                    "ERROR: No se encontró el controlador JDBC.\n\n"
                    + e.getMessage(),
                    Color.RED
            );

        } catch (SQLException e) {

            mostrarResultado(
                    "FALLO DE CONEXIÓN.\n\n"
                    + "No fue posible conectar con la base de datos.\n"
                    + "Detalle técnico: " + e.getMessage(),
                    Color.RED
            );
        }
    }

    // Método para mostrar los mensajes.
    private void mostrarResultado(String mensaje, Color color) {

        txtResultado.setForeground(color);
        txtResultado.setText(mensaje);
    }

    // Método principal.
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            ConexionTester ventana = new ConexionTester();
            ventana.setVisible(true);

        });
    }
}
