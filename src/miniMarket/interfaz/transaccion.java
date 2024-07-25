package miniMarket.interfaz;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class transaccion {
    public JPanel mainPanel2;
    private JLabel huevos;
    private JLabel leche;
    private JLabel fideos;
    private JLabel azucar;
    private JButton volver;
    private JButton enviarButton;
    private JLabel pan;
    private JLabel arroz;
    private JLabel embutidos;
    private JLabel vino;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;


    public transaccion() {
        ImageIcon icon = new ImageIcon("src/huevos.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
        huevos.setIcon(icon);

        icon = new ImageIcon("src/leche.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
        leche.setIcon(icon);

        icon = new ImageIcon("src/fideos.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
        fideos.setIcon(icon);

        icon = new ImageIcon("src/azucar.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
        azucar.setIcon(icon);

        icon = new ImageIcon("src/pan.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
        pan.setIcon(icon);

        icon = new ImageIcon("src/arroz.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
        arroz.setIcon(icon);

        icon = new ImageIcon("src/embutidos.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
        embutidos.setIcon(icon);

        icon = new ImageIcon("src/vino.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
        vino.setIcon(icon);
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
                button2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
                button3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
                button4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
                button5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
                button6.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
                button7.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
                button8.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
            }
        });
    }
}
