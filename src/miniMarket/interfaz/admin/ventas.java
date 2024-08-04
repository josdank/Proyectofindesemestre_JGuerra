package miniMarket.interfaz.admin;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ventas {
    public JPanel mainPanel3;
    private JButton revisar;
    private JLabel img1;
    private JLabel img2;
    private JTextField ncajero;
    private JLabel cajero;
    private JTable table1;

    public ventas() {

        ImageIcon icon = new ImageIcon("src/channels4_profile.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH));
        img1.setIcon(icon);

        icon = new ImageIcon("src/channels4_profile.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH));
        img2.setIcon(icon);

        revisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Usuario = "Josueso";
                String correoUsuario = "josue.guerra@epn.edu.ec";
                String contraUsuario = "159687Js";

                String inputUsuario = ncajero.getText();

                if ((inputUsuario.equals(Usuario) || inputUsuario.equals(correoUsuario))) {
                    JFrame frame = new JFrame("Biografía");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                    // Cerrar la ventana de login
                    JFrame login_frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel3);
                    login_frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas");
                }
            }
        });


    }

    public void setVisible(boolean b) {
        JFrame frame = new JFrame("Crear Cajero");
        frame.setContentPane(mainPanel3);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(b);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ventas().setVisible(true));
    }
}

