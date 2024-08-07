package miniMarket.interfaz.admin;

import miniMarket.interfaz.login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase actividad que representa la ventana de actividades administrativas.
 */
public class actividad extends JFrame {
    public JPanel mainPanel;
    private JButton seleccion;
    private JButton volverLogin;
    private JLabel img1;
    private JLabel img2;
    private JComboBox<String> comboBox1;

    /**
     * Constructor de la clase actividad.
     * Configura los iconos, los elementos del comboBox y las acciones de los botones.
     */
    public actividad() {
        // Configuración de los iconos de las imágenes
        ImageIcon icon = new ImageIcon("src/channels4_profile.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH));
        img1.setIcon(icon);

        icon = new ImageIcon("src/channels4_profile.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH));
        img2.setIcon(icon);

        // Asegúrate de que el comboBox1 tenga los elementos necesarios
        comboBox1.addItem(" ");
        comboBox1.addItem("Crear Cajero");
        comboBox1.addItem("Aumentar Stock");
        comboBox1.addItem("Revisar ventas");

        // Acción del botón seleccionar
        seleccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedActivity = (String) comboBox1.getSelectedItem();
                switch (selectedActivity) {
                    case "Crear Cajero":
                        new cajerosCreacion().setVisible(true);
                        JFrame actividad_frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                        actividad_frame.dispose();
                        dispose();
                        break;
                    case "Aumentar Stock":
                        new stock().setVisible(true);
                        JFrame actividad1_frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                        actividad1_frame.dispose();
                        dispose();
                        break;
                    case "Revisar ventas":
                        new ventas().setVisible(true);
                        JFrame actividad2_frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                        actividad2_frame.dispose();
                        dispose();
                        break;
                    default:
                        JOptionPane.showMessageDialog(actividad.this, "Seleccione una actividad válida.");
                        break;
                }
            }
        });

        // Acción del botón volver al login
        volverLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Login");
                frame.setContentPane(new login().mainPanel5);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                JFrame ccreacion_frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                ccreacion_frame.dispose();
            }
        });

        // Configuración de la ventana
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Método principal que inicia la aplicación.
     *
     * @param args los argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new actividad().setVisible(true));
    }
}
