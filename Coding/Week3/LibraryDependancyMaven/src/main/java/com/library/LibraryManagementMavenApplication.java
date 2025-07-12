package com.library;

import com.library.config.AppConfig;
import com.library.controller.BookController;
import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Collections;

public class LibraryManagementMavenApplication {

    public static void main(String[] args) {
        System.out.println("=== Starting Library Management Maven Application ===");
        System.out.println("Exercise 4: Maven Project with Spring Dependencies");
        System.out.println("Java Version: " + System.getProperty("java.version"));
        System.out.println("Maven Configuration with Spring Context, AOP, and WebMVC\n");

        runWithAnnotationConfig();

        System.out.println("\n" + repeatString("=", 60) + "\n");

        runWithXmlConfig();

        System.out.println("\n=== Maven Application Completed Successfully! ===");
    }

    private static String repeatString(String str, int count) {
        if (count <= 0) return "";
        return String.join("", Collections.nCopies(count, str));
    }

    private static void runWithAnnotationConfig() {
        System.out.println("=== Running with Annotation-based Configuration ===");

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        BookController bookController = context.getBean(BookController.class);
        BookService bookService = context.getBean(BookService.class);

        testApplication(bookController, "Annotation Config");

        ((AnnotationConfigApplicationContext) context).close();
    }

    private static void runWithXmlConfig() {
        System.out.println("=== Running with XML Configuration ===");

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        BookController bookController = context.getBean(BookController.class);
        BookService bookService = context.getBean(BookService.class);

        testApplication(bookController, "XML Config");

        ((ClassPathXmlApplicationContext) context).close();
    }

    private static void testApplication(BookController controller, String configType) {
        System.out.println("\n=== Testing Application with " + configType + " ===");

        controller.handleGetAllBooks();

        controller.handleGetLibraryStats();

        controller.handleAddBook("Maven in Action", "Brett Porter", "978-1932394887");
        controller.handleAddBook("Spring Boot in Action", "Craig Walls", "978-1617292545");

        controller.handleSearchBooks("Spring", "title");
        controller.handleSearchBooks("Craig", "author");

        controller.handleGetBookById(1L);
        controller.handleGetBookById(999L);

        controller.handleDeleteBook(3L);

        controller.handleGetAllBooks();
        controller.handleGetLibraryStats();
    }
}