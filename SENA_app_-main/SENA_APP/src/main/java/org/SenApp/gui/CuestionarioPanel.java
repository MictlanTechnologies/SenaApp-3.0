package org.SenApp.gui;

import javax.swing.*;
import java.awt.*;

public class CuestionarioPanel extends JPanel {
    private final JFrame frame;
    private final CardLayout cards;
    private final JPanel container;

    private static final String[] PREGUNTAS = {
            "¿Cuál es la seña para 'Hola'?",
            "¿Cómo se representa la letra 'A' en el alfabeto manual?",
            "¿Qué seña se usa para 'Gracias'?"
    };

    private static final String[][] OPCIONES = {
            {"Mano en el pecho", "Mano abierta saludando", "Dedos cruzados"},
            {"Mano cerrada con pulgar al lado", "Dos dedos arriba", "Mano en forma de C"},
            {"Mano cerrada tocando el pecho", "Mano abierta desde la boca hacia adelante", "Índice señalando al corazón"}
    };

    private static final int[] RESPUESTAS = {1, 0, 1};

    public CuestionarioPanel(JFrame frame, CardLayout cards, JPanel container) {
        this.frame = frame;
        this.cards = cards;
        this.container = container;

        setLayout(new BorderLayout());
        setBackground(Color.decode("#F0F4FA"));

        // LOGO
        ImageIcon logo = new ImageIcon(getClass().getResource("/resources/logo.png"));
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(logoLabel, BorderLayout.NORTH);

        // TÍTULO
        JLabel title = new JLabel("📝 Cuestionario", SwingConstants.CENTER);
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
        int puntuacion = 0;
        for (int i = 0; i < PREGUNTAS.length; i++) {
            Object sel = JOptionPane.showInputDialog(
                    frame,
                    PREGUNTAS[i],
                    "Pregunta " + (i + 1),
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    OPCIONES[i],
                    OPCIONES[i][0]);
            if (sel == null) return;
            if (sel.equals(OPCIONES[i][RESPUESTAS[i]])) {
                puntuacion++;
            }
        }
        JOptionPane.showMessageDialog(frame,
                "Tu puntuación final: " + puntuacion + "/" + PREGUNTAS.length);
    }
}
