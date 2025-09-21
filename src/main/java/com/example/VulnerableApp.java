package com.example;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;

public class VulnerableApp {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Uso: java -jar <jar_file> <fichero.xml>");
            return;
        }

        String fileName = args[0];
        System.out.println("[+] Procesando XML desde: " + fileName);

        // Leemos todo el fichero a un String
        String fileContent = new String(Files.readAllBytes(new File(fileName).toPath()));

        // Llamamos a la lógica vulnerable principal
        processXml(fileContent);
    }

    public static void processXml(String xmlContent) throws Exception {
        // 1. Se crea una factoría para construir parsers de XML
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // --- ¡LA CONFIGURACIÓN VULNERABLE! ---
        // La configuración por defecto sigue siendo insegura.

        // 2. Se crea el parser
        DocumentBuilder dBuilder = dbf.newDocumentBuilder();

        // 3. Se procesa el contenido. Usamos un ByteArrayInputStream para tratar
        // el String como si fuera un fichero en memoria.
        Document doc = dBuilder.parse(new ByteArrayInputStream(xmlContent.getBytes()));

        // 4. Se imprime el contenido para demostrar la exfiltración de datos
        NodeList nodes = doc.getElementsByTagName("data");
        if (nodes.getLength() > 0) {
            System.out.println("[+] Contenido del nodo 'data': " + nodes.item(0).getTextContent());
        }
    }
}