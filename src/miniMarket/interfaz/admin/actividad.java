package miniMarket.interfaz.admin;

import miniMarket.DatabaseConnection;
import miniMarket.interfaz.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class actividad extends JFrame {
    public JPanel mainPanel;
    private JButton seleccion;
    private JButton volverLogin;
    private JLabel img1;
    private JLabel img2;
    private JComboBox<String> comboBox1;

    public actividad() {
        // Inicializar los componentes faltantes
        comboBox1 = new JComboBox<>(new String[]{"Crear Cajero", "Aumentar Stock", "Revisar ventas"});

        // Configuración de los iconos de las imágenes
        ImageIcon icon = new ImageIcon("src/channels4_profile.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
        img1.setIcon(icon);

        icon = new ImageIcon("src/channels4_profile.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
        img2.setIcon(icon);


        // Acción del botón seleccionar
        seleccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedActivity = (String) comboBox1.getSelectedItem();
                switch (selectedActivity) {
                    case "Crear Cajero":
                        // Redirigir a la ventana de crear cajero
                        new cajerosCreacion().setVisible(true);
                        dispose();
                        break;
                    case "Aumentar Stock":
                        // Redirigir a la ventana de gestionar stock
                        break;
                    case "Revisar ventas":
                        // Redirigir a la ventana de revisar ventas
                        new ventas().setVisible(true);
                        dispose();
                        break;
                    default:
                }
            }
        });

        // Acción del botón volver al login
        volverLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login().setVisible(true);
                dispose();
            }
        });

        // Configuración de la ventana
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
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
