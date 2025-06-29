package com.library;

import com.library.service.BookService;
import com.library.repository.BookRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementDIApplication {
    public static void main(String[] args) {
        System.out.println("=== Starting Library Management DI Application ===");
        System.out.println("Initializing Spring Application Context...\n");

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("\n=== Spring Context Loaded Successfully ===");

        BookService bookService = (BookService) context.getBean("bookService");

        System.out.println("\n=== Testing Dependency Injection ===");
        if (bookService.getBookRepository() != null) {
            System.out.println("✓ Dependency Injection successful!");
            System.out.println("✓ BookRepository is properly injected into BookService");
        } else {
            System.out.println("✗ Dependency Injection failed!");
        }

        System.out.println("\n=== Testing Library Management Operations ===");

        bookService.displayAllBooks();

        bookService.getLibraryStats();

        System.out.println("\n=== Testing Add Book Functionality ===");
        bookService.addBook("Spring Boot in Action");
        bookService.addBook("Microservices Patterns");
        bookService.addBook("Spring Boot in Action"); // Duplicate test

        bookService.displayAllBooks();

        System.out.println("\n=== Testing Search Functionality ===");
        bookService.searchBook("Spring");
        bookService.searchBook("Java");
        bookService.searchBook("Python");

        System.out.println("\n=== Testing Remove Book Functionality ===");
        bookService.removeBook("Clean Code");
        bookService.removeBook("Non-existent Book");

        bookService.displayAllBooks();
        bookService.getLibraryStats();

        System.out.println("\n=== Testing Bean Scopes ===");
        BookService bookService2 = (BookService) context.getBean("bookService");
        System.out.println("BookService instances are same: " + (bookService == bookService2));

        BookRepository repository = (BookRepository) context.getBean("bookRepository");
        System.out.println("Repository instances are same: " + (bookService.getBookRepository() == repository));

        ((ClassPathXmlApplicationContext) context).close();

        System.out.println("\n=== DI Application Completed Successfully! ===");
        System.out.println("Spring context closed properly.");
    }
}