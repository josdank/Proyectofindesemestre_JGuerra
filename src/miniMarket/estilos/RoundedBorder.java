package miniMarket.estilos;

import javax.swing.border.Border;
import java.awt.*;

/**
 * Clase RoundedBorder que implementa un borde redondeado personalizado.
 */
public class RoundedBorder implements Border {
    private int radius;
    private Color color;

    /**
     * Constructor para crear un borde redondeado con un radio y color específicos.
     *
     * @param radius el radio de las esquinas redondeadas
     * @param color el color del borde
     */
    public RoundedBorder(int radius, Color color) {
        this.radius = radius;
        this.color = color;
    }

    /**
     * Devuelve los insets del borde.
     *
     * @param c el componente al que se aplica el borde
     * @return los insets del borde
     */
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    /**
     * Indica si el borde es opaco.
     *
     * @return true si el borde es opaco, false en caso contrario
     */
    @Override
    public boolean isBorderOpaque() {
        return true;
    }

    /**
     * Pinta el borde redondeado en el componente.
     *
     * @param c el componente en el que se pinta el borde
     * @param g el contexto gráfico utilizado para dibujar el borde
     * @param x la coordenada x del borde
     * @param y la coordenada y del borde
     * @param width el ancho del borde
     * @param height la altura del borde
     */
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(color);
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}
