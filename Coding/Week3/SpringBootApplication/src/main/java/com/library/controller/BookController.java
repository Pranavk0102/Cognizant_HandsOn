package com.library.controller;

import com.library.entity.Book;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@Validated
@CrossOrigin(origins = "*")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // GET /api/books - Get all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // GET /api/books/{id} - Get book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/books/isbn/{isbn} - Get book by ISBN
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        Optional<Book> book = bookService.getBookByIsbn(isbn);
        return book.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/books - Create a new book
    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        try {
            Book createdBook = bookService.createBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT /api/books/{id} - Update a book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book bookDetails) {
        try {
            Book updatedBook = bookService.updateBook(id, bookDetails);
            return ResponseEntity.ok(updatedBook);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/books/{id} - Delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET /api/books/search - Search books by keyword
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String keyword) {
        List<Book> books = bookService.searchBooks(keyword);
        return ResponseEntity.ok(books);
    }

    // GET /api/books/search/title - Search books by title
    @GetMapping("/search/title")
    public ResponseEntity<List<Book>> searchBooksByTitle(@RequestParam String title) {
        List<Book> books = bookService.searchBooksByTitle(title);
        return ResponseEntity.ok(books);
    }

    // GET /api/books/search/author - Search books by author
    @GetMapping("/search/author")
    public ResponseEntity<List<Book>> searchBooksByAuthor(@RequestParam String author) {
        List<Book> books = bookService.searchBooksByAuthor(author);
        return ResponseEntity.ok(books);
    }

    // GET /api/books/search/genre - Search books by genre
    @GetMapping("/search/genre")
    public ResponseEntity<List<Book>> searchBooksByGenre(@RequestParam String genre) {
        List<Book> books = bookService.searchBooksByGenre(genre);
        return ResponseEntity.ok(books);
    }

    // GET /api/books/available - Get available books
    @GetMapping("/available")
    public ResponseEntity<List<Book>> getAvailableBooks() {
        List<Book> books = bookService.getAvailableBooks();
        return ResponseEntity.ok(books);
    }

    // GET /api/books/unavailable - Get unavailable books
    @GetMapping("/unavailable")
    public ResponseEntity<List<Book>> getUnavailableBooks() {
        List<Book> books = bookService.getUnavailableBooks();
        return ResponseEntity.ok(books);
    }

    // PATCH /api/books/{id}/availability - Update book availability
    @PatchMapping("/{id}/availability")
    public ResponseEntity<Book> updateBookAvailability(@PathVariable Long id, @RequestParam Boolean available) {
        try {
            Book updatedBook = bookService.updateBookAvailability(id, available);
            return ResponseEntity.ok(updatedBook);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET /api/books/year/{year} - Get books by publication year
    @GetMapping("/year/{year}")
    public ResponseEntity<List<Book>> getBooksByPublicationYear(@PathVariable Integer year) {
        List<Book> books = bookService.getBooksByPublicationYear(year);
        return ResponseEntity.ok(books);
    }

    // GET /api/books/published/after/{year} - Get books published after a year
    @GetMapping("/published/after/{year}")
    public ResponseEntity<List<Book>> getBooksPublishedAfter(@PathVariable Integer year) {
        List<Book> books = bookService.getBooksPublishedAfter(year);
        return ResponseEntity.ok(books);
    }

    // GET /api/books/published/before/{year} - Get books published before a year
    @GetMapping("/published/before/{year}")
    public ResponseEntity<List<Book>> getBooksPublishedBefore(@PathVariable Integer year) {
        List<Book> books = bookService.getBooksPublishedBefore(year);
        return ResponseEntity.ok(books);
    }

    // GET /api/books/stats - Get library statistics
    @GetMapping("/stats")
    public ResponseEntity<BookService.LibraryStats> getLibraryStats() {
        BookService.LibraryStats stats = bookService.getLibraryStats();
        return ResponseEntity.ok(stats);
    }

    // Exception handler for validation errors
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body("Error: " + e.getMessage());
    }
}