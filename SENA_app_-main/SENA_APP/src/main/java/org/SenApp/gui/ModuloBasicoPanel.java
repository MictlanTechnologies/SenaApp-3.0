package org.SenApp.gui;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class ModuloBasicoPanel extends JPanel {
    private final JFrame frame;
    private final CardLayout cards;
    private final JPanel container;

    public ModuloBasicoPanel(JFrame frame, CardLayout cards, JPanel container) {
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
        JLabel title = new JLabel("🔤 Módulo Básico", SwingConstants.CENTER);
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

        JButton l1 = new JButton("📘 Introducción a la Lengua de Señas");
        JButton l2 = new JButton("🔡 Alfabeto Manual");
        JButton l3 = new JButton("🙋‍♂️ Saludos y Presentaciones");
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
        l1.addActionListener(e -> JOptionPane.showMessageDialog(frame,
                "Introducción: La lengua de señas es esencial para la comunicación con personas sordas. Tiene historia, reglas y cultura propia."));

        l2.addActionListener(e -> mostrarAlfabeto());
        l3.addActionListener(e -> mostrarSaludos());
        back.addActionListener(e -> cards.show(container, "lecciones"));

        add(botonesPanel, BorderLayout.SOUTH);
    }

    private void mostrarAlfabeto() {
        Map<String, String> vids = new LinkedHashMap<>();
        String letras = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        for (char c : letras.toCharArray()) {
            vids.put(String.valueOf(c), "/recursosGraficos/Modulo Basico/Abecedario/" + c + ".mp4");
        }
        VideoLessonPanel p = new VideoLessonPanel(frame, cards, container,
                "Alfabeto Manual", vids, "basico");
        container.add(p, "basico_alfabeto");
        cards.show(container, "basico_alfabeto");
    }

    private void mostrarSaludos() {
        Map<String, String> vids = new LinkedHashMap<>();
        vids.put("Hola", "/recursosGraficos/Modulo Basico/Hola.mp4");
        vids.put("Mi nombre es", "/recursosGraficos/Modulo Basico/Mi nombre es.mp4");
        vids.put("Adiós", "/recursosGraficos/Modulo Basico/Adiós.mp4");
        vids.put("Bien", "/recursosGraficos/Modulo Basico/Bien.mp4");
        vids.put("Buenas Tardes", "/recursosGraficos/Modulo Basico/Buenas Tardes.mp4");
        vids.put("Buenas noches", "/recursosGraficos/Modulo Basico/Buenas noches.mp4");
        vids.put("De nada", "/recursosGraficos/Modulo Basico/De nada.mp4");
        vids.put("Gracias", "/recursosGraficos/Modulo Basico/Gracias.mp4");
        vids.put("Gusto en conocerte", "/recursosGraficos/Modulo Basico/Gusto en conocerte.mp4");
        vids.put("Mal", "/recursosGraficos/Modulo Basico/Mal.mp4");
        vids.put("Más o menos", "/recursosGraficos/Modulo Basico/Mas o menos.mp4");
        vids.put("Nos vemos", "/recursosGraficos/Modulo Basico/Nos vemos.mp4");

        VideoLessonPanel p = new VideoLessonPanel(frame, cards, container,
                "Saludos y Presentaciones", vids, "basico");
        container.add(p, "basico_saludos");
        cards.show(container, "basico_saludos");
    }
}
