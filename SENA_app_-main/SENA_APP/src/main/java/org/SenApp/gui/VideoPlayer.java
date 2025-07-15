package org.SenApp.gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class VideoPlayer {

    /**
     * Intenta abrir un archivo de video a partir de su ruta en el recurso del proyecto.
     *
     * @param resourcePath Ruta relativa al recurso (por ejemplo: /videos/basico/A.mp4)
     */
    public static void play(String resourcePath) {
        try {
            URL url = VideoPlayer.class.getResource(resourcePath);
            if (url == null) {
                mostrarError("❌ No se encontró el video:\n" + resourcePath);
                return;
            }

            File file = new File(url.toURI());
            if (!file.exists()) {
                mostrarError("⚠️ El archivo no existe:\n" + file.getAbsolutePath());
                return;
            }

            Desktop.getDesktop().open(file);

        } catch (URISyntaxException | java.io.IOException ex) {
            mostrarError("❌ Error al reproducir el video:\n" + ex.getMessage());
        }
    }

    private static void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
                null,
                mensaje,
                "Error de Reproducción",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
