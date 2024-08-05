package miniMarket.interfaz;

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
import java.util.HashMap;
import java.util.Map;

public class transaccion extends JFrame {
    private Object cashier;
    public JPanel mainPanel6;
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
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;

    private int[] cantidades = new int[8];
    private Map<Integer, Integer> stock = new HashMap<>();

    public transaccion(Object cashier) {
        this.cashier = cashier;
        ImageIcon icon = new ImageIcon("src/huevos.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH));
        huevos.setIcon(icon);

        icon = new ImageIcon("src/leche.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH));
        leche.setIcon(icon);

        icon = new ImageIcon("src/fideos.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH));
        fideos.setIcon(icon);

        icon = new ImageIcon("src/azucar.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH));
        azucar.setIcon(icon);

        icon = new ImageIcon("src/pan.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH));
        pan.setIcon(icon);

        icon = new ImageIcon("src/arroz.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH));
        arroz.setIcon(icon);

        icon = new ImageIcon("src/embutidos.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH));
        embutidos.setIcon(icon);

        icon = new ImageIcon("src/vino.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH));
        vino.setIcon(icon);

        // Inicializar el stock desde la base de datos
        inicializarStock();

        // Acciones de incremento
        button1.addActionListener(e -> incrementarCantidad(0, textField1));
        button2.addActionListener(e -> incrementarCantidad(1, textField2));
        button3.addActionListener(e -> incrementarCantidad(2, textField3));
        button4.addActionListener(e -> incrementarCantidad(3, textField4));
        button5.addActionListener(e -> incrementarCantidad(4, textField5));
        button6.addActionListener(e -> incrementarCantidad(5, textField6));
        button7.addActionListener(e -> incrementarCantidad(6, textField7));
        button8.addActionListener(e -> incrementarCantidad(7, textField8));

        // Acciones de decremento
        button9.addActionListener(e -> decrementarCantidad(0, textField1));
        button10.addActionListener(e -> decrementarCantidad(1, textField2));
        button11.addActionListener(e -> decrementarCantidad(2, textField3));
        button12.addActionListener(e -> decrementarCantidad(3, textField4));
        button13.addActionListener(e -> decrementarCantidad(4, textField5));
        button14.addActionListener(e -> decrementarCantidad(5, textField6));
        button15.addActionListener(e -> decrementarCantidad(6, textField7));
        button16.addActionListener(e -> decrementarCantidad(7, textField8));

        // Acción del botón enviarButton
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int total = calcularTotal();
                JOptionPane.showMessageDialog(transaccion.this, "Cantidad total: " + total);
                int option = JOptionPane.showConfirmDialog(transaccion.this, "¿Desea hacer la factura?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    guardarVenta();
                    JOptionPane.showMessageDialog(transaccion.this, "Factura realizada con éxito.");
                }
            }
        });

        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login().setVisible(true);
                dispose();
            }
        });
    }

    public transaccion() {

    }

    private void inicializarStock() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM stock";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            int index = 0;
            while (resultSet.next()) {
                stock.put(index, resultSet.getInt("cantidad"));
                index++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    private void incrementarCantidad(int index, JTextField textField) {
        if (cantidades[index] < stock.get(index)) {
            cantidades[index]++;
            textField.setText(String.valueOf(cantidades[index]));
        } else {
            JOptionPane.showMessageDialog(this, "Lo sentimos, se agotó el stock de este producto.");
        }
    }

    private void decrementarCantidad(int index, JTextField textField) {
        if (cantidades[index] > 0) {
            cantidades[index]--;
            textField.setText(String.valueOf(cantidades[index]));
        }
    }

    private int calcularTotal() {
        int total = 0;
        for (int cantidad : cantidades) {
            total += cantidad; // Aquí debes agregar el precio de cada producto multiplicado por la cantidad
        }
        return total;
    }

    private void guardarVenta() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO ventas (precio_total, productos_vendidos, precio_por_producto, cajero_id) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, calcularTotal());
            preparedStatement.setString(2, obtenerProductosVendidos());
            preparedStatement.setString(3, obtenerPreciosPorProducto());
            preparedStatement.setObject(4, cashier);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private String obtenerProductosVendidos() {
        StringBuilder productos = new StringBuilder();
        for (int i = 0; i < cantidades.length; i++) {
            if (cantidades[i] > 0) {
                productos.append(getNombreProducto(i)).append(": ").append(cantidades[i]).append(", ");
            }
        }
        return productos.toString();
    }

    private String obtenerPreciosPorProducto() {
        // Aquí debes agregar el cálculo real de los precios por producto
        return "precios por producto";
    }

    private String getNombreProducto(int index) {
        switch (index) {
            case 0:
                return "huevos";
            case 1:
                return "leche";
            case 2:
                return "fideos";
            case 3:
                return "azucar";
            case 4:
                return "pan";
            case 5:
                return "arroz";
            case 6:
                return "embutidos";
            case 7:
                return "vino";
            default:
                return "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new transaccion(null).setVisible(true));
    }

    public void setVisible(boolean b) {
        JFrame frame = new JFrame("Transacción");
        frame.setContentPane(mainPanel6);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(b);
    }
}
