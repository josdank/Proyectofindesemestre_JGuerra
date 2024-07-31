package miniMarket.estilos;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class estilos {
    public static void aplicarEstilos(JPanel panel) {
        panel.setBorder(new LineBorder(Color.BLACK, 1));
    }

    public static void aplicarEstilos(JLabel label) {
        label.setBorder(new LineBorder(Color.BLACK, 1));
    }
}
