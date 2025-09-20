package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VulnerableApp {

    private static final Logger logger = LogManager.getLogger(VulnerableApp.class);

    public static void main(String[] args) {
        
        String userInput = "Logging data...";

        log(userInput);

        System.out.println("[+] Mensaje logueado.");
    }

    public static void log(String message) {
        // Vulnerable logging method
        logger.info(message);
    }
}
