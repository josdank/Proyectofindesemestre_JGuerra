package miniMarket.interfaz.admin;

import miniMarket.DatabaseConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class cajerosCreacion extends JFrame {
    private JPanel mainPanel;
    private JTextField ncajero;
    private JTextField usuario;
    private JPasswordField contrasenia;
    private JButton registrarCajero;
    private JButton volverActividad;
    private JComboBox<String> rolComboBox;
    private JRadioButton mostrarContraseñaRadioButton;
    private JLabel img1;
    private JLabel img2;
    private JLabel mensaje;

    public cajerosCreacion() {
        // Inicializar los componentes
        mainPanel = new JPanel();
        ncajero = new JTextField(20);
        usuario = new JTextField(20);
        contrasenia = new JPasswordField(20);
        registrarCajero = new JButton("Registrar Cajero");
        volverActividad = new JButton("Volver a Actividad");
        rolComboBox = new JComboBox<>(new String[]{"Cajero", "Admin"});
        mostrarContraseñaRadioButton = new JRadioButton("Mostrar Contraseña");
        img1 = new JLabel();
        img2 = new JLabel();
        mensaje = new JLabel();

        // Configuración de las imágenes
        ImageIcon icon = new ImageIcon("src/channels4_profile.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH));
        img1.setIcon(icon);
        img2.setIcon(icon);

        // Añadir componentes al panel principal
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(new JLabel("Número de Cajero:"));
        mainPanel.add(ncajero);
        mainPanel.add(new JLabel("Usuario:"));
        mainPanel.add(usuario);
        mainPanel.add(new JLabel("Contraseña:"));
        mainPanel.add(contrasenia);
        mainPanel.add(mostrarContraseñaRadioButton);
        mainPanel.add(new JLabel("Rol:"));
        mainPanel.add(rolComboBox);
        mainPanel.add(registrarCajero);
        mainPanel.add(volverActividad);
        mainPanel.add(img1);
        mainPanel.add(img2);
        mainPanel.add(mensaje);

        // Acción del botón registrar cajero
        registrarCajero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cajeroNumero = ncajero.getText();
                String user = usuario.getText();
                String pass = new String(contrasenia.getPassword());
                String rol = (String) rolComboBox.getSelectedItem();

                try {
                    if (registrarCajero(cajeroNumero, user, pass, rol)) {
                        JOptionPane.showMessageDialog(cajerosCreacion.this, "Cajero registrado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(cajerosCreacion.this, "Error al registrar el cajero.");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Acción del botón volver a actividad
        volverActividad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new actividad().setVisible(true);
                dispose();
            }
        });

        // Acción del botón mostrar contraseña
        mostrarContraseñaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mostrarContraseñaRadioButton.isSelected()) {
                    contrasenia.setEchoChar((char) 0);
                } else {
                    contrasenia.setEchoChar('•');
                }
            }
        });

        // Configuración de la ventana
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    // Método para registrar cajero en la base de datos
    private boolean registrarCajero(String cajeroNumero, String user, String pass, String rol) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        try {
            String query = "INSERT INTO usuarios (cajero_numero, username, password, role) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cajeroNumero);
            preparedStatement.setString(2, user);
            preparedStatement.setString(3, pass);
            preparedStatement.setString(4, rol);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new cajerosCreacion().setVisible(true));
    }
}
