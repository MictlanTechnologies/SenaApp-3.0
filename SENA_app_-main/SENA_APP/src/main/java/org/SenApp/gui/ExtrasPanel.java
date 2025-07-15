package org.SenApp.gui;

import javax.swing.*;
import java.awt.*;

public class ExtrasPanel extends JPanel {
    public ExtrasPanel(JFrame frame, CardLayout cards, JPanel container) {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#F0F4FA"));

        // LOGO
        ImageIcon logo = new ImageIcon(getClass().getResource("/recursosGraficos/logo.png"));
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(logoLabel, BorderLayout.NORTH);

        // TÍTULO
        JLabel title = new JLabel("🎮 Opciones Extras", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(Color.decode("#223C77"));
        title.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(title, BorderLayout.CENTER);

        // PANEL DE BOTONES
        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new BoxLayout(botonesPanel, BoxLayout.Y_AXIS));
        botonesPanel.setBackground(Color.decode("#F0F4FA"));
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        JButton ejercicios = new JButton("📘 Ejercicios Interactivos");
        JButton juego = new JButton("🧠 Juego de Texto");
        JButton cuestionario = new JButton("📝 Cuestionario");
        JButton back = new JButton("⬅ Regresar");

        for (JButton btn : new JButton[]{ejercicios, juego, cuestionario, back}) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setBackground(Color.decode("#3A61B1"));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
            btn.setMaximumSize(new Dimension(300, 40));
            btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            botonesPanel.add(Box.createVerticalStrut(10));
            botonesPanel.add(btn);
        }

        // Acciones
        ejercicios.addActionListener(e -> cards.show(container, "ejercicio"));
        juego.addActionListener(e -> cards.show(container, "juego"));
        cuestionario.addActionListener(e -> cards.show(container, "cuestionario"));
        back.addActionListener(e -> cards.show(container, "lecciones"));

        add(botonesPanel, BorderLayout.SOUTH);
    }
}
