package com.example;

import com.code_intelligence.jazzer.api.FuzzedDataProvider;

/** Fuzzer generated for class: VulnerableApp. Target: method main in line 10. */
public class VulnerableAppMainFuzzer {

  public static void fuzzerTestOneInput(FuzzedDataProvider dataProvider) {

    // Generate parameters for the target method or constructor
    java.lang.String[] args =
        new String[] {dataProvider.consumeString(1000), dataProvider.consumeString(1000)};

    // Instance creation

    // Method call
    try {
      VulnerableApp.main(args);
    } catch (Exception e) {
      // Catch all exceptions to prevent the fuzzer from stopping
      throw new RuntimeException(e);
    }
  }
}
