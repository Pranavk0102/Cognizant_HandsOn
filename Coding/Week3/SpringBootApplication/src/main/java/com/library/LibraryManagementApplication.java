package com.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementApplication.class, args);
        System.out.println("=== Spring Boot Library Management Application Started ===");
        System.out.println("Access H2 Database Console: http://localhost:8080/h2-console");
        System.out.println("API Base URL: http://localhost:8080/api/books");
        System.out.println("========================================================");
    }
}