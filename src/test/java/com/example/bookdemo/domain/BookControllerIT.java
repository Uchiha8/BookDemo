package com.example.bookdemo.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class BookControllerIT {

    @Mock
    BookService bookService;

    @InjectMocks
    BookController bookController;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void readAll() {
        List<Book> list = new ArrayList<>();
        Book book_1 = new Book(1L, "Name1", "Author1", "Description1");
        Book book_2 = new Book(2L, "Name2", "Author2", "Description2");
        Book book_3 = new Book(3L, "Name3", "Author3", "Description3");
        list.add(book_1);
        list.add(book_2);
        list.add(book_3);

        when(bookService.getAll()).thenReturn(list);

        var bookList = bookController.readAll();
        assertNotNull(bookList);

        assertEquals("200 OK", bookList.getStatusCode().toString());
        assertNotNull(bookList);
        assertEquals(3, bookList.getBody().size());
    }

    @Test
    void getById() {
        Long id = 1L;
        Book book = new Book(id, "Design Pattern", "John Clim", "If you need to improve DP");

        when(bookService.getById(id)).thenReturn(book);

        var result = bookController.read(id);

        assertEquals("200 OK", result.getStatusCode().toString());
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Design Pattern", result.getBody().getName());
    }

    @Test
    void save() {
        Book bookToSave = new Book(1L, "Design Pattern", "John Clim", "If you need to improve DP");

        when(bookService.save(bookToSave)).thenReturn(bookToSave);

        var savedBook = bookController.save(bookToSave);
        Assertions.assertNotNull(savedBook);
        assertEquals("200 OK", savedBook.getStatusCode().toString());
        Assertions.assertEquals("Design Pattern", savedBook.getBody().getName());
        Assertions.assertEquals("John Clim", savedBook.getBody().getAuthor());
    }

    @Test
    void update() {
        Long id = 1L;
        Book existingBook = new Book(id, "Design Pattern", "John Clim", "If you need to improve DP");
        Book updatedBook = new Book(id, "Updated Pattern", "Updated Author", "Updated Description");

        when(bookService.getById(id)).thenReturn(existingBook);
        when(bookService.save(existingBook)).thenReturn(existingBook);

        var result = bookController.update(id, updatedBook);
        assertEquals("200 OK", result.getStatusCode().toString());
        Assertions.assertNotNull(result);
    }

    @Test
    void deleteById() {
        Long id = 1L;

        doNothing().when(bookService).deleteById(id);
        assertEquals(null, bookService.getById(id));
        bookController.delete(id);
    }
}