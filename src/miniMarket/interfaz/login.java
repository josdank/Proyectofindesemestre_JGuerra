package miniMarket.interfaz;
import miniMarket.interfaz.clases.DatabaseConnection;
import miniMarket.interfaz.clases.Usuario;
import miniMarket.interfaz.admin.actividad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login extends JFrame {
    public JPanel mainPanel;
    private JPasswordField contrasenia;
    private JTextField usuario;
    private JRadioButton mostrarContraseñaRadioButton;
    private JButton loginButton;
    private JLabel img;
    private JLabel img1;
    private JLabel mensaje;
    private JComboBox<String> comboBox1;

    public login() {
        // Inicializar los componentes
        mainPanel = new JPanel();
        contrasenia = new JPasswordField(20);
        usuario = new JTextField(20);
        mostrarContraseñaRadioButton = new JRadioButton("Mostrar Contraseña");
        loginButton = new JButton("Iniciar Sesión");
        img = new JLabel();
        img1 = new JLabel();
        mensaje = new JLabel("Iniciar Sesión");
        comboBox1 = new JComboBox<>(new String[]{"Cajero", "Admin"});

        // Configuración de los iconos de las imágenes
        ImageIcon icon = new ImageIcon("src/channels4_profile.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
        img.setIcon(icon);

        icon = new ImageIcon("src/channels4_profile.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
        img1.setIcon(icon);

        // Añadir los componentes al panel principal
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(img);
        mainPanel.add(img1);
        mainPanel.add(mensaje);
        mainPanel.add(new JLabel("Usuario:"));
        mainPanel.add(usuario);
        mainPanel.add(new JLabel("Contraseña:"));
        mainPanel.add(contrasenia);
        mainPanel.add(mostrarContraseñaRadioButton);
        mainPanel.add(new JLabel("Rol:"));
        mainPanel.add(comboBox1);
        mainPanel.add(loginButton);

        // Configuración del frame
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        // Acción del botón de inicio de sesión
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usuario.getText();
                String password = new String(contrasenia.getPassword());
                String role = (String) comboBox1.getSelectedItem();

                Usuario user = authenticate(username, password, role);
                if (user != null) {
                    if (user.getRole().equals("Cajero")) {
                        new transaccion().setVisible(true);
                    } else if (user.getRole().equals("Admin")) {
                        new actividad().setVisible(true);
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(login.this, "Credenciales incorrectas.");
                }
            }
        });

        // Acción del radio button para mostrar contraseña
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
    }

    private Usuario authenticate(String username, String password, String role) {
        String query = "SELECT * FROM usuarios WHERE username = ? AND password = ? AND role = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(rs.getString("id"), rs.getString("username"), rs.getString("password"), rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new login().setVisible(true));
    }
}
