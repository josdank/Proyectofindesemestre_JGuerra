package miniMarket.interfaz;

import miniMarket.DatabaseConnection;
import miniMarket.Producto;
import miniMarket.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class transaccion extends JFrame {
    private Object cashier = null;
    public JPanel mainPanel2;
    private JLabel huevos;
    private JLabel leche;
    private JLabel fideos;
    private JLabel azucar;
    private JButton volver;
    private JButton enviarButton;
    private JLabel pan;
    private JLabel arroz;
    private JLabel embutidos;
    private JLabel vino;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;


    public transaccion(Usuario user) {
        this.cashier = cashier;
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
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel productIdField = null;
                String productId = productIdField.getText();
                TextComponent quantityField = null;
                int quantity = Integer.parseInt(quantityField.getText());

                Producto product = getProductById(productId);
                if (product != null) {
                    if (product.getStock() >= quantity) {
                        product.reduceStock(quantity);
                        updateProductStock(product);
                        // Añadir el producto a la transacción actual (gestionar una lista de productos)
                    } else {
                        JOptionPane.showMessageDialog(transaccion.this, "Lo sentimos, el stock de este producto se ha agotado, intente con la compra de otro producto.");
                    }
                } else {
                    JOptionPane.showMessageDialog(transaccion.this, "Producto no encontrado.");
                }
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel productIdField = null;
                String productId = productIdField.getText();
                TextComponent quantityField = null;
                int quantity = Integer.parseInt(quantityField.getText());

                Producto product = getProductById(productId);
                if (product != null) {
                    if (product.getStock() >= quantity) {
                        product.reduceStock(quantity);
                        updateProductStock(product);
                        // Añadir el producto a la transacción actual (gestionar una lista de productos)
                    } else {
                        JOptionPane.showMessageDialog(transaccion.this, "Lo sentimos, el stock de este producto se ha agotado, intente con la compra de otro producto.");
                    }
                } else {
                    JOptionPane.showMessageDialog(transaccion.this, "Producto no encontrado.");
                }
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel productIdField = null;
                String productId = productIdField.getText();
                TextComponent quantityField = null;
                int quantity = Integer.parseInt(quantityField.getText());

                Producto product = getProductById(productId);
                if (product != null) {
                    if (product.getStock() >= quantity) {
                        product.reduceStock(quantity);
                        updateProductStock(product);
                        // Añadir el producto a la transacción actual (gestionar una lista de productos)
                    } else {
                        JOptionPane.showMessageDialog(transaccion.this, "Lo sentimos, el stock de este producto se ha agotado, intente con la compra de otro producto.");
                    }
                } else {
                    JOptionPane.showMessageDialog(transaccion.this, "Producto no encontrado.");
                }
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel productIdField = null;
                String productId = productIdField.getText();
                TextComponent quantityField = null;
                int quantity = Integer.parseInt(quantityField.getText());

                Producto product = getProductById(productId);
                if (product != null) {
                    if (product.getStock() >= quantity) {
                        product.reduceStock(quantity);
                        updateProductStock(product);
                        // Añadir el producto a la transacción actual (gestionar una lista de productos)
                    } else {
                        JOptionPane.showMessageDialog(transaccion.this, "Lo sentimos, el stock de este producto se ha agotado, intente con la compra de otro producto.");
                    }
                } else {
                    JOptionPane.showMessageDialog(transaccion.this, "Producto no encontrado.");
                }
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel productIdField = null;
                String productId = productIdField.getText();
                TextComponent quantityField = null;
                int quantity = Integer.parseInt(quantityField.getText());

                Producto product = getProductById(productId);
                if (product != null) {
                    if (product.getStock() >= quantity) {
                        product.reduceStock(quantity);
                        updateProductStock(product);
                        // Añadir el producto a la transacción actual (gestionar una lista de productos)
                    } else {
                        JOptionPane.showMessageDialog(transaccion.this, "Lo sentimos, el stock de este producto se ha agotado, intente con la compra de otro producto.");
                    }
                } else {
                    JOptionPane.showMessageDialog(transaccion.this, "Producto no encontrado.");
                }
            }
        });
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel productIdField = null;
                String productId = productIdField.getText();
                TextComponent quantityField = null;
                int quantity = Integer.parseInt(quantityField.getText());

                Producto product = getProductById(productId);
                if (product != null) {
                    if (product.getStock() >= quantity) {
                        product.reduceStock(quantity);
                        updateProductStock(product);
                        // Añadir el producto a la transacción actual (gestionar una lista de productos)
                    } else {
                        JOptionPane.showMessageDialog(transaccion.this, "Lo sentimos, el stock de este producto se ha agotado, intente con la compra de otro producto.");
                    }
                } else {
                    JOptionPane.showMessageDialog(transaccion.this, "Producto no encontrado.");
                }
            }
        });
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel productIdField = null;
                String productId = productIdField.getText();
                TextComponent quantityField = null;
                int quantity = Integer.parseInt(quantityField.getText());

                Producto product = getProductById(productId);
                if (product != null) {
                    if (product.getStock() >= quantity) {
                        product.reduceStock(quantity);
                        updateProductStock(product);
                        // Añadir el producto a la transacción actual (gestionar una lista de productos)
                    } else {
                        JOptionPane.showMessageDialog(transaccion.this, "Lo sentimos, el stock de este producto se ha agotado, intente con la compra de otro producto.");
                    }
                } else {
                    JOptionPane.showMessageDialog(transaccion.this, "Producto no encontrado.");
                }
            }
        });
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel productIdField = null;
                String productId = productIdField.getText();
                TextComponent quantityField = null;
                int quantity = Integer.parseInt(quantityField.getText());

                Producto product = getProductById(productId);
                if (product != null) {
                    if (product.getStock() >= quantity) {
                        product.reduceStock(quantity);
                        updateProductStock(product);
                        // Añadir el producto a la transacción actual (gestionar una lista de productos)
                    } else {
                        JOptionPane.showMessageDialog(transaccion.this, "Lo sentimos, el stock de este producto se ha agotado, intente con la compra de otro producto.");
                    }
                } else {
                    JOptionPane.showMessageDialog(transaccion.this, "Producto no encontrado.");
                }
            }
        });

        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Realizar la facturación
                new facturacion().setVisible(true);
                dispose();
            }
        });
    }

    private Producto getProductById(String productId) {
        String query = "SELECT * FROM productos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Producto(rs.getString("id"), rs.getInt("stock"), rs.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void updateProductStock(Producto product) {
        String query = "UPDATE productos SET stock = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, product.getStock());
            stmt.setString(2, product.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new transaccion(null).setVisible(true));
    }
}

