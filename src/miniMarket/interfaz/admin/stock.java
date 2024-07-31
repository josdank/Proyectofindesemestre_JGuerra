package miniMarket.interfaz.admin;

import miniMarket.DatabaseConnection;
import miniMarket.Producto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class stock extends JFrame {
    private JTextField productIdField;
    private JTextField productNameField;
    private JTextField productStockField;
    private JTextField productPriceField;
    private JButton searchButton;
    private JButton updateButton;
    private JButton backButton;
    private JPanel mainPanel2;
    private JLabel huevos;
    private JLabel leche;
    private JLabel fideos;
    private JLabel azucar;
    private JLabel pan;
    private JLabel arroz;
    private JLabel embutidos;
    private JLabel vino;
    private JButton añadirStockButton;
    private JButton[] productButtons;
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
        mainPanel2 = new JPanel();
        mainPanel2.setLayout(new GridLayout(3, 3)); // Ajusta según la disposición deseada

        // Inicializar JTextField y JButton
        productIdField = new JTextField();
        productNameField = new JTextField();
        productStockField = new JTextField();
        productPriceField = new JTextField();
        searchButton = new JButton("Buscar");
        updateButton = new JButton("Actualizar");
        backButton = new JButton("Volver");
        añadirStockButton = new JButton("Añadir Stock");

        // Configuración de etiquetas de producto y botones
        huevos = new JLabel();
        leche = new JLabel();
        fideos = new JLabel();
        azucar = new JLabel();
        pan = new JLabel();
        arroz = new JLabel();
        embutidos = new JLabel();
        vino = new JLabel();

        productButtons = new JButton[8];
        for (int i = 0; i < 8; i++) {
            productButtons[i] = new JButton("Producto " + (i + 1));
        }

        // Agregar los componentes al panel
        mainPanel2.add(huevos);
        mainPanel2.add(leche);
        mainPanel2.add(fideos);
        mainPanel2.add(azucar);
        mainPanel2.add(pan);
        mainPanel2.add(arroz);
        mainPanel2.add(embutidos);
        mainPanel2.add(vino);

        for (JButton button : productButtons) {
            mainPanel2.add(button);
        }
        mainPanel2.add(searchButton);
        mainPanel2.add(updateButton);
        mainPanel2.add(backButton);
        mainPanel2.add(añadirStockButton);

        add(mainPanel2);

        // Configuración de imágenes
        setImageForLabel(huevos, "src/huevos.jpg");
        setImageForLabel(leche, "src/leche.jpg");
        setImageForLabel(fideos, "src/fideos.jpg");
        setImageForLabel(azucar, "src/azucar.jpg");
        setImageForLabel(pan, "src/pan.jpg");
        setImageForLabel(arroz, "src/arroz.jpg");
        setImageForLabel(embutidos, "src/embutidos.jpg");
        setImageForLabel(vino, "src/vino.jpg");

        // Acciones de los botones
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productId = productIdField.getText();
                Producto product = null;
                try {
                    product = getProductById(productId);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (product != null) {
                    productNameField.setText(product.getName());
                    productStockField.setText(String.valueOf(product.getStock()));
                    productPriceField.setText(String.valueOf(product.getPrice()));
                } else {
                    JOptionPane.showMessageDialog(stock.this, "Producto no encontrado.");
                }
            }
        });

        // Puedes agregar más ActionListener para otros botones aquí, si es necesario

        // Configuración de JFrame
        setTitle("Gestión de Stock");
        setSize(800, 600); // Ajusta el tamaño según tus necesidades
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void setImageForLabel(JLabel label, String path) {
        ImageIcon icon = new ImageIcon(path);
        icon = new ImageIcon(icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        label.setIcon(icon);
    }

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
