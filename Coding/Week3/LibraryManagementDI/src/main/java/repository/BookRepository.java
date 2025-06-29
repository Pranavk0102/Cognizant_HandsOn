package com.library.repository;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private List<String> books;

    public BookRepository() {
        this.books = new ArrayList<>();
        // Initialize with some sample books
        books.add("Spring in Action");
        books.add("Java: The Complete Reference");
        books.add("Clean Code");
        books.add("Effective Java");
        books.add("Design Patterns");
        System.out.println("BookRepository bean created and initialized with sample books");
    }

    public void addBook(String bookTitle) {
        if (bookTitle != null && !bookTitle.trim().isEmpty()) {
            books.add(bookTitle);
            System.out.println("Book added to repository: " + bookTitle);
        } else {
            System.out.println("Cannot add book: Invalid title");
        }
    }

    public List<String> getAllBooks() {
        return new ArrayList<>(books);
    }

    public boolean removeBook(String bookTitle) {
        boolean removed = books.remove(bookTitle);
        if (removed) {
            System.out.println("Book removed from repository: " + bookTitle);
        } else {
            System.out.println("Book not found in repository: " + bookTitle);
        }
        return removed;
    }

    public int getTotalBooks() {
        return books.size();
    }

    public void displayBooks() {
        System.out.println("Books in repository:");
        if (books.isEmpty()) {
            System.out.println("No books available");
        } else {
            for (int i = 0; i < books.size(); i++) {
                System.out.println((i + 1) + ". " + books.get(i));
            }
        }
    }

    public boolean bookExists(String bookTitle) {
        return books.contains(bookTitle);
    }

    public List<String> searchBooks(String keyword) {
        List<String> matchingBooks = new ArrayList<>();
        for (String book : books) {
            if (book.toLowerCase().contains(keyword.toLowerCase())) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }
}
