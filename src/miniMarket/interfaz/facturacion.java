package miniMarket.interfaz;

import miniMarket.interfaz.clases.DatabaseConnection;
import miniMarket.interfaz.clases.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.UUID;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


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
    private JComboBox<String> comboBox1;
    private JLabel img2;
    private Usuario cashier;

    public facturacion() {
        this.cashier = cashier;

        ImageIcon icon = new ImageIcon("src/channels4_profile.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
        img1.setIcon(icon);

        ImageIcon imageIcon = new ImageIcon("src/channels4_profile.jpg");
        icon = new ImageIcon(imageIcon.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
        img2.setIcon(icon);

        // Configuración del botón notaVenta
        notaVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    try {
                        double total = Double.parseDouble(pago.getText());
                        String metodoPago = (String) comboBox1.getSelectedItem();
                        if (metodoPago.equals("Tarjeta")) {
                            total *= 1.10; // Añadir 10% al total
                        }
                        generarPDF(total);
                        enviarCorreo(correo.getText());
                        guardarEnBaseDeDatos();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(facturacion.this, "Complete todos los campos para la facturación.");
                }
            }
        });
    }

    private boolean validarCampos() {
        if (usuario.getText().isEmpty() || cedula.getText().isEmpty() || correo.getText().isEmpty() ||
                pago.getText().isEmpty() || fecha.getText().isEmpty() || direccion.getText().isEmpty()) {
            return false;
        }
        if (!cedula.getText().matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "La cédula debe tener 10 dígitos.");
            return false;
        }
        if (!correo.getText().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            JOptionPane.showMessageDialog(this, "El correo electrónico no tiene un formato válido.");
            return false;
        }
        return true;
    }

    private void generarPDF(double total) throws DocumentException, IOException {
        Document document = new Document();
        String fileName = "Factura_" + UUID.randomUUID() + ".pdf";
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();
        document.add(new Paragraph("Pedido"));
        document.add(new Paragraph("Fecha: " + fecha.getText()));
        document.add(new Paragraph("Nombre: " + usuario.getText()));
        document.add(new Paragraph("Dirección: " + direccion.getText()));
        document.add(new Paragraph("Cédula: " + cedula.getText()));
        document.add(new Paragraph("Correo: " + correo.getText()));
        document.add(new Paragraph("Descripción: Compra de productos"));
        document.add(new Paragraph("Total: " + total));
        document.close();
    }

    private void enviarCorreo(String destinatario) {
        String remitente = "tu_correo@gmail.com";
        String clave = "tu_contraseña";
        String asunto = "Factura de compra";
        String mensaje = "Adjunto encontrará la factura de su compra.";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", clave);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(asunto);
            BodyPart texto = new MimeBodyPart();
            texto.setText(mensaje);
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource("Factura.pdf")));
            adjunto.setFileName("Factura.pdf");
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);
            message.setContent(multiParte);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, clave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }

    private void guardarEnBaseDeDatos() {
        String query = "INSERT INTO facturas (usuario, cedula, correo, pago, fecha, direccion, metodo_pago) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario.getText());
            stmt.setString(2, cedula.getText());
            stmt.setString(3, correo.getText());
            stmt.setString(4, pago.getText());
            stmt.setString(5, fecha.getText());
            stmt.setString(6, direccion.getText());
            stmt.setString(7, (String) comboBox1.getSelectedItem());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            facturacion frame = new facturacion();
            frame.setContentPane(frame.mainPanel4);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
