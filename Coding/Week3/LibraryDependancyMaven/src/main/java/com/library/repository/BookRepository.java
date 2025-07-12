package com.library.repository;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class BookRepository {
    private final ConcurrentHashMap<Long, Book> books = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public static class Book {
        private Long id;
        private String title;
        private String author;
        private String isbn;

        public Book() {}

        public Book(Long id, String title, String author, String isbn) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.isbn = isbn;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getAuthor() { return author; }
        public void setAuthor(String author) { this.author = author; }

        public String getIsbn() { return isbn; }
        public void setIsbn(String isbn) { this.isbn = isbn; }

        @Override
        public String toString() {
            return "Book{id=" + id + ", title='" + title + "', author='" + author + "', isbn='" + isbn + "'}";
        }
    }

    public BookRepository() {
        // Initialize with sample data
        initializeSampleBooks();
        System.out.println("BookRepository initialized with " + books.size() + " books");
    }

    private void initializeSampleBooks() {
        addBook(new Book(idGenerator.getAndIncrement(), "Spring in Action", "Craig Walls", "978-1617294945"));
        addBook(new Book(idGenerator.getAndIncrement(), "Java: The Complete Reference", "Herbert Schildt", "978-1260440232"));
        addBook(new Book(idGenerator.getAndIncrement(), "Clean Code", "Robert C. Martin", "978-0132350884"));
        addBook(new Book(idGenerator.getAndIncrement(), "Effective Java", "Joshua Bloch", "978-0134685991"));
        addBook(new Book(idGenerator.getAndIncrement(), "Design Patterns", "Gang of Four", "978-0201633612"));
    }

    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(idGenerator.getAndIncrement());
        }
        books.put(book.getId(), book);
        System.out.println("Book saved: " + book);
        return book;
    }

    public Book addBook(Book book) {
        return save(book);
    }

    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    public Book findById(Long id) {
        return books.get(id);
    }

    public void deleteById(Long id) {
        Book removed = books.remove(id);
        if (removed != null) {
            System.out.println("Book removed: " + removed);
        } else {
            System.out.println("Book not found with ID: " + id);
        }
    }

    public List<Book> findByTitleContaining(String title) {
        List<Book> matchingBooks = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }

    public List<Book> findByAuthor(String author) {
        List<Book> matchingBooks = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }

    public long count() {
        return books.size();
    }
}
