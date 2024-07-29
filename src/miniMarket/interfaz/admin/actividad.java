package miniMarket.interfaz.admin;

import miniMarket.interfaz.clases.DatabaseConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class actividad extends JFrame {
    public JPanel mainPanel1;
    private JPasswordField contrasenia;
    private JTextField usuario;
    private JRadioButton mostrarContraseñaRadioButton;
    private JButton seleccion;
    private JButton volverLogin;
    private JLabel img1;
    private JLabel img2;
    private JComboBox<String> comboBox1;

    public actividad() {
        // Inicializar los componentes
        mainPanel1 = new JPanel();
        contrasenia = new JPasswordField(20);
        usuario = new JTextField(20);
        mostrarContraseñaRadioButton = new JRadioButton("Mostrar Contraseña");
        seleccion = new JButton("Seleccionar");
        volverLogin = new JButton("Volver al Login");
        img1 = new JLabel();
        img2 = new JLabel();
        comboBox1 = new JComboBox<>(new String[]{"Crear Cajero", "Revisar Ventas", "Gestionar Stock"});

        // Configuración de los iconos de las imágenes
        ImageIcon icon = new ImageIcon("src/channels4_profile.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH));
        img1.setIcon(icon);

        icon = new ImageIcon("src/channels4_profile.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH));
        img2.setIcon(icon);

        // Añadir componentes al panel principal
        mainPanel1.setLayout(new BoxLayout(mainPanel1, BoxLayout.Y_AXIS));
        mainPanel1.add(img1);
        mainPanel1.add(new JLabel("Usuario:"));
        mainPanel1.add(usuario);
        mainPanel1.add(new JLabel("Contraseña:"));
        mainPanel1.add(contrasenia);
        mainPanel1.add(mostrarContraseñaRadioButton);
        mainPanel1.add(new JLabel("Seleccione la actividad:"));
        mainPanel1.add(comboBox1);
        mainPanel1.add(seleccion);
        mainPanel1.add(volverLogin);
        mainPanel1.add(img2);

        // Mostrar u ocultar la contraseña
        mostrarContraseñaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mostrarContraseñaRadioButton.isSelected()) {
                    contrasenia.setEchoChar((char) 0);
                } else {
                    contrasenia.setEchoChar('*');
                }
            }
        });

        // Acción del botón seleccionar
        seleccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = usuario.getText();
                String pass = new String(contrasenia.getPassword());
                try {
                    if (validarAdmin(user, pass)) {
                        String selectedActivity = (String) comboBox1.getSelectedItem();
                        switch (selectedActivity) {
                            case "Crear Cajero":
                                // Redirigir a la ventana de crear cajero
                                new cajerosCreacion().setVisible(true);
                                dispose();
                                break;
                            case "Revisar Ventas":
                                // Redirigir a la ventana de revisar ventas
                                new ventas().setVisible(true);
                                dispose();
                                break;
                            case "Gestionar Stock":
                                // Redirigir a la ventana de gestionar stock
                                new stock().setVisible(true);
                                dispose();
                                break;
                        }
                    } else {
                        JOptionPane.showMessageDialog(actividad.this, "Credenciales incorrectas. Inténtelo de nuevo.");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Configuración de la ventana
        setContentPane(mainPanel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    // Método para validar las credenciales del administrador
    private boolean validarAdmin(String user, String pass) throws SQLException {
        boolean isValid = false;
        Connection connection = DatabaseConnection.getConnection();
        try {
            String query = "SELECT * FROM usuarios WHERE username = ? AND password = ? AND role = 'admin'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isValid = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new actividad().setVisible(true));
    }
}
