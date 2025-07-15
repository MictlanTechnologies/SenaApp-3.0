package org.SenApp.gui;

import javax.swing.*;
import java.awt.*;

public class LeccionesAppPanel extends JPanel {
    public LeccionesAppPanel(JFrame frame, CardLayout cards, JPanel container) {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#F0F4FA"));

        // LOGO
        ImageIcon logo = new ImageIcon(getClass().getResource("/resources/logo.png"));
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(logoLabel, BorderLayout.NORTH);

        // TÍTULO
        JLabel title = new JLabel("📚 Módulos de Lecciones", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(Color.decode("#223C77"));
        title.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(title, BorderLayout.CENTER);

        // BOTONES
        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new BoxLayout(botonesPanel, BoxLayout.Y_AXIS));
        botonesPanel.setBackground(Color.decode("#F0F4FA"));
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        JButton basico = new JButton("🔤 Módulos Básicos");
        JButton intermedio = new JButton("📘 Módulos Intermedios");
        JButton avanzado = new JButton("📙 Módulos Avanzados");
        JButton extras = new JButton("🎮 Extras");
        JButton back = new JButton("⬅ Regresar");

        for (JButton btn : new JButton[]{basico, intermedio, avanzado, extras, back}) {
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
        basico.addActionListener(e -> cards.show(container, "basico"));
        intermedio.addActionListener(e -> cards.show(container, "intermedio"));
        avanzado.addActionListener(e -> cards.show(container, "avanzado"));
        extras.addActionListener(e -> cards.show(container, "extras"));
        back.addActionListener(e -> cards.show(container, "menu"));

        add(botonesPanel, BorderLayout.SOUTH);
    }
}
