package miniMarket.interfaz;

import miniMarket.interfaz.clases.DatabaseConnection;
import miniMarket.interfaz.clases.Usuario;
import miniMarket.interfaz.admin.actividad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase login que representa la ventana de inicio de sesión para los usuarios del sistema miniMarket.
 */
public class login extends JFrame {
    public JPanel mainPanel5;
    private JPasswordField contra;
    private JTextField usuario;
    private JRadioButton mostrarContraseñaRadioButton;
    private JButton loginButton;
    private JLabel img1;
    private JLabel img2;
    private JLabel mensaje;
    private JComboBox<String> comboBox1;
    private JButton Salir;

    /**
     * Constructor de la clase login.
     * Inicializa los componentes de la interfaz gráfica y define las acciones de los botones.
     */
    public login() {
        // Inicializar el comboBox con las opciones requeridas
        comboBox1.addItem(" ");
        comboBox1.addItem("Cajero");
        comboBox1.addItem("Admin");

        ImageIcon icon = new ImageIcon("src/channels4_profile.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH));
        img1.setIcon(icon);

        icon = new ImageIcon("src/channels4_profile.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH));
        img2.setIcon(icon);

        // Acción del botón loginButton
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usuario.getText();
                String password = new String(contra.getPassword());
                String role = (String) comboBox1.getSelectedItem();

                Usuario user = authenticate(username, password, role);
                if (user != null) {
                    if (user.getRole().equals("Cajero")) {
                        JFrame frame = new JFrame("Transaccion");
                        frame.setContentPane(new transaccion(Integer.parseInt(user.getId())).getMainPanel6());
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);

                        JFrame transaccion_frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel5);
                        transaccion_frame.dispose();
                    } else if (user.getRole().equals("Admin")) {
                        new actividad().setVisible(true);
                    }
                    // Cerrar la ventana actual
                    JFrame login_frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel5);
                    login_frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(login.this, "Credenciales incorrectas.");
                }
            }
        });

        // Acción del botón mostrarContraseñaRadioButton
        mostrarContraseñaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mostrarContraseñaRadioButton.isSelected()) {
                    contra.setEchoChar((char) 0);
                } else {
                    contra.setEchoChar('•');
                }
            }
        });

        // Acción del botón Salir
        Salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame facturacion_frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel5);
                facturacion_frame.dispose();
            }
        });
    }

    /**
     * Autentica las credenciales del usuario contra la base de datos.
     *
     * @param username el nombre de usuario
     * @param password la contraseña del usuario
     * @param role el rol del usuario
     * @return un objeto Usuario si las credenciales son válidas, null en caso contrario
     */
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

    /**
     * Método principal que inicia la aplicación.
     *
     * @param args los argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            login loginFrame = new login();
            loginFrame.setContentPane(loginFrame.mainPanel5);
            loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            loginFrame.pack();
            loginFrame.setLocationRelativeTo(null);
            loginFrame.setVisible(true);
        });
    }
}
