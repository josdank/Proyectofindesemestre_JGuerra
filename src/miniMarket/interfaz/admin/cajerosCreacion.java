package miniMarket.interfaz.admin;

import miniMarket.DatabaseConnection;

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
        // Acción del botón Volver
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new actividad().setVisible(true);
                dispose();
            }
        });

        // Acción del botón Crear Cajero
        crearCajeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar campos
                if (ntelefono.getText().isEmpty() || nombre.getText().isEmpty() || usuario.getText().isEmpty() || password.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(cajerosCreacion.this, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
                    highlightEmptyFields();
                } else if (ntelefono.getText().length() != 10) {
                    JOptionPane.showMessageDialog(cajerosCreacion.this, "El número de teléfono debe tener 10 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
                    ntelefono.setBorder(BorderFactory.createLineBorder(Color.RED));
                } else if (!nombreCompletoEsValido(nombre.getText())) {
                    JOptionPane.showMessageDialog(cajerosCreacion.this, "El campo nombre debe contener al menos dos nombres y un apellido.", "Error", JOptionPane.ERROR_MESSAGE);
                    nombre.setBorder(BorderFactory.createLineBorder(Color.RED));
                } else {
                    // Crear cajero en la base de datos
                    try {
                        crearCajeroEnBaseDeDatos(nombre.getText(), usuario.getText(), new String(password.getPassword()), ntelefono.getText());
                        // Enviar mensaje de WhatsApp
                        enviarMensajeWhatsApp(ntelefono.getText(), "Su usuario, cajero ha sido creado correctamente, estas son sus credenciales para su acceso");
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
        String query = "INSERT INTO cajeros (nombre_completo, usuario, contrasena, telefono) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nombre);
        preparedStatement.setString(2, usuario);
        preparedStatement.setString(3, contrasena);
        preparedStatement.setString(4, telefono);
        preparedStatement.executeUpdate();
    }

    private void enviarMensajeWhatsApp(String telefono, String mensaje) {
        String url = "https://wa.me/593964030442" + telefono + "?text=" + mensaje.replace(" ", "%20");
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
