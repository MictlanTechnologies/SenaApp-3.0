package org.SenApp.gui;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class ModuloIntermedioPanel extends JPanel {
    private final JFrame frame;
    private final CardLayout cards;
    private final JPanel container;

    public ModuloIntermedioPanel(JFrame frame, CardLayout cards, JPanel container) {
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
        JLabel title = new JLabel("📗 Módulo Intermedio", SwingConstants.CENTER);
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

        JButton l1 = new JButton("💬 Expresiones Cotidianas");
        JButton l2 = new JButton("👨‍👩‍👧‍👦 Familia y Relaciones");
        JButton l3 = new JButton("😊 Emociones y Estados de Ánimo");
        JButton back = new JButton("⬅ Regresar");

        for (JButton btn : new JButton[]{l1, l2, l3, back}) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setBackground(Color.decode("#3A61B1"));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
            btn.setMaximumSize(new Dimension(400, 40));
            btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            botonesPanel.add(Box.createVerticalStrut(10));
            botonesPanel.add(btn);
        }

        // Acciones
        l1.addActionListener(e -> mostrarExpresiones());
        l2.addActionListener(e -> mostrarFamilia());
        l3.addActionListener(e -> mostrarEmociones());
        back.addActionListener(e -> cards.show(container, "lecciones"));

        add(botonesPanel, BorderLayout.SOUTH);
    }

    private void mostrarExpresiones() {
        Map<String, String> vids = new LinkedHashMap<>();
        vids.put("Necesito ayuda", "/recursosGraficos/Modulo Intermedio/Ayuda.mp4");
        vids.put("¿Dónde está el baño?", "/recursosGraficos/Modulo Intermedio/Bano.mp4");
        vids.put("Vamos", "/recursosGraficos/Modulo Intermedio/Vamos.mp4");
        VideoLessonPanel p = new VideoLessonPanel(frame, cards, container,
                "Expresiones Cotidianas", vids, "intermedio");
        container.add(p, "inter_exp");
        cards.show(container, "inter_exp");
    }

    private void mostrarFamilia() {
        Map<String, String> vids = new LinkedHashMap<>();
        vids.put("Abuela", "/recursosGraficos/Modulo Intermedio/Abuela.mp4");
        vids.put("Abuelo", "/recursosGraficos/Modulo Intermedio/Abuelo.mp4");
        vids.put("Esposa", "/recursosGraficos/Modulo Intermedio/Esposa.mp4");
        vids.put("Esposo", "/recursosGraficos/Modulo Intermedio/Esposo.mp4");
        vids.put("Familia", "/recursosGraficos/Modulo Intermedio/Familia.mp4");
        vids.put("Grupo", "/recursosGraficos/Modulo Intermedio/Grupo.mp4");
        vids.put("Hermana", "/recursosGraficos/Modulo Intermedio/Hermana.mp4");
        vids.put("Hermano", "/recursosGraficos/Modulo Intermedio/Hermano.mp4");
        vids.put("Hija", "/recursosGraficos/Modulo Intermedio/Hija.mp4");
        vids.put("Hijo", "/recursosGraficos/Modulo Intermedio/Hijo.mp4");
        vids.put("Mamá", "/recursosGraficos/Modulo Intermedio/Mama.mp4");
        vids.put("Novia", "/recursosGraficos/Modulo Intermedio/Novia.mp4");
        vids.put("Novio", "/recursosGraficos/Modulo Intermedio/Novio.mp4");
        vids.put("Nuera", "/recursosGraficos/Modulo Intermedio/Nueva.mp4");
        vids.put("Papá", "/recursosGraficos/Modulo Intermedio/Papá.mp4");
        vids.put("Persona", "/recursosGraficos/Modulo Intermedio/Persona.mp4");
        vids.put("Prima", "/recursosGraficos/Modulo Intermedio/Prima.mp4");
        vids.put("Primo", "/recursosGraficos/Modulo Intermedio/Primo.mp4");
        vids.put("Tía", "/recursosGraficos/Modulo Intermedio/Tia.mp4");
        vids.put("Tío", "/recursosGraficos/Modulo Intermedio/Tio.mp4");
        vids.put("Yerno", "/recursosGraficos/Modulo Intermedio/Yerno.mp4");
        VideoLessonPanel p = new VideoLessonPanel(frame, cards, container,
                "Familia y Relaciones", vids, "intermedio");
        container.add(p, "inter_familia");
        cards.show(container, "inter_familia");
    }

    private void mostrarEmociones() {
        Map<String, String> vids = new LinkedHashMap<>();
        vids.put("Alegría", "/recursosGraficos/Modulo Intermedio/Alegria.mp4");
        vids.put("Enojo", "/recursosGraficos/Modulo Intermedio/Enojo.mp4");
        vids.put("Tristeza", "/recursosGraficos/Modulo Intermedio/Triste.mp4");
        VideoLessonPanel p = new VideoLessonPanel(frame, cards, container,
                "Emociones y Estados de Ánimo", vids, "intermedio");
        container.add(p, "inter_emociones");
        cards.show(container, "inter_emociones");
    }
}
