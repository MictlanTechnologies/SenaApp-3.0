package org.SenApp.lecciones;

import org.SenApp.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class LeccionesApp {
    public static void iniciar(Usuario usuario) {
        JFrame frame = new JFrame("📘 Lecciones de Lengua de Señas");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBackground(Color.decode("#F0F4FA"));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel title = new JLabel("Bienvenido, " + usuario.getNombre(), SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.decode("#0F1B3D"));
        panel.add(title, BorderLayout.NORTH);

        JPanel botones = new JPanel(new GridLayout(0, 1, 15, 15));
        botones.setBackground(Color.decode("#F0F4FA"));

        JButton btnBasico = crearBoton("📗 Módulos Básicos");
        JButton btnIntermedio = crearBoton("📘 Módulos Intermedios");
        JButton btnAvanzado = crearBoton("📙 Módulos Avanzados");
        JButton btnExtras = crearBoton("🧩 Extras: Ejercicios, Juegos y Cuestionarios");
        JButton btnSalir = crearBoton("⬅️ Regresar al menú principal");

        botones.add(btnBasico);
        botones.add(btnIntermedio);
        botones.add(btnAvanzado);
        botones.add(btnExtras);
        botones.add(btnSalir);

        panel.add(botones, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);

        btnBasico.addActionListener(e -> ModuloBasico.mostrarLecciones());
        btnIntermedio.addActionListener(e -> ModuloIntermedio.mostrarLecciones());
        btnAvanzado.addActionListener(e -> ModuloAvanzado.mostrarLecciones());
        btnExtras.addActionListener(e -> Extras.mostrarExtras(usuario));
        btnSalir.addActionListener(e -> frame.dispose());
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
