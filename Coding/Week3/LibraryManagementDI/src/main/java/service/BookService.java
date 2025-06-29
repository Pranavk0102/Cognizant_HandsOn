package com.library.service;

import com.library.repository.BookRepository;
import java.util.List;

public class BookService {
    private BookRepository bookRepository;

    public BookService() {
        System.out.println("BookService bean created");
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("BookRepository injected into BookService via setter method");
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public void addBook(String bookTitle) {
        if (bookRepository == null) {
            System.out.println("Error: BookRepository not injected");
            return;
        }

        if (bookTitle != null && !bookTitle.trim().isEmpty()) {
            if (bookRepository.bookExists(bookTitle)) {
                System.out.println("Book already exists: " + bookTitle);
            } else {
                bookRepository.addBook(bookTitle);
                System.out.println("BookService: Successfully added book - " + bookTitle);
            }
        } else {
            System.out.println("BookService: Invalid book title provided");
        }
    }

    public void removeBook(String bookTitle) {
        if (bookRepository == null) {
            System.out.println("Error: BookRepository not injected");
            return;
        }

        boolean removed = bookRepository.removeBook(bookTitle);
        if (removed) {
            System.out.println("BookService: Successfully removed book - " + bookTitle);
        } else {
            System.out.println("BookService: Failed to remove book - " + bookTitle);
        }
    }

    public List<String> getAllBooks() {
        if (bookRepository == null) {
            System.out.println("Error: BookRepository not injected");
            return null;
        }
        return bookRepository.getAllBooks();
    }

    public void displayAllBooks() {
        if (bookRepository == null) {
            System.out.println("Error: BookRepository not injected");
            return;
        }

        System.out.println("\n=== Library Management System ===");
        bookRepository.displayBooks();
        System.out.println("Total books in library: " + bookRepository.getTotalBooks());
        System.out.println("================================");
    }

    public void searchBook(String keyword) {
        if (bookRepository == null) {
            System.out.println("Error: BookRepository not injected");
            return;
        }

        System.out.println("\nSearching for books containing: '" + keyword + "'");
        List<String> matchingBooks = bookRepository.searchBooks(keyword);

        if (matchingBooks.isEmpty()) {
            System.out.println("No books found matching: " + keyword);
        } else {
            System.out.println("Found " + matchingBooks.size() + " book(s):");
            for (int i = 0; i < matchingBooks.size(); i++) {
                System.out.println((i + 1) + ". " + matchingBooks.get(i));
            }
        }
    }

    public void getLibraryStats() {
        if (bookRepository == null) {
            System.out.println("Error: BookRepository not injected");
            return;
        }

        System.out.println("\n=== Library Statistics ===");
        System.out.println("Total books: " + bookRepository.getTotalBooks());
        System.out.println("Repository status: " + (bookRepository.getTotalBooks() > 0 ? "Active" : "Empty"));
        System.out.println("========================");
    }
}
