package miniMarket.interfaz.admin;

import miniMarket.interfaz.clases.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class ventas extends JFrame {
    public JPanel mainPanel3;
    private JButton revisar;
    private JLabel img1;
    private JLabel img2;
    private JTextField ncajero;
    private JLabel cajero;
    private JTable table1;
    private JButton volver;

    public ventas() {
        // Configuración de los iconos de las imágenes
        ImageIcon icon = new ImageIcon("src/channels4_profile.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
        img1.setIcon(icon);

        icon = new ImageIcon("src/channels4_profile.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
        img2.setIcon(icon);

        // Configuración inicial de la ventana y sus componentes
        setContentPane(mainPanel3);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setSize(800, 600); // Ajustar el tamaño de la ventana

        revisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cajeroInput = ncajero.getText().trim();
                if (!cajeroInput.isEmpty()) {
                    buscarCajeroYVentas(cajeroInput);
                } else {
                    JOptionPane.showMessageDialog(ventas.this, "Por favor, ingrese el nombre completo o usuario del cajero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Actividad");
                frame.setContentPane(new actividad().mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                JFrame hobbies_frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel3);
                hobbies_frame.dispose();
            }
        });
    }

    private void buscarCajeroYVentas(String cajeroInput) {
        Connection connection = null;
        PreparedStatement cajeroStmt = null;
        PreparedStatement ventasStmt = null;
        ResultSet cajeroRs = null;
        ResultSet ventasRs = null;

        try {
            connection = DatabaseConnection.getConnection();

            // Buscar información del cajero por nombre_completo o usuario
            String cajeroQuery = "SELECT * FROM cajeros WHERE nombre_completo = ? OR usuario = ?";
            cajeroStmt = connection.prepareStatement(cajeroQuery);
            cajeroStmt.setString(1, cajeroInput);
            cajeroStmt.setString(2, cajeroInput);
            cajeroRs = cajeroStmt.executeQuery();

            if (cajeroRs.next()) {
                String cajeroInfo = "<html>ID: " + cajeroRs.getString("id") +
                        "<br>Nombre: " + cajeroRs.getString("nombre_completo") +
                        "<br>Usuario: " + cajeroRs.getString("usuario") + "</html>";
                cajero.setText(cajeroInfo);

                // Buscar ventas realizadas por el cajero
                String ventasQuery = "SELECT * FROM ventas WHERE cajero_id = ?";
                ventasStmt = connection.prepareStatement(ventasQuery);
                ventasStmt.setString(1, cajeroRs.getString("id"));
                ventasRs = ventasStmt.executeQuery();

                // Crear un modelo de tabla para las ventas
                DefaultTableModel model = new DefaultTableModel(new String[]{"Precio Total", "Productos Vendidos", "Precio por Producto", "Fecha"}, 0);

                boolean hasVentas = false;
                while (ventasRs.next()) {
                    hasVentas = true;
                    String precioTotal = ventasRs.getString("precio_total");
                    String productosVendidos = ventasRs.getString("productos_vendidos");
                    String precioPorProducto = ventasRs.getString("precio_por_producto");
                    String fecha = ventasRs.getString("fecha");
                    model.addRow(new Object[]{precioTotal, productosVendidos, precioPorProducto, fecha});
                }

                if (!hasVentas) {
                    model.addRow(new Object[]{"No hay ventas registradas para este cajero", "", "", ""});
                }

                table1.setModel(model);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró un cajero con el nombre completo o usuario especificado.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al buscar información del cajero y sus ventas.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (cajeroRs != null) cajeroRs.close();
                if (ventasRs != null) ventasRs.close();
                if (cajeroStmt != null) cajeroStmt.close();
                if (ventasStmt != null) ventasStmt.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ventas().setVisible(true));
    }
}
