package org.SenApp.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class VideoLessonPanel extends JPanel {
    public VideoLessonPanel(JFrame frame, CardLayout cards, JPanel container,
                            String title, Map<String, String> videos, String backCard) {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#F0F4FA"));

        // Título superior
        JLabel lbl = new JLabel("🎥 " + title, SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lbl.setForeground(Color.WHITE);
        lbl.setOpaque(true);
        lbl.setBackground(Color.decode("#223C77"));
        lbl.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(lbl, BorderLayout.NORTH);

        // Panel de botones de video
        JPanel grid = new JPanel();
        grid.setBackground(Color.decode("#F0F4FA"));
        int cols = Math.min(5, videos.size());
        grid.setLayout(new GridLayout(0, cols, 15, 15));
        grid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (Map.Entry<String, String> entry : videos.entrySet()) {
            JButton btn = new JButton(entry.getKey());
            btn.setBackground(Color.decode("#3A61B1"));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btn.setPreferredSize(new Dimension(140, 40));
            btn.addActionListener(e -> VideoPlayer.play(entry.getValue()));
            grid.add(btn);
        }

        JScrollPane scroll = new JScrollPane(grid);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);  // smoother scroll
        add(scroll, BorderLayout.CENTER);

        // Botón regresar
        JPanel bottom = new JPanel();
        bottom.setBackground(Color.decode("#F0F4FA"));
        JButton back = new JButton("⬅ Regresar");
        back.setBackground(Color.decode("#3A61B1"));
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        back.setFont(new Font("Segoe UI", Font.BOLD, 14));
        back.setPreferredSize(new Dimension(140, 35));
        back.addActionListener(e -> cards.show(container, backCard));
        bottom.add(back);

        add(bottom, BorderLayout.SOUTH);
    }
}
