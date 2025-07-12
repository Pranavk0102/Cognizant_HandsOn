package com.library;

import com.library.config.AppConfig;
import com.library.service.BookService;
import com.library.repository.BookRepository.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Before
    public void setUp() {
        System.out.println("Setting up test environment...");
    }

    @Test
    public void testGetAllBooks() {
        System.out.println("Testing getAllBooks...");
        List<Book> books = bookService.getAllBooks();
        assertNotNull("Books list should not be null", books);
        assertTrue("Should have at least one book", books.size() > 0);
        System.out.println("Found " + books.size() + " books");
    }

    @Test
    public void testAddBook() {
        System.out.println("Testing addBook...");
        long initialCount = bookService.getTotalBooks();

        Book newBook = bookService.addBook("Test Book", "Test Author", "123456789");

        assertNotNull("New book should not be null", newBook);
        assertNotNull("Book ID should be assigned", newBook.getId());
        assertEquals("Title should match", "Test Book", newBook.getTitle());
        assertEquals("Author should match", "Test Author", newBook.getAuthor());
        assertEquals("ISBN should match", "123456789", newBook.getIsbn());

        long finalCount = bookService.getTotalBooks();
        assertEquals("Book count should increase by 1", initialCount + 1, finalCount);

        System.out.println("Book added successfully: " + newBook);
    }

    @Test
    public void testSearchBooksByTitle() {
        System.out.println("Testing searchBooksByTitle...");
        List<Book> books = bookService.searchBooksByTitle("Spring");

        assertNotNull("Search results should not be null", books);
        assertTrue("Should find at least one Spring book", books.size() > 0);

        for (Book book : books) {
            assertTrue("Book title should contain 'Spring'",
                    book.getTitle().toLowerCase().contains("spring"));
        }

        System.out.println("Found " + books.size() + " books with 'Spring' in title");
    }

    @Test
    public void testGetBookById() {
        System.out.println("Testing getBookById...");
        Book book = bookService.getBookById(1L);

        assertNotNull("Book should be found", book);
        assertEquals("Book ID should be 1", Long.valueOf(1L), book.getId());

        System.out.println("Found book: " + book);
    }

    @Test
    public void testGetBookByIdNotFound() {
        System.out.println("Testing getBookById with non-existent ID...");
        Book book = bookService.getBookById(999L);

        assertNull("Book should not be found", book);
        System.out.println("Book with ID 999 not found (expected)");
    }
}