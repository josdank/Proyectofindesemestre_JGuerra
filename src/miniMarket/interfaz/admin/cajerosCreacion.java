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

public class cajerosCreacion extends JFrame {
    private JTextField ntelefono;
    private JTextField nombre;
    public JPanel mainPanel1;
    private JTextField usuario;
    private JPasswordField password;
    private JRadioButton mostrarContrasenia;
    private JButton volverButton;
    private JButton crearCajeroButton;

    public cajerosCreacion() {
        estilos.aplicarColorDeFondo(mainPanel1);
        estilos.aplicarEstilos1(nombre);
        estilos.aplicarEstilos1(usuario);
        estilos.aplicarEstilos1(password);
        estilos.aplicarEstilos2(volverButton);
        estilos.aplicarEstilos2(crearCajeroButton);
        estilos.aplicarEstilos3(mostrarContrasenia);

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

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Biografía");
                frame.setContentPane(new actividad().mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                JFrame hobbies_frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel1);
                hobbies_frame.dispose();
            }
        });

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

    private boolean nombreCompletoEsValido(String nombreCompleto) {
        String[] partes = nombreCompleto.trim().split("\\s+");
        return partes.length >= 3;
    }

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

    public void setVisible(boolean b) {
        JFrame frame = new JFrame("Crear Cajero");
        frame.setContentPane(mainPanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(b);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new cajerosCreacion().setVisible(true));
    }
}
