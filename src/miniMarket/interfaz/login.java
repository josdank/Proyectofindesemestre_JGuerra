package miniMarket.interfaz;
import miniMarket.DatabaseConnection;
import miniMarket.Usuario;
import miniMarket.interfaz.admin.actividad;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login extends JFrame{
    public JPanel mainPanel;
    private JPasswordField contrasenia;
    private JTextField usuario;
    private JRadioButton mostrarContraseñaRadioButton;
    private JButton loginButton;
    private JLabel img1;
    private JLabel img2;
    private JLabel mensaje;
    private JComboBox comboBox1;

    public login() {

        ImageIcon icon = new ImageIcon("src/channels4_profile.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH));
        img1.setIcon(icon);

        icon = new ImageIcon("src/channels4_profile.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH));
        img2.setIcon(icon);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usuario.getText();
                String password = new String(contrasenia.getPassword());
                String role = (String) comboBox1.getSelectedItem();

                Usuario user = authenticate(username, password, role);
                if (user != null) {
                    if (user.getRole().equals("Cajero")) {
                        new transaccion(user).setVisible(true);
                    } else if (user.getRole().equals("Admin")) {
                        new actividad().setVisible(true);
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(login.this, "Credenciales incorrectas.");
                }
            }
        });
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


