package com.library.service;

import com.library.repository.BookRepository;
import java.util.List;

public class BookService {
    private BookRepository bookRepository;


    public BookService() {
        System.out.println("BookService initialized");
    }


    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("BookRepository injected into BookService");
    }

    public void addBook(String bookTitle) {
        if (bookTitle != null && !bookTitle.trim().isEmpty()) {
            bookRepository.addBook(bookTitle);
        } else {
            System.out.println("Invalid book title");
        }
    }

    public void removeBook(String bookTitle) {
        bookRepository.removeBook(bookTitle);
    }

    public List<String> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public void displayAllBooks() {
        System.out.println("=== Library Books ===");
        bookRepository.displayBooks();
        System.out.println("Total books: " + bookRepository.getTotalBooks());
    }

    public void searchBook(String bookTitle) {
        List<String> books = bookRepository.getAllBooks();
        boolean found = false;

        for (String book : books) {
            if (book.toLowerCase().contains(bookTitle.toLowerCase())) {
                System.out.println("Found book: " + book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found matching: " + bookTitle);
        }
    }
}
