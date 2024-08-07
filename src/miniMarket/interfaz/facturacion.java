package miniMarket.interfaz;

import miniMarket.interfaz.admin.actividad;
import miniMarket.interfaz.clases.DatabaseConnection;
import miniMarket.interfaz.clases.Usuario;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class facturacion extends JFrame {
    public JPanel mainPanel4;
    private JTextField usuario;
    private JLabel img1;
    private JLabel mensaje;
    private JButton notaVenta;
    private JTextField cedula;
    private JTextField correo;
    private JTextField fecha;
    private JTextField direccion;
    private JComboBox<String> comboBox1;
    private JLabel img2;
    private JButton volver;
    private Usuario cashier;
    private List<String> productosVendidos;
    private double precioTotal;

    public facturacion(List<String> productosVendidos, double precioTotal) {
        this.productosVendidos = productosVendidos;
        this.precioTotal = precioTotal;

        ImageIcon icon = new ImageIcon("src/channels4_profile.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH));
        img1.setIcon(icon);

        icon = new ImageIcon("src/channels4_profile.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH));
        img2.setIcon(icon);

        // Configuración del botón notaVenta
        notaVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    try {
                        double total = precioTotal;
                        String metodoPago = (String) comboBox1.getSelectedItem();
                        if (metodoPago.equals("Tarjeta")) {
                            total *= 1.10; // Añadir 10% al total
                        }
                        String pdfPath = generarPDF(total);
                        String xmlPath = generarXML();
                        boolean emailSent = enviarCorreo(correo.getText(), pdfPath, xmlPath);
                        guardarEnBaseDeDatos(pdfPath, xmlPath);

                        if (emailSent) {
                            JOptionPane.showMessageDialog(facturacion.this, "El correo se ha enviado correctamente.");
                        } else {
                            JOptionPane.showMessageDialog(facturacion.this, "No se ha podido enviar la información, asegúrese de que se insertó bien el correo electrónico.");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(facturacion.this, "Complete todos los campos para la facturación.");
                }
            }
        });

        setContentPane(mainPanel4);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Actividades");
                frame.setContentPane(new login().mainPanel5);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                JFrame facturacion_frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel4);
                facturacion_frame.dispose();
            }
        });
    }

    private boolean validarCampos() {
        if (usuario.getText().isEmpty() || cedula.getText().isEmpty() || correo.getText().isEmpty() ||
                fecha.getText().isEmpty() || direccion.getText().isEmpty()) {
            return false;
        } else {
            if (!cedula.getText().matches("\\d{10}")) {
                JOptionPane.showMessageDialog(this, "La cédula debe tener 10 dígitos.");
                cedula.setBorder(BorderFactory.createLineBorder(Color.RED));
                return false;
            }
            if (!correo.getText().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                JOptionPane.showMessageDialog(this, "El correo electrónico no tiene un formato válido.");
                correo.setBorder(BorderFactory.createLineBorder(Color.RED));
                return false;
            }
            if (comboBox1.getSelectedItem() == null || comboBox1.getSelectedItem().toString().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un método de pago.");
                comboBox1.setBorder(BorderFactory.createLineBorder(Color.RED));
                return false;
            }
            return true;
        }
    }

    private String generarPDF(double total) throws DocumentException, IOException {
        Document document = new Document();
        String fileName = "src/facturas/Factura_" + UUID.randomUUID() + ".pdf";
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();

        // Información de la factura
        document.add(new Paragraph("MiniMarket SafJos", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
        document.add(new Paragraph("Dirección Matriz: Ladrón de Guevara E11-253 y Andalucía", FontFactory.getFont(FontFactory.HELVETICA, 10)));
        document.add(new Paragraph("Contribuyente especial Nro: 1308", FontFactory.getFont(FontFactory.HELVETICA, 10)));
        document.add(new Paragraph("Obligado a llevar contabilidad: Sí", FontFactory.getFont(FontFactory.HELVETICA, 10)));

        // Espacio
        document.add(new Paragraph("\n"));

        // Información del cliente
        document.add(new Paragraph("Razón Social/Nombres y Apellidos: " + usuario.getText(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
        document.add(new Paragraph("Cédula/RUC: " + cedula.getText(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
        document.add(new Paragraph("Fecha: " + fecha.getText(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
        document.add(new Paragraph("Correo Electrónico: " + correo.getText(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
        document.add(new Paragraph("Dirección: " + direccion.getText(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
        document.add(new Paragraph("Forma de Pago: " + comboBox1.getSelectedItem().toString(), FontFactory.getFont(FontFactory.HELVETICA, 10)));

        // Espacio
        document.add(new Paragraph("\n"));

        // Detalle de productos
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{2, 3, 1, 2, 2});

        addTableHeader(table, "Código");
        addTableHeader(table, "Descripción");
        addTableHeader(table, "Cantidad");
        addTableHeader(table, "Precio Unitario");
        addTableHeader(table, "Precio Total");

        for (String producto : productosVendidos) {
            String[] parts = producto.split(": ");
            addTableCell(table, parts[0]);  // Código
            addTableCell(table, parts[1]);  // Descripción
            addTableCell(table, "1");       // Cantidad
            addTableCell(table, parts[2]);  // Precio Unitario
            addTableCell(table, parts[2]);  // Precio Total
        }

        document.add(table);

        // Total
        document.add(new Paragraph("Valor Total: $" + total, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));

        document.close();
        return fileName;
    }

    private void addTableHeader(PdfPTable table, String headerTitle) {
        PdfPCell header = new PdfPCell();
        header.setBorderWidth(2);
        header.setPhrase(new Phrase(headerTitle));
        table.addCell(header);
    }

    private void addTableCell(PdfPTable table, String cellValue) {
        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Phrase(cellValue));
        table.addCell(cell);
    }

    private String generarXML() throws ParserConfigurationException, TransformerException, FileNotFoundException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        org.w3c.dom.Document doc = docBuilder.newDocument();
        org.w3c.dom.Element rootElement = doc.createElement("factura");
        doc.appendChild(rootElement);

        org.w3c.dom.Element nombre = doc.createElement("nombre");
        nombre.appendChild(doc.createTextNode(usuario.getText()));
        rootElement.appendChild(nombre);

        org.w3c.dom.Element direccionElem = doc.createElement("direccion");
        direccionElem.appendChild(doc.createTextNode(direccion.getText()));
        rootElement.appendChild(direccionElem);

        org.w3c.dom.Element cedulaElem = doc.createElement("cedula");
        cedulaElem.appendChild(doc.createTextNode(cedula.getText()));
        rootElement.appendChild(cedulaElem);

        org.w3c.dom.Element correoElem = doc.createElement("correo");
        correoElem.appendChild(doc.createTextNode(correo.getText()));
        rootElement.appendChild(correoElem);

        org.w3c.dom.Element fechaElem = doc.createElement("fecha");
        fechaElem.appendChild(doc.createTextNode(fecha.getText()));
        rootElement.appendChild(fechaElem);

        org.w3c.dom.Element productosElem = doc.createElement("productos");
        for (String producto : productosVendidos) {
            org.w3c.dom.Element productoElem = doc.createElement("producto");
            productoElem.appendChild(doc.createTextNode(producto));
            productosElem.appendChild(productoElem);
        }
        rootElement.appendChild(productosElem);

        org.w3c.dom.Element totalElem = doc.createElement("total");
        totalElem.appendChild(doc.createTextNode(String.valueOf(precioTotal)));
        rootElement.appendChild(totalElem);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        String fileName = "src/facturas/Factura_" + UUID.randomUUID() + ".xml";
        StreamResult result = new StreamResult(new FileOutputStream(fileName));

        transformer.transform(source, result);
        return fileName;
    }

    private boolean enviarCorreo(String destinatario, String pdfPath, String xmlPath) {
        String remitente = "guerralovatojosue@hotmail.com";
        String clave = "swordart12";  // Cambia esto a tu clave de Hotmail
        String asunto = "Factura de compra";
        String mensaje = "Agradecemos su compra en miniMarket SafJos,\nAdjunto encontrará la factura de su compra.\n esperamos tenerlo de vuelta por nuestras instalaciones 😊👍🎇";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.office365.com");
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
            BodyPart adjuntoPDF = new MimeBodyPart();
            adjuntoPDF.setDataHandler(new DataHandler(new FileDataSource(pdfPath)));
            adjuntoPDF.setFileName("Factura.pdf");
            BodyPart adjuntoXML = new MimeBodyPart();
            adjuntoXML.setDataHandler(new DataHandler(new FileDataSource(xmlPath)));
            adjuntoXML.setFileName("Factura.xml");
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjuntoPDF);
            multiParte.addBodyPart(adjuntoXML);
            message.setContent(multiParte);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.office365.com", remitente, clave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        } catch (MessagingException me) {
            me.printStackTrace();
            return false;
        }
    }

    private void guardarEnBaseDeDatos(String pdfPath, String xmlPath) {
        String query = "INSERT INTO facturas (usuario, cedula, correo, fecha, direccion, metodo_pago, pdf_path, xml_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario.getText());
            stmt.setString(2, cedula.getText());
            stmt.setString(3, correo.getText());
            stmt.setString(4, fecha.getText());
            stmt.setString(5, direccion.getText());
            stmt.setString(6, (String) comboBox1.getSelectedItem());
            stmt.setString(7, pdfPath);
            stmt.setString(8, xmlPath);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            facturacion frame = new facturacion(new ArrayList<>(), 0.0);
            frame.setContentPane(frame.mainPanel4);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
