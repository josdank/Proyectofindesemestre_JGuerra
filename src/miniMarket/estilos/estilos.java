package miniMarket.estilos;

import javax.swing.*;
import java.awt.*;

/**
 * Clase estilos que proporciona métodos para aplicar estilos personalizados a componentes de Swing.
 */
public class estilos {

    /**
     * Aplica un borde redondeado a un botón.
     *
     * @param panel el botón al que se aplicará el borde
     */
    public static void aplicarEstilos(JButton panel) {
        panel.setBorder(new RoundedBorder(15, Color.BLACK));
    }

    /**
     * Aplica un borde redondeado a una etiqueta.
     *
     * @param label la etiqueta a la que se aplicará el borde
     */
    public static void aplicarEstilos(JLabel label) {
        label.setBorder(new RoundedBorder(15, Color.BLACK));
    }

    /**
     * Aplica un borde redondeado de menor tamaño a un botón.
     *
     * @param panel el botón al que se aplicará el borde
     */
    public static void aplicarEstilos2(JButton panel) {
        panel.setBorder(new RoundedBorder(8, Color.BLACK));
    }

    /**
     * Aplica un borde redondeado de menor tamaño a un campo de texto.
     *
     * @param panel el campo de texto al que se aplicará el borde
     */
    public static void aplicarEstilos1(JTextField panel) {
        panel.setBorder(new RoundedBorder(8, Color.BLACK));
    }

    /**
     * Aplica un borde redondeado de menor tamaño a un botón de radio.
     *
     * @param panel el botón de radio al que se aplicará el borde
     */
    public static void aplicarEstilos3(JRadioButton panel) {
        panel.setBorder(new RoundedBorder(8, Color.BLACK));
    }

    /**
     * Aplica un color de fondo personalizado a un panel.
     *
     * @param panel el panel al que se aplicará el color de fondo
     */
    public static void aplicarColorDeFondo(JPanel panel) {
        panel.setBackground(new Color(230, 230, 250));
    }
}
