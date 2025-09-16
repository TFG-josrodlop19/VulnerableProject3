package com.example;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class VulnerableApp {
    public static void main(String[] args) throws Exception {

        String templateFile = "payload.vm";
        System.out.println("[+] Renderizando plantilla desde: " + templateFile);
        
        String templateContent = new String(Files.readAllBytes(Paths.get(templateFile)));

        // Se prepara el motor de Velocity
        Velocity.init();
        VelocityContext context = new VelocityContext();
        context.put("username", "Mundo"); // Una variable de ejemplo para la plantilla

        StringWriter writer = new StringWriter();
        
        // --- ¡LA LLAMADA VULNERABLE A LA DEPENDENCIA! ---
        // El método `evaluate` toma el contenido de la plantilla (controlado por el
        // atacante) y lo procesa. En esta versión vulnerable, no hay suficientes
        // protecciones, permitiendo que la plantilla ejecute código Java.
        Velocity.evaluate(context, writer, "logTag", templateContent);
        
        System.out.println("[+] Plantilla renderizada con éxito:");
        System.out.println(writer.toString());
    }
}