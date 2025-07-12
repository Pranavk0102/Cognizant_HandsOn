package com.library.service;

import com.library.repository.BookRepository;
import com.library.repository.BookRepository.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookService() {
        System.out.println("BookService created");
    }

    public Book addBook(String title, String author, String isbn) {
        Book book = new Book(null, title, author, isbn);
        return bookRepository.addBook(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContaining(title);
    }

    public List<Book> searchBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public long getTotalBooks() {
        return bookRepository.count();
    }

    public void displayAllBooks() {
        System.out.println("\n=== Library Books ===");
        List<Book> books = getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books available");
        } else {
            for (int i = 0; i < books.size(); i++) {
                System.out.println((i + 1) + ". " + books.get(i));
            }
        }
        System.out.println("Total books: " + getTotalBooks());
        System.out.println("====================");
    }

    public void displaySearchResults(String searchTerm, String searchType) {
        System.out.println("\n=== Search Results ===");
        List<Book> results;

        if ("title".equalsIgnoreCase(searchType)) {
            results = searchBooksByTitle(searchTerm);
        } else if ("author".equalsIgnoreCase(searchType)) {
            results = searchBooksByAuthor(searchTerm);
        } else {
            System.out.println("Invalid search type. Use 'title' or 'author'");
            return;
        }

        if (results.isEmpty()) {
            System.out.println("No books found for " + searchType + ": " + searchTerm);
        } else {
            System.out.println("Found " + results.size() + " book(s) for " + searchType + ": " + searchTerm);
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i));
            }
        }
        System.out.println("====================");
    }
}