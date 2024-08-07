package miniMarket.interfaz.admin;

import miniMarket.estilos.estilos;
import miniMarket.interfaz.clases.DatabaseConnection;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Clase cajerosCreacion que representa la ventana para la creación de cajeros.
 */
public class cajerosCreacion extends JFrame {
    private JTextField ntelefono;
    private JTextField nombre;
    public JPanel mainPanel1;
    private JTextField usuario;
    private JPasswordField password;
    private JRadioButton mostrarContrasenia;
    private JButton volverButton;
    private JButton crearCajeroButton;

    /**
     * Constructor de la clase cajerosCreacion.
     * Configura los estilos y las acciones de los componentes.
     */
    public cajerosCreacion() {
        estilos.aplicarColorDeFondo(mainPanel1);
        estilos.aplicarEstilos1(nombre);
        estilos.aplicarEstilos1(usuario);
        estilos.aplicarEstilos1(password);
        estilos.aplicarEstilos2(volverButton);
        estilos.aplicarEstilos2(crearCajeroButton);
        estilos.aplicarEstilos3(mostrarContrasenia);

        // Acción para mostrar u ocultar la contraseña
        mostrarContrasenia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mostrarContrasenia.isSelected()) {
                    password.setEchoChar((char) 0);
                } else {
                    password.setEchoChar('*');
                }
            }
        });

        // Acción para volver a la ventana de actividades
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Actividades");
                frame.setContentPane(new actividad().mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                JFrame ccreacion_frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel1);
                ccreacion_frame.dispose();
            }
        });

        // Acción para crear un cajero
        crearCajeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ntelefono.getText().isEmpty() || nombre.getText().isEmpty() || usuario.getText().isEmpty() || password.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(cajerosCreacion.this, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
                    highlightEmptyFields();
                } else if (ntelefono.getText().length() != 16) {
                    JOptionPane.showMessageDialog(cajerosCreacion.this, "El número de teléfono debe tener 10 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
                    ntelefono.setBorder(BorderFactory.createLineBorder(Color.RED));
                } else if (!nombreCompletoEsValido(nombre.getText())) {
                    JOptionPane.showMessageDialog(cajerosCreacion.this, "El campo nombre debe contener al menos dos nombres y un apellido.", "Error", JOptionPane.ERROR_MESSAGE);
                    nombre.setBorder(BorderFactory.createLineBorder(Color.RED));
                } else {
                    try {
                        crearCajeroEnBaseDeDatos(nombre.getText(), usuario.getText(), new String(password.getPassword()), ntelefono.getText());
                        enviarMensajeWhatsApp(ntelefono.getText(), "Su usuario: " + usuario.getText() + " y su contraseña: " + new String(password.getPassword()));
                        JOptionPane.showMessageDialog(cajerosCreacion.this, "Cajero creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(cajerosCreacion.this, "Error al crear el cajero en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * Verifica si el nombre completo es válido.
     *
     * @param nombreCompleto el nombre completo a verificar
     * @return true si el nombre es válido, false en caso contrario
     */
    private boolean nombreCompletoEsValido(String nombreCompleto) {
        String[] partes = nombreCompleto.trim().split("\\s+");
        return partes.length >= 3;
    }

    /**
     * Resalta los campos vacíos con un borde rojo.
     */
    private void highlightEmptyFields() {
        Border redBorder = BorderFactory.createLineBorder(Color.RED);
        Border defaultBorder = UIManager.getBorder("TextField.border");

        if (ntelefono.getText().isEmpty()) {
            ntelefono.setBorder(redBorder);
        } else {
            ntelefono.setBorder(defaultBorder);
        }
        if (nombre.getText().isEmpty()) {
            nombre.setBorder(redBorder);
        } else {
            nombre.setBorder(defaultBorder);
        }
        if (usuario.getText().isEmpty()) {
            usuario.setBorder(redBorder);
        } else {
            usuario.setBorder(defaultBorder);
        }
        if (password.getPassword().length == 0) {
            password.setBorder(redBorder);
        } else {
            password.setBorder(defaultBorder);
        }
    }

    /**
     * Crea un cajero en la base de datos.
     *
     * @param nombre el nombre del cajero
     * @param usuario el nombre de usuario del cajero
     * @param contrasena la contraseña del cajero
     * @param telefono el número de teléfono del cajero
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    private void crearCajeroEnBaseDeDatos(String nombre, String usuario, String contrasena, String telefono) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query1 = "INSERT INTO cajeros (nombre_completo, usuario, contrasena, telefono) VALUES (?, ?, ?, ?)";
        String query2 = "INSERT INTO usuarios (username, password, role) VALUES (?, ?, 'Cajero')";

        try {
            connection.setAutoCommit(false); // Iniciar transacción

            try (PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
                 PreparedStatement preparedStatement2 = connection.prepareStatement(query2)) {

                // Insertar en la tabla 'cajeros'
                preparedStatement1.setString(1, nombre);
                preparedStatement1.setString(2, usuario);
                preparedStatement1.setString(3, contrasena);
                preparedStatement1.setString(4, telefono);
                preparedStatement1.executeUpdate();

                // Insertar en la tabla 'usuarios'
                preparedStatement2.setString(1, usuario);
                preparedStatement2.setString(2, contrasena);
                preparedStatement2.executeUpdate();

                connection.commit(); // Confirmar transacción
            } catch (SQLException ex) {
                connection.rollback(); // Revertir transacción en caso de error
                throw ex;
            }
        } finally {
            connection.setAutoCommit(true); // Restaurar modo autocommit
        }
    }

    /**
     * Envía un mensaje de WhatsApp al número especificado.
     *
     * @param telefono el número de teléfono al que se enviará el mensaje
     * @param mensaje el contenido del mensaje
     */
    private void enviarMensajeWhatsApp(String telefono, String mensaje) {
        telefono = telefono.replaceAll("[^\\d]", "");
        if (telefono.startsWith("0")) {
            telefono = telefono.substring(1);
        }
        String numeroCompleto =  telefono;

        String url = "https://wa.me/" + numeroCompleto + "?text=" + mensaje.replace(" ", "%20");
        try {
            Desktop.getDesktop().browse(new URL(url).toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Establece la visibilidad de la ventana de creación de cajeros.
     *
     * @param b true para hacer visible la ventana, false en caso contrario
     */
    public void setVisible(boolean b) {
        JFrame frame = new JFrame("Crear Cajero");
        frame.setContentPane(mainPanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(b);
    }

    /**
     * Método principal que inicia la aplicación.
     *
     * @param args los argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new cajerosCreacion().setVisible(true));
    }
}
