package org.SenApp.gui;

import org.SenApp.Util.HibernateUtil;
import org.SenApp.model.GestorUsuarios;
import org.SenApp.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class SwingApp {
    private final JFrame frame = new JFrame("SenApp");
    private final CardLayout cards = new CardLayout();
    private final JPanel container = new JPanel(cards);

    private final GestorUsuarios gestor = new GestorUsuarios();
    private Usuario currentUser;

    public SwingApp() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 450);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        container.add(createLoginPanel(), "login");
        frame.setContentPane(container);
    }

    public void show() {
        frame.setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.decode("#F0F4FA"));

        // Logo
        JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/recursosGraficos/logo.png"));
            Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            logoLabel.setText("SenApp");
            logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
            logoLabel.setForeground(Color.decode("#223C77"));
        }
        panel.add(logoLabel, BorderLayout.NORTH);

        // Formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.decode("#F0F4FA"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField emailField = new JTextField();
        JPasswordField passField = new JPasswordField();

        JButton loginBtn = new JButton("🔓 Iniciar sesión");
        JButton registerBtn = new JButton("📝 Registrar");

        JLabel correoLabel = new JLabel("Correo electrónico:");
        JLabel passLabel = new JLabel("Contraseña:");

        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(correoLabel, gbc);
        gbc.gridx = 1; formPanel.add(emailField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(passLabel, gbc);
        gbc.gridx = 1; formPanel.add(passField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        JPanel botones = new JPanel();
        botones.setBackground(Color.decode("#F0F4FA"));
        botones.add(loginBtn);
        botones.add(registerBtn);
        formPanel.add(botones, gbc);

        panel.add(formPanel, BorderLayout.CENTER);

        // Estilos de botones
        for (JButton btn : new JButton[]{loginBtn, registerBtn}) {
            btn.setBackground(Color.decode("#3A61B1"));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        }

        // Acciones
        loginBtn.addActionListener(e -> {
            String email = emailField.getText().trim();
            String pass = new String(passField.getPassword());
            Usuario u = gestor.iniciarSesion(email, pass);
            if (u != null) {
                currentUser = u;
                container.add(createMainMenu(u), "menu");
                addUserPanels();
                cards.show(container, "menu");
            } else {
                JOptionPane.showMessageDialog(frame, "❌ Credenciales inválidas");
            }
        });

        registerBtn.addActionListener(e -> {
            String email = emailField.getText().trim();
            String pass = new String(passField.getPassword());
            if (gestor.registrar(email, pass)) {
                JOptionPane.showMessageDialog(frame, "✅ Cuenta creada correctamente");
            } else {
                JOptionPane.showMessageDialog(frame, "❌ No se pudo crear la cuenta");
            }
        });

        return panel;
    }

    private JPanel createMainMenu(Usuario usuario) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.decode("#F0F4FA"));

        JLabel welcome = new JLabel("👋 Bienvenido, " + usuario.getNombre(), SwingConstants.CENTER);
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 20));
        welcome.setForeground(Color.decode("#223C77"));
        welcome.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panel.add(welcome, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.decode("#F0F4FA"));

        JButton lecciones = new JButton("📚 Lecciones y Extras");
        JButton salir = new JButton("🚪 Salir");

        for (JButton btn : new JButton[]{lecciones, salir}) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setBackground(Color.decode("#3A61B1"));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
            btn.setMaximumSize(new Dimension(250, 40));
            btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            centerPanel.add(Box.createVerticalStrut(10));
            centerPanel.add(btn);
        }

        lecciones.addActionListener(e -> cards.show(container, "lecciones"));
        salir.addActionListener(e -> {
            currentUser = null;
            cards.show(container, "login");
        });

        panel.add(centerPanel, BorderLayout.CENTER);

        return panel;
    }

    private void addUserPanels() {
        container.add(new LeccionesAppPanel(frame, cards, container), "lecciones");
        container.add(new ModuloBasicoPanel(frame, cards, container), "basico");
        container.add(new ModuloIntermedioPanel(frame, cards, container), "intermedio");
        container.add(new ModuloAvanzadoPanel(frame, cards, container), "avanzado");
        container.add(new ExtrasPanel(frame, cards, container), "extras");
        container.add(new EjercicioInteractivoPanel(frame, cards, container), "ejercicio");
        container.add(new JuegoTextoPanel(frame, cards, container), "juego");
        container.add(new CuestionarioPanel(frame, cards, container), "cuestionario");
    }

    public static void main(String[] args) {
        new SwingApp().show();
    }
}
