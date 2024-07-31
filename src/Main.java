import javax.swing.*;

import miniMarket.interfaz.login;
import miniMarket.interfaz.transaccion;
import miniMarket.interfaz.admin.actividad;
import miniMarket.interfaz.facturacion;
import miniMarket.interfaz.admin.cajerosCreacion;
import miniMarket.interfaz.admin.stock;

public class Main {
    public static void main(String[] args) {
        // Crear la ventana de login
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setContentPane(new login().mainPanel);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);

    }
}
