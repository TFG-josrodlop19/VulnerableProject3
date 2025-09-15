package com.example;

import java.io.ByteArrayOutputStream;

import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.exec.CommandLine;

public class VulnerableApp {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java -jar <jar_file> <hostname_o_ip>");
            return;
        }

        String userInput = args[0];
        System.out.println("[+] Se comprobará la conectividad con: " + userInput);

        // --- ¡LA CONSTRUCCIÓN VULNERABLE DEL COMANDO! ---
        // El error fatal está aquí. El input del usuario se concatena directamente
        // en el string del comando que se va a ejecutar.
        String command = "ping -c 1 " + userInput; // En Windows, sería "ping -n 1 "

        try {
            System.out.println("[+] Ejecutando comando: " + command);
            
            // La biblioteca commons-exec es potente, pero el método `parse` es peligroso
            // si recibe un string que ya ha sido contaminado con input del usuario.
            CommandLine cmdLine = CommandLine.parse(command);
            
            DefaultExecutor executor = new DefaultExecutor();
            // Redirigimos la salida del comando para mostrarla en nuestra consola
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
            executor.setStreamHandler(streamHandler);
            
            executor.execute(cmdLine);
            
            System.out.println("[+] Salida del comando:");
            System.out.println(outputStream.toString());

        } catch (Exception e) {
            System.err.println("[!] Error ejecutando el comando.");
            e.printStackTrace();
        }
    }
}