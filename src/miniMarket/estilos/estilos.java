package miniMarket.estilos;

import javax.swing.*;
import java.awt.*;

public class estilos {
    public static void aplicarEstilos(JPanel panel) {
        panel.setBorder(new RoundedBorder(15, Color.BLACK));
    }

    public static void aplicarEstilos(JLabel label) {
        label.setBorder(new RoundedBorder(15, Color.BLACK));
    }

    public static void aplicarColorDeFondo(JPanel panel) {
        panel.setBackground(new Color(230, 230, 250));
    }
}
