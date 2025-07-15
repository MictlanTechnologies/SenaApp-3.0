package org.SenApp.gui;

import javax.swing.*;
import java.awt.*;

public class EjercicioInteractivoPanel extends JPanel {
    private final JFrame frame;
    private final CardLayout cards;
    private final JPanel container;

    public EjercicioInteractivoPanel(JFrame frame, CardLayout cards, JPanel container) {
        this.frame = frame;
        this.cards = cards;
        this.container = container;

        setLayout(new BorderLayout());
        setBackground(Color.decode("#F0F4FA"));

        // LOGO
        ImageIcon logo = new ImageIcon(getClass().getResource("/recursosGraficos/logo.png"));
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(logoLabel, BorderLayout.NORTH);

        // TÍTULO
        JLabel title = new JLabel("🎯 Ejercicios Interactivos", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(Color.decode("#223C77"));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(title, BorderLayout.CENTER);

        // BOTONES
        JButton start = new JButton("▶ Comenzar");
        JButton back = new JButton("⏪ Regresar");

        for (JButton b : new JButton[]{start, back}) {
            b.setBackground(Color.decode("#3A61B1"));
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setFont(new Font("Segoe UI", Font.BOLD, 14));
            b.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        }

        JPanel bottom = new JPanel(new FlowLayout());
        bottom.setBackground(Color.decode("#F0F4FA"));
        bottom.add(start);
        bottom.add(back);
        add(bottom, BorderLayout.SOUTH);

        // Acciones
        start.addActionListener(e -> iniciar());
        back.addActionListener(e -> cards.show(container, "extras"));
    }

    private void iniciar() {
        UIManager.put("OptionPane.background", Color.decode("#F0F4FA"));
        UIManager.put("Panel.background", Color.decode("#F0F4FA"));
        UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("OptionPane.buttonFont", new Font("Segoe UI", Font.BOLD, 13));

        ImageIcon icono = new ImageIcon(getClass().getResource("/recursosGraficos/logo.png"));

        String[][] ejercicios = {
                {"¿Qué letra representa esta descripción? 'Mano cerrada, con el pulgar al costado'", "a) A\nb) B\nc) C", "a"},
                {"¿Cuál de estas señas representa 'Gracias'?", "a) Mano cerrada al pecho\nb) Mano extendida desde la boca hacia afuera\nc) Mano en la frente", "b"},
                {"¿Qué seña representa una emoción positiva?", "a) Mano abierta en la frente\nb) Puños cerrados\nc) Sonrisa con mano en el pecho", "c"}
        };
        int puntuacion = 0;
        for (String[] e : ejercicios) {
            String resp = JOptionPane.showInputDialog(
                    frame,
                    e[0] + "\n" + e[1],
                    "Ejercicio",
                    JOptionPane.QUESTION_MESSAGE,
                    icono,
                    null,
                    null
            ).toString();
            if (resp == null) return;
            if (resp.trim().equalsIgnoreCase(e[2])) {
                puntuacion++;
            }
        }

        JOptionPane.showMessageDialog(
                frame,
                "Puntuación: " + puntuacion + "/" + ejercicios.length,
                "Resultado",
                JOptionPane.INFORMATION_MESSAGE,
                icono
        );
    }
}
