package com.library.service;

import com.library.entity.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Create a new book
    public Book createBook(Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new IllegalArgumentException("Book with ISBN " + book.getIsbn() + " already exists");
        }
        return bookRepository.save(book);
    }

    // Get all books
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get book by ID
    @Transactional(readOnly = true)
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    // Get book by ISBN
    @Transactional(readOnly = true)
    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    // Update book
    public Book updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));

        // Check if ISBN is being changed and if the new ISBN already exists
        if (!book.getIsbn().equals(bookDetails.getIsbn()) &&
                bookRepository.existsByIsbn(bookDetails.getIsbn())) {
            throw new IllegalArgumentException("Book with ISBN " + bookDetails.getIsbn() + " already exists");
        }

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        book.setGenre(bookDetails.getGenre());
        book.setPublicationYear(bookDetails.getPublicationYear());
        book.setAvailable(bookDetails.getAvailable());

        return bookRepository.save(book);
    }

    // Delete book
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new IllegalArgumentException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    // Search books by title
    @Transactional(readOnly = true)
    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    // Search books by author
    @Transactional(readOnly = true)
    public List<Book> searchBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    // Search books by genre
    @Transactional(readOnly = true)
    public List<Book> searchBooksByGenre(String genre) {
        return bookRepository.findByGenreContainingIgnoreCase(genre);
    }

    // Search books by keyword (title or author)
    @Transactional(readOnly = true)
    public List<Book> searchBooks(String keyword) {
        return bookRepository.findByTitleOrAuthorContainingIgnoreCase(keyword);
    }

    // Get available books
    @Transactional(readOnly = true)
    public List<Book> getAvailableBooks() {
        return bookRepository.findByAvailable(true);
    }

    // Get unavailable books
    @Transactional(readOnly = true)
    public List<Book> getUnavailableBooks() {
        return bookRepository.findByAvailable(false);
    }

    // Update book availability
    public Book updateBookAvailability(Long id, Boolean available) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));

        book.setAvailable(available);
        return bookRepository.save(book);
    }

    // Get books by publication year
    @Transactional(readOnly = true)
    public List<Book> getBooksByPublicationYear(Integer year) {
        return bookRepository.findByPublicationYear(year);
    }

    // Get books published after a year
    @Transactional(readOnly = true)
    public List<Book> getBooksPublishedAfter(Integer year) {
        return bookRepository.findByPublicationYearGreaterThan(year);
    }

    // Get books published before a year
    @Transactional(readOnly = true)
    public List<Book> getBooksPublishedBefore(Integer year) {
        return bookRepository.findByPublicationYearLessThan(year);
    }

    // Get library statistics
    @Transactional(readOnly = true)
    public LibraryStats getLibraryStats() {
        long totalBooks = bookRepository.countTotalBooks();
        long availableBooks = bookRepository.countAvailableBooks();
        long unavailableBooks = totalBooks - availableBooks;

        return new LibraryStats(totalBooks, availableBooks, unavailableBooks);
    }

    // Inner class for library statistics
    public static class LibraryStats {
        private final long totalBooks;
        private final long availableBooks;
        private final long unavailableBooks;

        public LibraryStats(long totalBooks, long availableBooks, long unavailableBooks) {
            this.totalBooks = totalBooks;
            this.availableBooks = availableBooks;
            this.unavailableBooks = unavailableBooks;
        }

        public long getTotalBooks() {
            return totalBooks;
        }

        public long getAvailableBooks() {
            return availableBooks;
        }

        public long getUnavailableBooks() {
            return unavailableBooks;
        }

        @Override
        public String toString() {
            return "LibraryStats{" +
                    "totalBooks=" + totalBooks +
                    ", availableBooks=" + availableBooks +
                    ", unavailableBooks=" + unavailableBooks +
                    '}';
        }
    }
}