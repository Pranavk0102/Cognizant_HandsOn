package com.library.config;

import com.library.entity.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Autowired
    public DataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists
        if (bookRepository.count() == 0) {
            loadSampleData();
        }
    }

    private void loadSampleData() {
        System.out.println("Loading sample data...");

        // Create sample books
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "978-0-7432-7356-5");
        book1.setGenre("Classic Literature");
        book1.setPublicationYear(1925);
        book1.setAvailable(true);

        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "978-0-06-112008-4");
        book2.setGenre("Classic Literature");
        book2.setPublicationYear(1960);
        book2.setAvailable(true);

        Book book3 = new Book("1984", "George Orwell", "978-0-452-28423-4");
        book3.setGenre("Dystopian Fiction");
        book3.setPublicationYear(1949);
        book3.setAvailable(false);

        Book book4 = new Book("Pride and Prejudice", "Jane Austen", "978-0-14-143951-8");
        book4.setGenre("Romance");
        book4.setPublicationYear(1813);
        book4.setAvailable(true);

        Book book5 = new Book("The Catcher in the Rye", "J.D. Salinger", "978-0-316-76948-0");
        book5.setGenre("Coming-of-age Fiction");
        book5.setPublicationYear(1951);
        book5.setAvailable(true);

        Book book6 = new Book("Java: The Complete Reference", "Herbert Schildt", "978-0-07-180855-9");
        book6.setGenre("Programming");
        book6.setPublicationYear(2020);
        book6.setAvailable(true);

        Book book7 = new Book("Spring Boot in Action", "Craig Walls", "978-1-617-29254-5");
        book7.setGenre("Programming");
        book7.setPublicationYear(2015);
        book7.setAvailable(true);

        Book book8 = new Book("Clean Code", "Robert C. Martin", "978-0-13-235088-4");
        book8.setGenre("Programming");
        book8.setPublicationYear(2008);
        book8.setAvailable(false);

        Book book9 = new Book("The Lord of the Rings", "J.R.R. Tolkien", "978-0-544-00341-5");
        book9.setGenre("Fantasy");
        book9.setPublicationYear(1954);
        book9.setAvailable(true);

        Book book10 = new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", "978-0-7475-3269-9");
        book10.setGenre("Fantasy");
        book10.setPublicationYear(1997);
        book10.setAvailable(true);

        // Save all books
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);
        bookRepository.save(book5);
        bookRepository.save(book6);
        bookRepository.save(book7);
        bookRepository.save(book8);
        bookRepository.save(book9);
        bookRepository.save(book10);

        System.out.println("Sample data loaded successfully!");
        System.out.println("Total books in library: " + bookRepository.count());
    }}