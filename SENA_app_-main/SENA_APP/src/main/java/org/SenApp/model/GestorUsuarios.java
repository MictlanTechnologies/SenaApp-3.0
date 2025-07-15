package org.SenApp.model;

import org.hibernate.Session;
import org.SenApp.Util.HibernateUtil;
import org.SenApp.Util.HibernateUtil.UsuarioDAO;

import java.util.List;

public class GestorUsuarios {
    private final UsuarioDAO dao;

    public GestorUsuarios() {
        this.dao = new UsuarioDAO();
    }

    /**
     * Registra un nuevo usuario si su correo no está en uso.
     * Se genera un nombre único basado en el correo.
     */
    public boolean registrar(String email, String contraseña) {
        Session session = HibernateUtil.getSession();
        if (session == null) {
            System.out.println("❌ Error al inicializar Hibernate. Verifica la configuración.");
            return false;
        }

        try (session) {
            Usuario existente = session.createQuery(
                            "FROM Usuario WHERE email = :email", Usuario.class)
                    .setParameter("email", email)
                    .uniqueResult();

            if (existente != null) {
                System.out.println("❌ Ya hay una cuenta con este correo.");
                return false;
            }

            String baseNombre = email.split("@")[0];
            String nombreFinal = baseNombre;
            int contador = 1;

            while (session.createQuery(
                            "SELECT COUNT(u) FROM Usuario u WHERE nombre = :nom", Long.class)
                    .setParameter("nom", nombreFinal)
                    .uniqueResult() > 0) {
                nombreFinal = baseNombre + contador++;
            }

            Usuario nuevo = new Usuario(email, contraseña, nombreFinal);
            dao.save(nuevo);
            System.out.println("✅ Cuenta creada. Tu nombre de usuario es: " + nombreFinal);
            return true;
        }
    }

    /**
     * Intenta iniciar sesión con email y contraseña.
     */
    public Usuario iniciarSesion(String email, String contraseña) {
        Session session = HibernateUtil.getSession();
        if (session == null) {
            System.out.println("❌ Error al inicializar Hibernate. Verifica la configuración.");
            return null;
        }

        try (session) {
            Usuario entity = session.createQuery(
                            "FROM Usuario WHERE email = :email AND contrasena = :con", Usuario.class)
                    .setParameter("email", email)
                    .setParameter("con", contraseña)
                    .uniqueResult();

            if (entity != null) {
                System.out.println("✅ Inicio de sesión exitoso. ¡Bienvenido " + entity.getNombre() + "!");
                return entity; // ✅ Devuelve el objeto completo desde Hibernate
            } else {
                System.out.println("⚠️ Correo o contraseña incorrectos.");
                return null;
            }
        }
    }
}
