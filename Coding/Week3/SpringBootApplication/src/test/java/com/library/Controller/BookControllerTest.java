package com.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.entity.Book;
import com.library.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureWebMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Test
    public void testGetAllBooks() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCreateBook() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Book book = new Book("Test Book", "Test Author", "978-1-234-56789-0");
        book.setGenre("Test Genre");
        book.setPublicationYear(2023);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Book"))
                .andExpect(jsonPath("$.author").value("Test Author"))
                .andExpected(jsonPath("$.isbn").value("978-1-234-56789-0"));
    }

    @Test
    public void testGetBookById() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // Create a book first
        Book book = new Book("Test Book", "Test Author", "978-1-234-56789-0");
        Book savedBook = bookRepository.save(book);

        mockMvc.perform(get("/api/books/" + savedBook.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Book"))
                .andExpect(jsonPath("$.author").value("Test Author"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // Create a book first
        Book book = new Book("Original Title", "Original Author", "978-1-234-56789-0");
        Book savedBook = bookRepository.save(book);

        // Update the book
        savedBook.setTitle("Updated Title");
        savedBook.setAuthor("Updated Author");

        mockMvc.perform(put("/api/books/" + savedBook.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(savedBook)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.author").value("Updated Author"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // Create a book first
        Book book = new Book("Test Book", "Test Author", "978-1-234-56789-0");
        Book savedBook = bookRepository.save(book);

        mockMvc.perform(delete("/api/books/" + savedBook.getId()))
                .andExpect(status().isNoContent());

        // Verify the book is deleted
        mockMvc.perform(get("/api/books/" + savedBook.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSearchBooks() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // Create a book first
        Book book = new Book("Spring Boot Guide", "John Doe", "978-1-234-56789-0");
        bookRepository.save(book);

        mockMvc.perform(get("/api/books/search")
                        .param("keyword", "Spring"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Spring Boot Guide"));
    }
}