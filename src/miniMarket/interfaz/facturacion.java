package miniMarket.interfaz;

import miniMarket.interfaz.clases.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class facturacion extends JFrame {
    public JPanel mainPanel4;
    private JTextField usuario;
    private JLabel img1;
    private JLabel mensaje;
    private JButton notaVenta;
    private JTextField cedula;
    private JTextField correo;
    private JTextField pago;
    private JTextField fecha;
    private JTextField direccion;
    private Usuario cashier;

    public facturacion() {
        this.cashier = cashier;

        // Configuración de la interfaz (JTextField, JLabel, JButton, etc.)
        usuario = new JTextField(20);
        img1 = new JLabel();
        mensaje = new JLabel("Generar Nota de Venta");
        notaVenta = new JButton("Generar Nota de Venta");
        cedula = new JTextField(20);
        correo = new JTextField(20);
        pago = new JTextField(20);
        fecha = new JTextField(20);
        direccion = new JTextField(20);

        // Configuración de los iconos de las imágenes
        ImageIcon icon = new ImageIcon("src/channels4_profile.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH));
        img1.setIcon(icon);

        // Añadir los componentes a la ventana
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(img1);
        add(mensaje);
        add(new JLabel("Usuario:"));
        add(usuario);
        add(new JLabel("Cédula:"));
        add(cedula);
        add(new JLabel("Correo:"));
        add(correo);
        add(new JLabel("Método de Pago:"));
        add(pago);
        add(new JLabel("Fecha:"));
        add(fecha);
        add(new JLabel("Dirección:"));
        add(direccion);
        add(notaVenta);

        // Configuración del botón de generar nota de venta
        notaVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = usuario.getText();
                String id = cedula.getText();
                String email = correo.getText();
                String address = direccion.getText();
                String paymentMethod = pago.getText();
                String date = fecha.getText();

                double total = calculateTotal(paymentMethod);
                String invoiceDetails = generateInvoice(name, id, email, address, paymentMethod, total);
                saveInvoice(invoiceDetails);
                JOptionPane.showMessageDialog(facturacion.this, "Nota de venta generada.");
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private double calculateTotal(String paymentMethod) {
        double total = 0.0;
        // Calcular el total de la transacción actual
        // ...
        if (paymentMethod.equalsIgnoreCase("tarjeta")) {
            total *= 1.10; // 10% extra por pago con tarjeta
        }
        return total;
    }

    private String generateInvoice(String name, String id, String email, String address, String paymentMethod, double total) {
        StringBuilder invoice = new StringBuilder();
        invoice.append("Nombre: ").append(name).append("\n");
        invoice.append("Cédula: ").append(id).append("\n");
        invoice.append("Email: ").append(email).append("\n");
        invoice.append("Dirección: ").append(address).append("\n");
        invoice.append("Método de Pago: ").append(paymentMethod).append("\n");
        invoice.append("Total: ").append(total).append("\n");
        invoice.append("Cajero: ").append(cashier.getUsername()).append("\n");
        return invoice.toString();
    }

    private void saveInvoice(String invoiceDetails) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("nota_de_venta.pdf"));
            document.open();
            document.add(new Paragraph(invoiceDetails));
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new facturacion().setVisible(true));
    }
}
