import javax.swing.*;

import miniMarket.interfaz.login;
import miniMarket.interfaz.transaccion;
import miniMarket.interfaz.admin.actividad;
import miniMarket.interfaz.facturacion;
import miniMarket.interfaz.admin.cajerosCreacion;

public class Main {
    public static void main(String[] args) {
        // Crear la ventana de login
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setContentPane(new cajerosCreacion().mainPanel6);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);

    }
}
