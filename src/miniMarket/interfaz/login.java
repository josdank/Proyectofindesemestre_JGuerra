package miniMarket.interfaz;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login {
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
                String Usuario = "Josueso";
                String correoUsuario = "josue.guerra@epn.edu.ec";
                String contraUsuario = "159687Js";

                String inputUsuario = usuario.getText();
                String inputContraseña = new String(contrasenia.getPassword());

                if ((inputUsuario.equals(Usuario) || inputUsuario.equals(correoUsuario)) && inputContraseña.equals(contraUsuario)) {
                    JFrame frame = new JFrame("Biografía");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                    // Cerrar la ventana de login
                    JFrame login_frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                    login_frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas");
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
}

