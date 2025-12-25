package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * DemoApplication
 * ----------------
 * This is the main entry point of the Spring Boot application.
 *
 * Responsibilities:
 * - Bootstraps the Spring context
 * - Enables component scanning for com.example.demo
 * - Starts embedded server (Tomcat)
 *
 * This class is REQUIRED for:
 * - @SpringBootTest
 * - Application startup
 * - Integration testing
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
