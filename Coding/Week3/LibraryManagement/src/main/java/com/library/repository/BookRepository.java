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
        System.out.println("BookRepository initialized with sample books");
    }

    public void addBook(String bookTitle) {
        books.add(bookTitle);
        System.out.println("Book added: " + bookTitle);
    }

    public List<String> getAllBooks() {
        return new ArrayList<>(books);
    }

    public boolean removeBook(String bookTitle) {
        boolean removed = books.remove(bookTitle);
        if (removed) {
            System.out.println("Book removed: " + bookTitle);
        } else {
            System.out.println("Book not found: " + bookTitle);
        }
        return removed;
    }

    public int getTotalBooks() {
        return books.size();
    }

    public void displayBooks() {
        System.out.println("Books in repository:");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
    }
}
