package com.library.repository;

import com.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Find books by title (case-insensitive)
    List<Book> findByTitleContainingIgnoreCase(String title);

    // Find books by author (case-insensitive)
    List<Book> findByAuthorContainingIgnoreCase(String author);

    // Find books by genre (case-insensitive)
    List<Book> findByGenreContainingIgnoreCase(String genre);

    // Find book by ISBN
    Optional<Book> findByIsbn(String isbn);

    // Check if book exists by ISBN
    boolean existsByIsbn(String isbn);

    // Find books by availability status
    List<Book> findByAvailable(Boolean available);

    // Find books by publication year
    List<Book> findByPublicationYear(Integer publicationYear);

    // Find books published after a certain year
    List<Book> findByPublicationYearGreaterThan(Integer year);

    // Find books published before a certain year
    List<Book> findByPublicationYearLessThan(Integer year);

    // Custom query to find books by title or author
    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(b.author) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Book> findByTitleOrAuthorContainingIgnoreCase(@Param("keyword") String keyword);

    // Custom query to get books count by genre
    @Query("SELECT b.genre, COUNT(b) FROM Book b GROUP BY b.genre")
    List<Object[]> countBooksByGenre();

    // Custom query to get available books count
    @Query("SELECT COUNT(b) FROM Book b WHERE b.available = true")
    long countAvailableBooks();

    // Custom query to get total books count
    @Query("SELECT COUNT(b) FROM Book b")
    long countTotalBooks();

    // Custom query to find books by multiple criteria
    @Query("SELECT b FROM Book b WHERE " +
            "(:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
            "(:author IS NULL OR LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%'))) AND " +
            "(:genre IS NULL OR LOWER(b.genre) LIKE LOWER(CONCAT('%', :genre, '%'))) AND " +
            "(:available IS NULL OR b.available = :available)")
    List<Book> findBooksByCriteria(@Param("title") String title,
                                   @Param("author") String author,
                                   @Param("genre") String genre,
                                   @Param("available") Boolean available);
}