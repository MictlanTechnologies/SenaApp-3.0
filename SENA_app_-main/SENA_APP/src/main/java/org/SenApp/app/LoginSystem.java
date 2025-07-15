package org.SenApp.app;

import org.SenApp.model.GestorUsuarios;
import org.SenApp.model.Usuario;
import org.SenApp.SistemaReportes.Reportes;
import org.SenApp.lecciones.LeccionesApp;
import org.SenApp.Util.HibernateUtil;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.regex.Pattern;

public class LoginSystem {
    private static final GestorUsuarios gestor = new GestorUsuarios();

    public static void main(String[] args) {
        HibernateUtil.getSession(); // inicializa Hibernate

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("SenApp - Inicio de Sesión");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 400);
            frame.setLocationRelativeTo(null);

            JPanel content = new JPanel(new BorderLayout());
            content.setBackground(Color.decode("#F0F4FA"));

            // LOGO (escala el icono si existe)
            JLabel logoLabel = new JLabel();
            logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            try {
                URL logoURL = LoginSystem.class.getResource("/recursosGraficos/logo.png");
                if (logoURL != null) {
                    ImageIcon logoIcon = new ImageIcon(logoURL);
                    Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    logoLabel.setIcon(new ImageIcon(scaledImage));
                } else {
                    logoLabel.setText("SenApp");
                    logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
                    logoLabel.setForeground(Color.decode("#223C77"));
                }
            } catch (Exception e) {
                logoLabel.setText("SenApp");
                logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
                logoLabel.setForeground(Color.decode("#223C77"));
            }

            content.add(logoLabel, BorderLayout.NORTH);

            // Panel de formulario
            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBackground(Color.decode("#223C77"));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel correoLabel = new JLabel("Correo electrónico:");
            correoLabel.setForeground(Color.WHITE);
            JTextField emailField = new JTextField(20);

            JLabel passLabel = new JLabel("Contraseña:");
            passLabel.setForeground(Color.WHITE);
            JPasswordField passField = new JPasswordField(20);

            JButton btnCrear = new JButton("Crear cuenta");
            JButton btnIniciar = new JButton("Iniciar sesión");

            Color btnColor = Color.decode("#3A61B1");
            Font btnFont = new Font("Segoe UI", Font.BOLD, 14);
            for (JButton btn : new JButton[]{btnCrear, btnIniciar}) {
                btn.setBackground(btnColor);
                btn.setForeground(Color.WHITE);
                btn.setFocusPainted(false);
                btn.setFont(btnFont);
                btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            }

            gbc.gridx = 0; gbc.gridy = 0;
            panel.add(correoLabel, gbc);
            gbc.gridx = 1;
            panel.add(emailField, gbc);

            gbc.gridx = 0; gbc.gridy = 1;
            panel.add(passLabel, gbc);
            gbc.gridx = 1;
            panel.add(passField, gbc);

            gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
            JPanel botones = new JPanel(new FlowLayout());
            botones.setBackground(Color.decode("#223C77"));
            botones.add(btnCrear);
            botones.add(btnIniciar);
            panel.add(botones, gbc);

            content.add(panel, BorderLayout.CENTER);
            frame.setContentPane(content);
            frame.setVisible(true);

            // Crear cuenta
            btnCrear.addActionListener(e -> {
                String email = emailField.getText().trim();
                String pass = new String(passField.getPassword());

                if (!esCorreoValido(email)) {
                    mostrarAlerta("Por favor, ingresa un correo válido.", frame);
                    return;
                }

                if (pass.length() < 6) {
                    mostrarAlerta("La contraseña debe tener al menos 6 caracteres.", frame);
                    return;
                }

                try {
                    gestor.registrar(email, pass);
                    JOptionPane.showMessageDialog(frame, "✅ Cuenta creada con éxito.");
                } catch (Exception ex) {
                    mostrarError("❌ No se pudo crear la cuenta: " + ex.getMessage(), frame);
                }
            });

            // Iniciar sesión
            btnIniciar.addActionListener(e -> {
                String email = emailField.getText().trim();
                String pass = new String(passField.getPassword());

                if (!esCorreoValido(email) || pass.isBlank()) {
                    mostrarAlerta("Credenciales inválidas.", frame);
                    return;
                }

                Usuario user = gestor.iniciarSesion(email, pass);
                if (user != null) {
                    Reportes.guardarProgreso(user, "test_swing", 10, 10);
                    JOptionPane.showMessageDialog(frame, "🎉 Bienvenido, " + user.getNombre());
                    LeccionesApp.iniciar(user);
                } else {
                    mostrarError("❌ Credenciales incorrectas.", frame);
                }
            });
        });
    }

    private static boolean esCorreoValido(String correo) {
        return Pattern.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", correo);
    }

    private static void mostrarAlerta(String msg, Component parent) {
        JOptionPane.showMessageDialog(parent, msg, "⚠️ Atención", JOptionPane.WARNING_MESSAGE);
    }

    private static void mostrarError(String msg, Component parent) {
        JOptionPane.showMessageDialog(parent, msg, "❌ Error", JOptionPane.ERROR_MESSAGE);
    }
}
