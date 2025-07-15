package org.SenApp.SistemaReportes;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.SenApp.model.ProgresoUsuario;
import org.SenApp.Util.HibernateUtil;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.SenApp.model.Usuario;

public class Reportes {

    // ANSI Colors
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";

    public static void guardarProgreso(Usuario usuario, String tipoActividad, int puntaje, int total) {
        if (usuario == null) {
            System.out.println(RED + "⚠️  No se puede guardar progreso sin un usuario válido." + RESET);
            return;
        }

        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String fecha = ahora.format(formato);

        System.out.println(CYAN + "\n📋 ── REPORTE DE ACTIVIDAD ──────────────" + RESET);
        System.out.println(CYAN + "👤 Usuario: " + RESET + usuario.getNombre());
        System.out.println(CYAN + "🧠 Actividad: " + RESET + tipoActividad);
        System.out.println(CYAN + "📊 Puntaje: " + RESET + puntaje + "/" + total);
        System.out.println(CYAN + "🗓️  Fecha: " + RESET + fecha);

        try (Session s = HibernateUtil.getSession()) {
            Usuario entity = s.createQuery(
                            "from Usuario where email=:email",
                            Usuario.class)
                    .setParameter("email", usuario.getEmail())
                    .uniqueResult();
            if (entity != null) {
                Transaction tx = s.beginTransaction();
                ProgresoUsuario prog = new ProgresoUsuario(entity, puntaje);
                s.persist(prog);
                tx.commit();
                System.out.println(GREEN + "✅ Progreso guardado exitosamente en la base de datos." + RESET);
            } else {
                System.out.println(RED + "⚠️  Usuario no encontrado en la base de datos." + RESET);
            }
        } catch (Exception e) {
            System.out.println(RED + "❌ Error al guardar el progreso: " + e.getMessage() + RESET);
        }
    }
}
