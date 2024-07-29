import javax.swing.*;

import  miniMarket.interfaz.login;


public class Main {
    public static void main(String[] args) {

        System.out.println("Hola Mundo");
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new login().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}