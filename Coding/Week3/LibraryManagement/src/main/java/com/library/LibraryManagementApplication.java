package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        System.out.println("Starting Library Management Application...");

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = (BookService) context.getBean("bookService");

        System.out.println("\n=== Testing Library Management System ===");
        bookService.displayAllBooks();

        System.out.println("\n=== Adding new books ===");
        bookService.addBook("Design Patterns");
        bookService.addBook("Effective Java");

        System.out.println("\n=== After adding books ===");
        bookService.displayAllBooks();

        System.out.println("\n=== Searching for books ===");
        bookService.searchBook("Java");
        bookService.searchBook("Python");

        System.out.println("\n=== Removing a book ===");
        bookService.removeBook("Clean Code");

        System.out.println("\n=== Final book list ===");
        bookService.displayAllBooks();

        ((ClassPathXmlApplicationContext) context).close();

        System.out.println("\nApplication completed successfully!");
    }
}