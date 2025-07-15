package org.SenApp.lecciones;

import org.SenApp.SistemaReportes.Reportes;
import org.SenApp.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class EjercicioInteractivo extends JPanel {
    private final JFrame frame;
    private final Usuario usuario;

    private static final String[][] ejercicios = {
            {"¿Qué letra representa esta descripción? 'Mano cerrada, con el pulgar al costado'", "a) A\nb) B\nc) C", "a"},
            {"¿Cuál de estas señas representa 'Gracias'?", "a) Mano cerrada al pecho\nb) Mano extendida desde la boca hacia afuera\nc) Mano en la frente", "b"},
            {"¿Qué seña representa una emoción positiva?", "a) Mano abierta en la frente\nb) Puños cerrados\nc) Sonrisa con mano en el pecho", "c"}
    };

    public EjercicioInteractivo(JFrame frame, Usuario usuario) {
        this.frame = frame;
        this.usuario = usuario;

        setLayout(new BorderLayout());
        setBackground(Color.decode("#F0F4FA"));

        JLabel titulo = new JLabel("🧩 Ejercicios Interactivos", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(Color.decode("#0F1B3D"));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        JButton btnIniciar = new JButton("▶ Comenzar ejercicios");
        styleButton(btnIniciar);

        btnIniciar.addActionListener(e -> iniciar());

        JPanel center = new JPanel();
        center.setBackground(getBackground());
        center.add(btnIniciar);

        add(titulo, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
    }

    public static void iniciarCuestionario() {
    }

    private void iniciar() {
        int puntuacion = 0;

        for (String[] ejercicio : ejercicios) {
            String respuesta = JOptionPane.showInputDialog(
                    frame,
                    ejercicio[0] + "\n" + ejercicio[1],
                    "Ejercicio",
                    JOptionPane.QUESTION_MESSAGE
            );
            if (respuesta == null) return;
            if (respuesta.trim().equalsIgnoreCase(ejercicio[2])) {
                puntuacion++;
            }
        }

        Reportes.guardarProgreso(usuario, "Ejercicios", puntuacion, ejercicios.length);

        JOptionPane.showMessageDialog(
                frame,
                "🏁 Finalizaste los ejercicios.\nPuntuación: " + puntuacion + "/" + ejercicios.length,
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
