package org.SenApp.lecciones;

import javax.swing.*;
import java.awt.*;

public class Cuestionario extends JPanel {
    private static final String[] preguntas = {
            "¿Cuál es la seña para 'Hola'?",
            "¿Cómo se representa la letra 'A' en el alfabeto manual?",
            "¿Qué seña se usa para 'Gracias'?"
    };

    private static final String[][] opciones = {
            {"Mano en el pecho", "Mano abierta saludando", "Dedos cruzados"},
            {"Mano cerrada con pulgar al lado", "Dos dedos arriba", "Mano en forma de C"},
            {"Mano cerrada tocando el pecho", "Mano abierta desde la boca hacia adelante", "Índice señalando al corazón"}
    };

    private static final int[] respuestasCorrectas = {1, 0, 1}; // Índices base 0

    private static JFrame frame;

    public Cuestionario(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(Color.decode("#F0F4FA"));

        JLabel titulo = new JLabel("🧠 Cuestionario Interactivo", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(Color.decode("#0F1B3D"));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        JButton btnIniciar = new JButton("▶ Comenzar");
        styleButton(btnIniciar);

        btnIniciar.addActionListener(e -> iniciar());

        JPanel center = new JPanel();
        center.setBackground(getBackground());
        center.add(btnIniciar);

        add(titulo, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
    }

    public static void iniciar() {
        int puntuacion = 0;
        for (int i = 0; i < preguntas.length; i++) {
            Object seleccion = JOptionPane.showInputDialog(
                    frame,
                    preguntas[i],
                    "Pregunta " + (i + 1),
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones[i],
                    opciones[i][0]
            );
            if (seleccion == null) return;
            if (seleccion.equals(opciones[i][respuestasCorrectas[i]])) {
                puntuacion++;
            }
        }

        JOptionPane.showMessageDialog(
                frame,
                "🎯 Tu puntuación final: " + puntuacion + "/" + preguntas.length,
                "Resultado",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void styleButton(JButton b) {
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI", Font.BOLD, 16));
        b.setBackground(Color.decode("#3A61B1"));
        b.setForeground(Color.WHITE);
        b.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }
}
