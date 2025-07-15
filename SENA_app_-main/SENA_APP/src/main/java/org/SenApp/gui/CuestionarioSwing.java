package org.SenApp.gui;

import org.SenApp.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class CuestionarioSwing {
    private final JFrame parent;

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

    public CuestionarioSwing(JFrame parent) {
        this.parent = parent;
    }

    public void iniciar(Usuario usuario) {
        UIManager.put("OptionPane.background", Color.decode("#F0F4FA"));
        UIManager.put("Panel.background", Color.decode("#F0F4FA"));
        UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("OptionPane.buttonFont", new Font("Segoe UI", Font.BOLD, 13));

        // Si tienes un icono de logo puedes cargarlo aquí como ícono del JOptionPane
        ImageIcon logo = new ImageIcon(getClass().getResource("/resources/logo.png"));

        int puntuacion = 0;
        for (int i = 0; i < PREGUNTAS.length; i++) {
            Object sel = JOptionPane.showInputDialog(
                    parent,
                    PREGUNTAS[i],
                    "Pregunta " + (i + 1),
                    JOptionPane.QUESTION_MESSAGE,
                    logo,
                    OPCIONES[i],
                    OPCIONES[i][0]
            );
            if (sel == null) return;
            if (sel.equals(OPCIONES[i][RESPUESTAS[i]])) {
                puntuacion++;
            }
        }

        JOptionPane.showMessageDialog(
                parent,
                "Tu puntuación final: " + puntuacion + "/" + PREGUNTAS.length,
                "Resultado",
                JOptionPane.INFORMATION_MESSAGE,
                logo
        );
    }
}
