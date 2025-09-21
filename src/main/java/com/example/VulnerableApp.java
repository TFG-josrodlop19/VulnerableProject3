package com.example;

import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class VulnerableApp {

    /**
     * Método "fuzzable". Acepta un array de bytes y lo procesa como un ZIP.
     * La vulnerabilidad está en cómo getNextZipEntry() maneja datos corruptos.
     */
    public static void processZip(byte[] zipContent) throws Exception {
        ZipArchiveInputStream zipStream = new ZipArchiveInputStream(new ByteArrayInputStream(zipContent));
        
        // Al iterar sobre las entradas, un payload manipulado puede causar
        // que la biblioteca intente acceder a un índice de array inválido.
        while (zipStream.getNextEntry() != null) {
            // No es necesario hacer nada, el simple hecho de leer la entrada dispara el bug.
        }
        zipStream.close();
    }

    /**
     * Método main para pruebas manuales.
     */
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Uso: java -jar <jar_file> <fichero.zip>");
            return;
        }
        System.out.println("[+] Procesando fichero ZIP: " + args[0]);
        byte[] fileContent = Files.readAllBytes(Paths.get(args[0]));
        processZip(fileContent);
        System.out.println("[+] Fichero ZIP procesado con éxito.");
    }
}