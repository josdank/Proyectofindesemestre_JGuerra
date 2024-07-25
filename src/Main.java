import javax.swing.*;
import miniMarket.interfaz.login;
import miniMarket.interfaz.transaccion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("login    ");
        frame.setContentPane(new transaccion().mainPanel2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Cerrar la ventana de login
        /*JFrame login_frame = (JFrame) SwingUtilities.getWindowAncestor(login);
        login_frame.dispose();*/
    }
}