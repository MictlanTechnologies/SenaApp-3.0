package org.SenApp.SistemaReportes;

public class Menu {

    // Códigos ANSI para color en consola
    private static final String RED = "\u001B[31m";
    private static final String CYAN = "\u001B[36m";
    private static final String RESET = "\u001B[0m";
    private static final String YELLOW = "\u001B[33m";

    /**
     * Muestra un mensaje de error cuando se selecciona una opción no válida del menú.
     */
    public static void opcionInvalida() {
        System.out.println(RED + "\n\t✘ ¡ERROR! La opción seleccionada no es válida." + RESET);
    }

    /**
     * Muestra el mensaje que solicita al usuario ingresar una opción del menú.
     */
    public static void seleccionaOpcion() {
        System.out.print(CYAN + "\n> Selecciona una opción del menú: " + RESET);
    }

    /**
     * Muestra un mensaje de error cuando el usuario ingresa un dato inválido (no numérico, etc.).
     */
    public static void errorDato() {
        System.out.println(RED + "\t✘ ¡ERROR! Ese dato no es válido, intenta de nuevo." + RESET);
    }
}
