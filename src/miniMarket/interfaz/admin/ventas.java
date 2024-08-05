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
                String cajeroId = ncajero.getText().trim();
                if (!cajeroId.isEmpty()) {
                    buscarCajeroYVentas(cajeroId);
                } else {
                    JOptionPane.showMessageDialog(ventas.this, "Por favor, ingrese el ID del cajero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void buscarCajeroYVentas(String cajeroId) {
        Connection connection = null;
        PreparedStatement cajeroStmt = null;
        PreparedStatement ventasStmt = null;
        ResultSet cajeroRs = null;
        ResultSet ventasRs = null;

        try {
            connection = DatabaseConnection.getConnection();

            // Buscar información del cajero
            String cajeroQuery = "SELECT * FROM cajeros WHERE usuario = ?";
            cajeroStmt = connection.prepareStatement(cajeroQuery);
            cajeroStmt.setString(1, cajeroId);
            cajeroRs = cajeroStmt.executeQuery();

            if (cajeroRs.next()) {
                String cajeroInfo = "ID: " + cajeroRs.getString("id") + ", Nombre: " + cajeroRs.getString("nombre");
                cajero.setText(cajeroInfo);

                // Buscar ventas realizadas por el cajero
                String ventasQuery = "SELECT * FROM ventas WHERE cajero_id = ?";
                ventasStmt = connection.prepareStatement(ventasQuery);
                ventasStmt.setString(1, cajeroId);
                ventasRs = ventasStmt.executeQuery();

                // Crear un modelo de tabla para las ventas
                DefaultTableModel model = new DefaultTableModel(new String[]{"Precio Total", "Productos Vendidos", "Precio por Producto", "Fecha"}, 0);

                while (ventasRs.next()) {
                    String precioTotal = ventasRs.getString("precio_total");
                    String productosVendidos = ventasRs.getString("productos_vendidos");
                    String precioPorProducto = ventasRs.getString("precio_por_producto");
                    String fecha = ventasRs.getString("fecha");
                    model.addRow(new Object[]{precioTotal, productosVendidos, precioPorProducto, fecha});
                }

                table1.setModel(model);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró un cajero con el ID especificado.", "Error", JOptionPane.ERROR_MESSAGE);
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
