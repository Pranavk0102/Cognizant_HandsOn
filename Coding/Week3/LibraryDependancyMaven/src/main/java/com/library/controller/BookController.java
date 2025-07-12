package com.library.controller;

import com.library.service.BookService;
import com.library.repository.BookRepository.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    public BookController() {
        System.out.println("BookController created");
    }

    public void handleGetAllBooks() {
        System.out.println("\n=== Controller: Getting All Books ===");
        bookService.displayAllBooks();
    }

    public void handleAddBook(String title, String author, String isbn) {
        System.out.println("\n=== Controller: Adding New Book ===");
        Book book = bookService.addBook(title, author, isbn);
        System.out.println("Controller: Book added successfully - " + book);
    }

    public void handleDeleteBook(Long id) {
        System.out.println("\n=== Controller: Deleting Book ===");
        Book book = bookService.getBookById(id);
        if (book != null) {
            bookService.deleteBook(id);
            System.out.println("Controller: Book deleted successfully - " + book);
        } else {
            System.out.println("Controller: Book not found with ID: " + id);
        }
    }

    public void handleSearchBooks(String searchTerm, String searchType) {
        System.out.println("\n=== Controller: Searching Books ===");
        bookService.displaySearchResults(searchTerm, searchType);
    }

    public void handleGetBookById(Long id) {
        System.out.println("\n=== Controller: Getting Book by ID ===");
        Book book = bookService.getBookById(id);
        if (book != null) {
            System.out.println("Controller: Book found - " + book);
        } else {
            System.out.println("Controller: Book not found with ID: " + id);
        }
    }

    public void handleGetLibraryStats() {
        System.out.println("\n=== Controller: Library Statistics ===");
        long totalBooks = bookService.getTotalBooks();
        System.out.println("Total books in library: " + totalBooks);
        System.out.println("Library status: " + (totalBooks > 0 ? "Active" : "Empty"));
    }
}
