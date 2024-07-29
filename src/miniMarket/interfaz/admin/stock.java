package miniMarket.interfaz.admin;

import miniMarket.interfaz.clases.DatabaseConnection;
import miniMarket.interfaz.clases.Producto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class stock extends JFrame {
    public JPanel mainPanel2;
    private JLabel huevos;
    private JLabel leche;
    private JLabel fideos;
    private JLabel azucar;
    private JLabel pan;
    private JLabel arroz;
    private JLabel embutidos;
    private JLabel vino;
    private JButton volver;
    private JButton enviarButton;
    private JButton aniadirStockButton;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;

    public stock() {
        // Configuración de la interfaz gráfica
        // Cargar imágenes y ajustar iconos
        ImageIcon icon = new ImageIcon("src/huevos.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        huevos.setIcon(icon);

        icon = new ImageIcon("src/leche.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        leche.setIcon(icon);

        icon = new ImageIcon("src/fideos.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        fideos.setIcon(icon);

        icon = new ImageIcon("src/azucar.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        azucar.setIcon(icon);

        icon = new ImageIcon("src/pan.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        pan.setIcon(icon);

        icon = new ImageIcon("src/arroz.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        arroz.setIcon(icon);

        icon = new ImageIcon("src/embutidos.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        embutidos.setIcon(icon);

        icon = new ImageIcon("src/vino.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        vino.setIcon(icon);

        // Acción para el botón añadirStockButton
        aniadirStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(stock.this, "Actualizar stock");
            }
        });

        // Configuración de botones adicionales (aún sin funcionalidad específica)
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para button1
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para button2
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para button3
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para button4
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para button5
            }
        });
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para button6
            }
        });
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para button7
            }
        });
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para button8
            }
        });
    }

    // Método para obtener producto por ID
    private Producto getProductById(String productId) throws SQLException {
        Producto product = null;
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM productos WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int stock = resultSet.getInt("stock");
                    double price = resultSet.getDouble("price");
                    product = new Producto(name, stock, price);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }
}
