package com.example;

import com.code_intelligence.jazzer.api.FuzzedDataProvider;

/** Fuzzer generated for class: VulnerableApp. Target: method processTemplate in line 16. */
public class VulnerableAppProcesstemplateFuzzer {

  public static void fuzzerTestOneInput(FuzzedDataProvider dataProvider) {

    // Generate parameters for the target method or constructor
    java.lang.String templateContent = dataProvider.consumeString(1000);

    // Instance creation

    // Method call
    try {
      VulnerableApp.processTemplate(templateContent);
    } catch (Exception e) {
      // Catch all exceptions to prevent the fuzzer from stopping
    }
  }
}
