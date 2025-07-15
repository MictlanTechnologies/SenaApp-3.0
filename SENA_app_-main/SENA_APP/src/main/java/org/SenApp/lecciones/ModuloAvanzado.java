package org.SenApp.lecciones;

import javax.swing.*;
import java.awt.*;

public class ModuloAvanzado {

    public static void mostrarLecciones() {
        JFrame frame = new JFrame("📘 Módulos Avanzados");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBackground(Color.decode("#F0F4FA"));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel title = new JLabel("Selecciona una lección avanzada", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.decode("#0F1B3D"));
        panel.add(title, BorderLayout.NORTH);

        JPanel botones = new JPanel(new GridLayout(0, 1, 15, 15));
        botones.setBackground(panel.getBackground());

        JButton btn1 = crearBoton("💬 Conversaciones Completas");
        JButton btn2 = crearBoton("💼 Lengua de Señas en el Trabajo");
        JButton btn3 = crearBoton("🛠️ Señas Técnicas y Especializadas");
        JButton btnSalir = crearBoton("⬅️ Regresar");

        botones.add(btn1);
        botones.add(btn2);
        botones.add(btn3);
        botones.add(btnSalir);

        panel.add(botones, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);

        btn1.addActionListener(e -> mostrarMensaje("Conversaciones: Aprende a mantener diálogos más fluidos y naturales."));
        btn2.addActionListener(e -> mostrarMensaje("Trabajo: Señales útiles para reuniones, entrevistas y tareas laborales."));
        btn3.addActionListener(e -> mostrarMensaje("Técnicas: Aprende términos de áreas como medicina, tecnología, y educación."));
        btnSalir.addActionListener(e -> frame.dispose());
    }

    private static void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private static JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(Color.decode("#3A61B1"));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }
}
