package miniMarket.interfaz.admin;

import miniMarket.interfaz.clases.DatabaseConnection;
import miniMarket.estilos.estilos;

import javax.swing.*;
import java.awt.*;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    private JButton volver;

    private int[] cantidades = new int[8];
    private List<JLabel> dynamicProductLabels = new ArrayList<>();
    private List<JLabel> dynamicQuantityLabels = new ArrayList<>();
    private List<JButton> dynamicIncrementButtons = new ArrayList<>();
    private List<JButton> dynamicDecrementButtons = new ArrayList<>();

    public stock() {
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
        inicializarComponentesDinamicos();

        // Aplica estilos
        estilos.aplicarColorDeFondo(mainPanel2);
        aplicarEstilosComponentesEstaticos();

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
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Actividades");
                frame.setContentPane(new actividad().mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                JFrame stock_frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel2);
                stock_frame.dispose();
            }
        });
    }

    private void inicializarComponentesDinamicos() {
        // Implementar la lógica para inicializar componentes dinámicos desde la base de datos
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM productos WHERE nombre NOT IN ('huevos', 'leche', 'fideos', 'azucar', 'pan', 'arroz', 'embutidos', 'vino')";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            int row = 3; // Starting row for dynamic components
            int col = 0; // Starting column for dynamic components

            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                int cantidad = resultSet.getInt("cantidad");
                double precio = resultSet.getDouble("precio");
                String imagen = resultSet.getString("imagen");

                JLabel nameLabel = new JLabel(nombre + " - $" + precio);
                GridConstraints nameConstraints = new GridConstraints(row, col, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false);
                mainPanel2.add(nameLabel, nameConstraints);

                JLabel productLabel = new JLabel();
                setLabelImage(productLabel, imagen);
                GridConstraints productConstraints = new GridConstraints(row + 1, col, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false);
                mainPanel2.add(productLabel, productConstraints);
                estilos.aplicarEstilos(productLabel);

                JButton incrementButton = new JButton("+");
                GridConstraints incrementConstraints = new GridConstraints(row + 2, col, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false);
                mainPanel2.add(incrementButton, incrementConstraints);


                JButton decrementButton = new JButton("-");
                GridConstraints decrementConstraints = new GridConstraints(row + 2, col, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false);
                mainPanel2.add(decrementButton, decrementConstraints);


                JLabel quantityLabel = new JLabel(String.valueOf(cantidad));
                GridConstraints quantityConstraints = new GridConstraints(row + 2, col, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false);
                mainPanel2.add(quantityLabel, quantityConstraints);


                incrementButton.addActionListener(e -> incrementarCantidadDinamica(quantityLabel, nombre));
                decrementButton.addActionListener(e -> decrementarCantidadDinamica(quantityLabel, nombre));

                dynamicProductLabels.add(productLabel);
                dynamicQuantityLabels.add(quantityLabel);
                dynamicIncrementButtons.add(incrementButton);
                dynamicDecrementButtons.add(decrementButton);

                col++;
                if (col == 4) { // Assuming 4 columns per row
                    col = 4;
                    row += 1; // Move to the next row, keeping space between groups
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void aplicarEstilosComponentesEstaticos() {
        estilos.aplicarEstilos(huevos);
        estilos.aplicarEstilos(leche);
        estilos.aplicarEstilos(fideos);
        estilos.aplicarEstilos(azucar);
        estilos.aplicarEstilos(pan);
        estilos.aplicarEstilos(arroz);
        estilos.aplicarEstilos(embutidos);
        estilos.aplicarEstilos(vino);
        estilos.aplicarEstilos2(productos);
        estilos.aplicarEstilos2(añadirStockButton);
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

    private void incrementarCantidadDinamica(JLabel cantidadLabel, String nombre) {
        int cantidad = Integer.parseInt(cantidadLabel.getText());
        cantidad++;
        cantidadLabel.setText(String.valueOf(cantidad));
    }

    private void decrementarCantidadDinamica(JLabel cantidadLabel, String nombre) {
        int cantidad = Integer.parseInt(cantidadLabel.getText());
        if (cantidad > 0) {
            cantidad--;
            cantidadLabel.setText(String.valueOf(cantidad));
        }
    }

    private void inicializarCantidades() {
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

    private void actualizarStockEnBaseDeDatos() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String querySelect = "SELECT cantidad, precio FROM stock WHERE nombre = ?";
        String queryUpdate = "UPDATE stock SET cantidad = ?, precio = ? WHERE nombre = ?";
        String queryInsert = "INSERT INTO stock (nombre, cantidad, precio) VALUES (?, ?, ?)";

        try (PreparedStatement selectStmt = connection.prepareStatement(querySelect);
             PreparedStatement updateStmt = connection.prepareStatement(queryUpdate);
             PreparedStatement insertStmt = connection.prepareStatement(queryInsert)) {
            for (int i = 0; i < 8; i++) {
                selectStmt.setString(1, getNombreProducto(i));
                ResultSet resultSet = selectStmt.executeQuery();
                if (resultSet.next()) {
                    int cantidadActual = resultSet.getInt("cantidad");
                    double precioActual = resultSet.getDouble("precio");
                    int nuevaCantidad = cantidadActual + cantidades[i];

                    updateStmt.setInt(1, nuevaCantidad);
                    updateStmt.setDouble(2, precioActual);
                    updateStmt.setString(3, getNombreProducto(i));
                    updateStmt.executeUpdate();
                } else {
                    insertStmt.setString(1, getNombreProducto(i));
                    insertStmt.setInt(2, cantidades[i]);
                    insertStmt.setDouble(3, obtenerPrecioProducto(i)); // Obtener el precio del producto
                    insertStmt.executeUpdate();
                }
            }
        }
    }

    private double obtenerPrecioProducto(int index) {
        switch (index) {
            case 0:
                return 1.50; // Precio de huevos
            case 1:
                return 0.90; // Precio de leche
            case 2:
                return 1.20; // Precio de fideos
            case 3:
                return 0.80; // Precio de azucar
            case 4:
                return 0.50; // Precio de pan
            case 5:
                return 0.60; // Precio de arroz
            case 6:
                return 2.00; // Precio de embutidos
            case 7:
                return 5.00; // Precio de vino
            default:
                return 0.0;
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
        JTextField cantidadField = new JTextField();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccione una imagen");

        int result = JOptionPane.showConfirmDialog(null, new Object[]{
                "Nombre:", nombreField,
                "Precio:", precioField,
                "Cantidad:", cantidadField,
                "Imagen:", fileChooser
        }, "Agregar Nuevo Producto", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String nombre = nombreField.getText();
            String precio = precioField.getText();
            String cantidad = cantidadField.getText();
            String imagenPath = fileChooser.getSelectedFile().getAbsolutePath();

            // Copiar la imagen seleccionada a la carpeta src/images del proyecto
            try {
                Path source = fileChooser.getSelectedFile().toPath();
                Path destination = Paths.get("src/" + fileChooser.getSelectedFile().getName());
                Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                imagenPath = destination.toString();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            // Guardar nuevo producto en la base de datos
            try {
                Connection connection = DatabaseConnection.getConnection();
                String query = "INSERT INTO productos (nombre, precio, imagen, cantidad) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, nombre);
                preparedStatement.setString(2, precio);
                preparedStatement.setString(3, imagenPath);
                preparedStatement.setInt(4, Integer.parseInt(cantidad));

                preparedStatement.executeUpdate();

                // Insertar en la tabla stock
                String stockQuery = "INSERT INTO stock (nombre, cantidad, precio) VALUES (?, ?, ?)";
                PreparedStatement stockStatement = connection.prepareStatement(stockQuery);
                stockStatement.setString(1, nombre);
                stockStatement.setInt(2, Integer.parseInt(cantidad));
                stockStatement.setString(3, precio);
                stockStatement.executeUpdate();

                JOptionPane.showMessageDialog(this, "Producto agregado exitosamente.");

                // Añadir el nuevo producto a la interfaz
                añadirProductoInterfaz(nombre, Double.parseDouble(precio), imagenPath, Integer.parseInt(cantidad));

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al agregar el producto en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    private int row = 3; // Starting row for dynamic components
    private int col = 0; // Starting column for dynamic components

    private void añadirProductoInterfaz(String nombre, double precio, String imagen, int cantidad) {
        JLabel nameLabel = new JLabel(nombre + " - $" + precio);
        GridConstraints nameConstraints = new GridConstraints(row, col, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false);
        mainPanel2.add(nameLabel, nameConstraints);

        JLabel productLabel = new JLabel();
        setLabelImage(productLabel, imagen);
        GridConstraints productConstraints = new GridConstraints(row + 1, col, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false);
        mainPanel2.add(productLabel, productConstraints);
        estilos.aplicarEstilos(productLabel);

        JButton incrementButton = new JButton("+");
        GridConstraints incrementConstraints = new GridConstraints(row + 2, col, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false);
        mainPanel2.add(incrementButton, incrementConstraints);

        JButton decrementButton = new JButton("-");
        GridConstraints decrementConstraints = new GridConstraints(row + 2, col, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false);
        mainPanel2.add(decrementButton, decrementConstraints);

        JLabel quantityLabel = new JLabel(String.valueOf(cantidad));
        GridConstraints quantityConstraints = new GridConstraints(row + 3, col, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false);
        mainPanel2.add(quantityLabel, quantityConstraints);

        incrementButton.addActionListener(e -> incrementarCantidadDinamica(quantityLabel, nombre));
        decrementButton.addActionListener(e -> decrementarCantidadDinamica(quantityLabel, nombre));

        dynamicProductLabels.add(productLabel);
        dynamicQuantityLabels.add(quantityLabel);
        dynamicIncrementButtons.add(incrementButton);
        dynamicDecrementButtons.add(decrementButton);

        row++;
        if (row == 1) { // Assuming 1 column per row
            row = 0;
            col = (col == 0) ? 1 : 0; // Toggle row between 0 and 1
        }
    }


    public void setVisible(boolean b) {
        JFrame frame = new JFrame("Stock");
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
