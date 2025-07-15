package org.SenApp.lecciones;

import org.SenApp.model.Usuario;
import org.SenApp.lecciones.Cuestionario;


import javax.swing.*;
import java.awt.*;

public class Extras {
    public static void mostrarExtras(Usuario usuario) {
        JFrame frame = new JFrame("🎁 Contenido Extra");
        frame.setSize(450, 350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Panel principal con fondo profesional
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 15, 15));
        panel.setBackground(Color.decode("#F0F4FA"));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel titulo = new JLabel("Selecciona una opción", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(Color.decode("#0F1B3D"));
        frame.add(titulo, BorderLayout.NORTH);

        // Botones estilizados
        JButton btn1 = crearBoton("🧩 Ejercicios Interactivos");
        JButton btn2 = crearBoton("🎮 Juegos");
        JButton btn3 = crearBoton("📝 Cuestionarios");
        JButton btn4 = crearBoton("🔙 Regresar");

        // Acciones
        btn1.addActionListener(e -> {
            frame.dispose();
            EjercicioInteractivo.iniciarCuestionario();
        });

        btn2.addActionListener(e -> {
            frame.dispose();
            JuegoTexto.iniciar();
        });

        btn3.addActionListener(e -> {
            frame.dispose();
            Cuestionario.iniciar();
        });


        btn4.addActionListener(e -> frame.dispose());

        // Agregar botones
        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(btn4);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        boton.setBackground(Color.decode("#3A61B1"));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return boton;
    }
}
