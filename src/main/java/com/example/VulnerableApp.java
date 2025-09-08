package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VulnerableApp {

    private static final Logger logger = LogManager.getLogger(VulnerableApp.class);

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java -jar <jar_file> \"<mensaje_a_loguear>\"");
            return;
        }
        
        String userInput = args[0];
        
        System.out.println("[+] Logueando el siguiente mensaje del usuario: " + userInput);
        
        // --- ¡LA LLAMADA VULNERABLE A LA DEPENDENCIA! ---
        // Este es un uso estándar y completamente normal de una biblioteca de logging.
        // El desarrollador no tiene forma de saber que al registrar ciertos mensajes,
        // Log4j ejecutará código. La vulnerabilidad está 100% oculta en la dependencia.
        logger.error(userInput);
        
        System.out.println("[+] Mensaje logueado. La aplicación termina.");
    }
}