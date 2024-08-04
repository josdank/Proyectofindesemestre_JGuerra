import javax.swing.*;

import miniMarket.interfaz.transaccion;

public class Main {
    public static void main(String[] args) {
        // Crear la ventana de login
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setContentPane(new transaccion().mainPanel6);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);

    }
}
