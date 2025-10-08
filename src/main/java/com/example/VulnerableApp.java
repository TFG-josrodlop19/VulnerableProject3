package com.example;

import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class VulnerableApp {

    /**
     * Vulnerable method that processes ZIP content.
     */
    public static void processZip(byte[] zipContent) throws Exception {
        ZipArchiveInputStream zipStream = new ZipArchiveInputStream(new ByteArrayInputStream(zipContent));

        // When iterating over entries, a crafted payload may cause
        // the library to attempt to access an invalid array index.
        while (zipStream.getNextEntry() != null) {
            // No action is needed, simply reading the entry triggers the bug.
        }
        zipStream.close();
    }

    /**
     * Main method for manual testing.
     */
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: java -jar <jar_file> <file.zip>");
            return;
        }
        System.out.println("[+] Processing ZIP file: " + args[0]);
        byte[] fileContent = Files.readAllBytes(Paths.get(args[0]));
        processZip(fileContent);
        System.out.println("[+] ZIP file processed successfully.");
    }
}