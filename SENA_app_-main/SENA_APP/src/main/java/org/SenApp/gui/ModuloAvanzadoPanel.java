package org.SenApp.gui;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class ModuloAvanzadoPanel extends JPanel {
    private final JFrame frame;
    private final CardLayout cards;
    private final JPanel container;

    public ModuloAvanzadoPanel(JFrame frame, CardLayout cards, JPanel container) {
        this.frame = frame;
        this.cards = cards;
        this.container = container;

        setLayout(new BorderLayout());
        setBackground(Color.decode("#F0F4FA"));

        // LOGO
        ImageIcon logo = new ImageIcon(getClass().getResource("/recursosGraficos/logo.png"));
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(logoLabel, BorderLayout.NORTH);

        // TÍTULO
        JLabel title = new JLabel("📈 Módulo Avanzado", SwingConstants.CENTER);
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

        JButton l1 = new JButton("💬 Conversaciones Completas");
        JButton l2 = new JButton("🏢 Lengua de Señas en el Trabajo");
        JButton l3 = new JButton("🧪 Señas Técnicas y Especializadas");
        JButton back = new JButton("⬅ Regresar");

        for (JButton btn : new JButton[]{l1, l2, l3, back}) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setBackground(Color.decode("#3A61B1"));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
            btn.setMaximumSize(new Dimension(360, 40));
            btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            botonesPanel.add(Box.createVerticalStrut(10));
            botonesPanel.add(btn);
        }

        // Acciones
        l1.addActionListener(e -> mostrarConversaciones());
        l2.addActionListener(e -> mostrarTrabajo());
        l3.addActionListener(e -> mostrarTecnicas());
        back.addActionListener(e -> cards.show(container, "lecciones"));

        add(botonesPanel, BorderLayout.SOUTH);
    }

    private void mostrarConversaciones() {
        Map<String, String> vids = new LinkedHashMap<>();
        vids.put("Conversación 1", "/recursosGraficos/Modulo Intermedio/Conversacion 1.mp4");
        vids.put("Conversación 2", "/recursosGraficos/Modulo Intermedio/Conversacion 1.mp4");
        VideoLessonPanel p = new VideoLessonPanel(frame, cards, container,
                "Conversaciones Completas", vids, "avanzado");
        container.add(p, "avan_conv");
        cards.show(container, "avan_conv");
    }

    private void mostrarTrabajo() {
        Map<String, String> vids = new LinkedHashMap<>();
        vids.put("Reunión", "/recursosGraficos/Modulo Intermedio/Grupo.mp4");
        vids.put("Entrevista", "/recursosGraficos/Modulo Intermedio/Persona.mp4");
        VideoLessonPanel p = new VideoLessonPanel(frame, cards, container,
                "Lengua de Señas en el Trabajo", vids, "avanzado");
        container.add(p, "avan_trabajo");
        cards.show(container, "avan_trabajo");
    }

    private void mostrarTecnicas() {
        Map<String, String> vids = new LinkedHashMap<>();
        vids.put("Medicina", "/recursosGraficos/Modulo Intermedio/Persona.mp4");
        vids.put("Tecnología", "/recursosGraficos/Modulo Intermedio/Grupo.mp4");
        VideoLessonPanel p = new VideoLessonPanel(frame, cards, container,
                "Señas Técnicas y Especializadas", vids, "avanzado");
        container.add(p, "avan_tecnicas");
        cards.show(container, "avan_tecnicas");
    }
}
