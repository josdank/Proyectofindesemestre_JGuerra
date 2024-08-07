import javax.swing.*;

import miniMarket.interfaz.admin.actividad;
import miniMarket.interfaz.login;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import Sonido.AudioPlayer;

public class Main {
    private static AudioPlayer audioPlayer = new AudioPlayer();

    public static void main(String[] args) {
        // Reproducir sonido al iniciar
        audioPlayer.playSound("ruta/al/archivo/inicio.wav");

        // Crear la ventana de login
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setContentPane(new login().mainPanel5);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);

        // Agregar un listener para reproducir un sonido al cerrar la ventana
        loginFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                audioPlayer.playSound("ruta/al/archivo/salida.wav");
            }
        });
    }
}

