package org.SenApp.lecciones;

import org.SenApp.SistemaReportes.Reportes;
import org.SenApp.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class JuegoTexto {
    public static Usuario usuario;

    public static void iniciar() {
        JFrame frame = new JFrame("🎮 Juego de Texto: Adivina la Seña");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 350);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#F0F4FA"));
        panel.setLayout(new BorderLayout(20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel titulo = new JLabel("¡Adivina la Seña!", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(Color.decode("#0F1B3D"));
        panel.add(titulo, BorderLayout.NORTH);

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        outputArea.setBackground(Color.decode("#F0F4FA"));
        JScrollPane scroll = new JScrollPane(outputArea);
        panel.add(scroll, BorderLayout.CENTER);

        JButton comenzar = new JButton("Comenzar");
        comenzar.setBackground(Color.decode("#3A61B1"));
        comenzar.setForeground(Color.WHITE);
        comenzar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panel.add(comenzar, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

        comenzar.addActionListener(e -> {
            String[][] preguntas = {
                    {"¿Cuál es la seña correcta para 'Hola'?", "saludar"},
                    {"¿Cómo se dice 'Gracias' en señas?", "mano boca"},
                    {"¿Cuál es la seña de 'Familia'?", "circulo f"}
            };

            int puntuacion = 0;

            for (String[] pregunta : preguntas) {
                String entrada = JOptionPane.showInputDialog(frame, pregunta[0] + "\nIngresa una palabra clave:");
                if (entrada == null) return;
                entrada = entrada.trim().toLowerCase();

                if (entrada.contains(pregunta[1])) {
                    outputArea.append("✅ Correcto: " + pregunta[0] + "\n");
                    puntuacion++;
                } else {
                    outputArea.append("❌ Incorrecto: debía incluir '" + pregunta[1] + "'\n");
                }
            }

            outputArea.append("\n🎯 Puntuación final: " + puntuacion + "/" + preguntas.length + "\n");
            Reportes.guardarProgreso(usuario, "Juego Texto", puntuacion, preguntas.length);
        });
    }
}
