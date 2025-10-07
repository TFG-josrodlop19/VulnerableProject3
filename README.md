# VulnerableProject3

This is a sample Java project that contains a known vulnerability for testing Autofuzz. 

## Vulnerability Details

The project includes Apache Commons Compress version 1.18, which is known to have a critical security vulnerability: CVE-2019-12402. This vulnerability allows Denial of Service (DoS) attacks via a crafted archive file.

## Purpose
The purpose of this project is to provide a controlled environment for testing and demonstrating the Autofuzz tool's capabilities in identifying and exploiting vulnerabilities in Java applications.