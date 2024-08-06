package miniMarket.interfaz;

import miniMarket.interfaz.clases.DatabaseConnection;
import miniMarket.estilos.estilos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class transaccion extends JFrame {
    public JPanel mainPanel6;
    private JLabel huevos;
    private JLabel leche;
    private JLabel fideos;
    private JLabel azucar;
    private JLabel pan;
    private JLabel arroz;
    private JLabel embutidos;
    private JLabel vino;
    private JButton compra;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JScrollBar scrollBar1;
    private JLabel cantidad1;
    private JLabel cantidad2;
    private JLabel cantidad3;
    private JLabel cantidad4;
    private JLabel cantidad5;
    private JLabel cantidad6;
    private JLabel cantidad7;
    private JLabel cantidad8;
    private JButton volver;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button15;
    private JButton button16;

    private int[] cantidades = new int[8];
    private List<String> productosVendidos = new ArrayList<>();
    private double precioTotal = 0.0;
    private int cajeroId;

    public transaccion(int cajeroId) {
        this.cajeroId = cajeroId;

        // Configuración de imágenes
        setLabelImage(huevos, "src/huevos.jpg");
        setLabelImage(leche, "src/leche.jpg");
        setLabelImage(fideos, "src/fideos.jpg");
        setLabelImage(azucar, "src/azucar.jpg");
        setLabelImage(pan, "src/pan.jpg");
        setLabelImage(arroz, "src/arroz.jpg");
        setLabelImage(embutidos, "src/embutidos.jpg");
        setLabelImage(vino, "src/vino.jpg");

        inicializarCantidades();

        // Aplica estilos
        estilos.aplicarColorDeFondo(mainPanel6);
        estilos.aplicarEstilos(huevos);
        estilos.aplicarEstilos(leche);
        estilos.aplicarEstilos(fideos);
        estilos.aplicarEstilos(azucar);
        estilos.aplicarEstilos(pan);
        estilos.aplicarEstilos(arroz);
        estilos.aplicarEstilos(embutidos);
        estilos.aplicarEstilos(vino);
        estilos.aplicarEstilos2(volver);
        estilos.aplicarEstilos2(compra);

        // Acciones de incremento
        button1.addActionListener(e -> incrementarCantidad(0, cantidad1));
        button2.addActionListener(e -> incrementarCantidad(1, cantidad2));
        button3.addActionListener(e -> incrementarCantidad(2, cantidad3));
        button4.addActionListener(e -> incrementarCantidad(3, cantidad4));
        button5.addActionListener(e -> incrementarCantidad(4, cantidad5));
        button6.addActionListener(e -> incrementarCantidad(5, cantidad6));
        button7.addActionListener(e -> incrementarCantidad(6, cantidad7));
        button8.addActionListener(e -> incrementarCantidad(7, cantidad8));

        // Acciones de decremento
        button9.addActionListener(e -> decrementarCantidad(0, cantidad1));
        button10.addActionListener(e -> decrementarCantidad(1, cantidad2));
        button11.addActionListener(e -> decrementarCantidad(2, cantidad3));
        button12.addActionListener(e -> decrementarCantidad(3, cantidad4));
        button13.addActionListener(e -> decrementarCantidad(4, cantidad5));
        button14.addActionListener(e -> decrementarCantidad(5, cantidad6));
        button15.addActionListener(e -> decrementarCantidad(6, cantidad7));
        button16.addActionListener(e -> decrementarCantidad(7, cantidad8));

        // Acción del botón añadir compra en la base de datos
        compra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    realizarCompra();
                    JOptionPane.showMessageDialog(transaccion.this, "Compra realizada con éxito.");
                    // Redirigir a la facturación
                    redirigirAFacturacion();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(transaccion.this, "Error al realizar la compra en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        // Acción del botón volver
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Login");
                frame.setContentPane(new login().mainPanel5);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                JFrame login_frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel6);
                login_frame.dispose();
            }
        });
    }

    public transaccion() {
        // Constructor vacío necesario para crear un nuevo JFrame sin pasar el ID del cajero
    }

    private void setLabelImage(JLabel label, String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        icon = new ImageIcon(icon.getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH));
        label.setIcon(icon);
    }

    private void incrementarCantidad(int index, JLabel cantidadLabel) {
        cantidades[index]++;
        cantidadLabel.setText(String.valueOf(cantidades[index]));
    }

    private void decrementarCantidad(int index, JLabel cantidadLabel) {
        if (cantidades[index] > 0) {
            cantidades[index]--;
            cantidadLabel.setText(String.valueOf(cantidades[index]));
        }
    }

    private void inicializarCantidades() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM stock";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                int cantidad = resultSet.getInt("cantidad");
                switch (nombre.toLowerCase()) {
                    case "huevos":
                        cantidades[0] = cantidad;
                        cantidad1.setText(String.valueOf(cantidades[0]));
                        break;
                    case "leche":
                        cantidades[1] = cantidad;
                        cantidad2.setText(String.valueOf(cantidades[1]));
                        break;
                    case "fideos":
                        cantidades[2] = cantidad;
                        cantidad3.setText(String.valueOf(cantidades[2]));
                        break;
                    case "azucar":
                        cantidades[3] = cantidad;
                        cantidad4.setText(String.valueOf(cantidades[3]));
                        break;
                    case "pan":
                        cantidades[4] = cantidad;
                        cantidad5.setText(String.valueOf(cantidades[4]));
                        break;
                    case "arroz":
                        cantidades[5] = cantidad;
                        cantidad6.setText(String.valueOf(cantidades[5]));
                        break;
                    case "embutidos":
                        cantidades[6] = cantidad;
                        cantidad7.setText(String.valueOf(cantidades[6]));
                        break;
                    case "vino":
                        cantidades[7] = cantidad;
                        cantidad8.setText(String.valueOf(cantidades[7]));
                        break;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void realizarCompra() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String queryUpdate = "UPDATE stock SET cantidad = ? WHERE nombre = ?";
        String querySelect = "SELECT cantidad, precio FROM stock WHERE nombre = ?";
        PreparedStatement updateStmt = connection.prepareStatement(queryUpdate);
        PreparedStatement selectStmt = connection.prepareStatement(querySelect);

        boolean stockAgotado = false;

        for (int i = 0; i < 8; i++) {
            if (cantidades[i] > 0) {
                selectStmt.setString(1, getNombreProducto(i));
                ResultSet resultSet = selectStmt.executeQuery();

                if (resultSet.next()) {
                    int stockActual = resultSet.getInt("cantidad");
                    double precio = resultSet.getDouble("precio");
                    if (stockActual >= cantidades[i]) {
                        int nuevoStock = stockActual - cantidades[i];
                        updateStmt.setInt(1, nuevoStock);
                        updateStmt.setString(2, getNombreProducto(i));
                        updateStmt.executeUpdate();

                        productosVendidos.add(getNombreProducto(i) + ": " + cantidades[i]);
                        precioTotal += precio * cantidades[i];
                    } else {
                        stockAgotado = true;
                        JOptionPane.showMessageDialog(this, "Lo sentimos, no disponemos de " + getNombreProducto(i) + " en estos momentos, el stock se ha agotado.", "Stock Agotado", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
        if (!productosVendidos.isEmpty()) {
            guardarVenta();
        }
    }

    private void guardarVenta() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "INSERT INTO ventas (precio_total, productos_vendidos, precio_por_producto, cajero_id) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setDouble(1, precioTotal);
        preparedStatement.setString(2, String.join(", ", productosVendidos));
        preparedStatement.setString(3, obtenerPreciosPorProducto());
        preparedStatement.setInt(4, cajeroId);

        preparedStatement.executeUpdate();
    }

    private String obtenerPreciosPorProducto() {
        StringBuilder precios = new StringBuilder();
        for (String producto : productosVendidos) {
            String[] partes = producto.split(": ");
            precios.append(partes[0]).append(": ").append(obtenerPrecioProducto(partes[0])).append(", ");
        }
        return precios.toString();
    }

    private double obtenerPrecioProducto(String nombreProducto) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT precio FROM stock WHERE nombre = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nombreProducto);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("precio");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0.0;
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

    private void redirigirAFacturacion() {
        JFrame frame = new JFrame("Facturación");
        frame.setContentPane(new facturacion().mainPanel4);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JFrame transaccion_frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel6);
        transaccion_frame.dispose();
    }

    public void setVisible(boolean b) {
        JFrame frame = new JFrame("Transaccion");
        frame.setContentPane(mainPanel6);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(b);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new transaccion(1).setVisible(true)); // El ID del cajero debe ser pasado aquí
    }
}
