package org.SenApp.lecciones;

import javax.swing.*;
import java.awt.*;

public class ModuloIntermedio {

    public static void mostrarLecciones() {
        JFrame frame = new JFrame("📙 Módulos Intermedios");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBackground(Color.decode("#F0F4FA"));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel title = new JLabel("Selecciona una lección intermedia", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.decode("#0F1B3D"));
        panel.add(title, BorderLayout.NORTH);

        JPanel botones = new JPanel(new GridLayout(0, 1, 15, 15));
        botones.setBackground(panel.getBackground());

        JButton btn1 = crearBoton("💬 Expresiones Cotidianas");
        JButton btn2 = crearBoton("👪 Familia y Relaciones");
        JButton btn3 = crearBoton("😊 Emociones y Estados de Ánimo");
        JButton btnSalir = crearBoton("⬅️ Regresar");

        botones.add(btn1);
        botones.add(btn2);
        botones.add(btn3);
        botones.add(btnSalir);

        panel.add(botones, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);

        btn1.addActionListener(e -> mostrarMensaje("Expresiones Cotidianas: Frases como 'necesito ayuda', '¿dónde está el baño?', 'vamos'."));
        btn2.addActionListener(e -> mostrarMensaje("Familia: Aprende señas para mamá, papá, hermano, abuela, etc."));
        btn3.addActionListener(e -> mostrarMensaje("Emociones: Aprende a expresar alegría, enojo, tristeza, miedo."));
        btnSalir.addActionListener(e -> frame.dispose());
    }

    private static void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private static JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(Color.decode("#223C77")); // azul medio
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }
}
