package miniMarket.interfaz.admin;

import miniMarket.DatabaseConnection;
import miniMarket.estilos.estilos;

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
    private JButton añadirStockButton;
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
    private JButton productos;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button15;
    private JButton button16;

    private int[] cantidades = new int[8];

    public stock() {
        // Configuración de imágenes
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

        inicializarCantidades();

        // Aplica estilos
        estilos.aplicarColorDeFondo(mainPanel2);
        estilos.aplicarEstilos(huevos);
        estilos.aplicarEstilos(leche);
        estilos.aplicarEstilos(fideos);
        estilos.aplicarEstilos(azucar);
        estilos.aplicarEstilos(pan);
        estilos.aplicarEstilos(arroz);
        estilos.aplicarEstilos(embutidos);
        estilos.aplicarEstilos(vino);


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

        // Acción del botón añadirStockButton
        añadirStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    actualizarStockEnBaseDeDatos();
                    JOptionPane.showMessageDialog(stock.this, "Stock actualizado exitosamente.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(stock.this, "Error al actualizar el stock en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        // Acción del botón productos
        productos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarNuevoProducto();
            }
        });
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
        // Código para inicializar las cantidades desde la base de datos
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM productos";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                int cantidad = resultSet.getInt("cantidad");
                switch (nombre.toLowerCase()) {
                    case "huevos":
                        cantidades[0] = cantidad;
                        cantidad1.setText(String.valueOf(cantidad));
                        break;
                    case "leche":
                        cantidades[1] = cantidad;
                        cantidad2.setText(String.valueOf(cantidad));
                        break;
                    case "fideos":
                        cantidades[2] = cantidad;
                        cantidad3.setText(String.valueOf(cantidad));
                        break;
                    case "azucar":
                        cantidades[3] = cantidad;
                        cantidad4.setText(String.valueOf(cantidad));
                        break;
                    case "pan":
                        cantidades[4] = cantidad;
                        cantidad5.setText(String.valueOf(cantidad));
                        break;
                    case "arroz":
                        cantidades[5] = cantidad;
                        cantidad6.setText(String.valueOf(cantidad));
                        break;
                    case "embutidos":
                        cantidades[6] = cantidad;
                        cantidad7.setText(String.valueOf(cantidad));
                        break;
                    case "vino":
                        cantidades[7] = cantidad;
                        cantidad8.setText(String.valueOf(cantidad));
                        break;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void actualizarStockEnBaseDeDatos() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "UPDATE productos SET cantidad = ? WHERE nombre = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < 8; i++) {
                preparedStatement.setInt(1, cantidades[i]);
                preparedStatement.setString(2, getNombreProducto(i));
                preparedStatement.executeUpdate();
            }
        }
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

    private void agregarNuevoProducto() {
        JTextField nombreField = new JTextField();
        JTextField precioField = new JTextField();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccione una imagen");

        int result = JOptionPane.showConfirmDialog(null, new Object[]{
                "Nombre:", nombreField,
                "Precio:", precioField,
                "Imagen:", fileChooser
        }, "Agregar Nuevo Producto", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String nombre = nombreField.getText();
            String precio = precioField.getText();
            String imagenPath = fileChooser.getSelectedFile().getAbsolutePath();

            // Guardar nuevo producto en la base de datos
            try {
                Connection connection = DatabaseConnection.getConnection();
                String query = "INSERT INTO productos (nombre, precio, imagen) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, nombre);
                preparedStatement.setString(2, precio);
                preparedStatement.setString(3, imagenPath);
                preparedStatement.executeUpdate();

                JOptionPane.showMessageDialog(this, "Producto agregado exitosamente.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al agregar el producto en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    public void setVisible(boolean b) {
        JFrame frame = new JFrame("Crear Cajero");
        frame.setContentPane(mainPanel2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(b);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new stock().setVisible(true));
    }
}
