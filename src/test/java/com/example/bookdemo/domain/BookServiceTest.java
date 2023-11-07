package com.example.bookdemo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookServiceTest {

    @InjectMocks
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllTest() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1L, "Design Pattern", "John Clim", "If you need to improve DP"));
        bookList.add(new Book(2L, "Spring Boot", "ALisher Usmonov", "If you need to improve Spring Book"));
        bookList.add(new Book(3L, "Object Oriented Programming", "Imron Usmonov", "If you need to improve OOP"));

        Mockito.when(bookRepository.findAll()).thenReturn(bookList);

        List<Book> list = bookService.getAll();
        assertNotNull(list);
        assertEquals(3, list.size());
        assertEquals("Spring Boot", list.get(1).getName());
        assertEquals("Imron Usmonov", list.get(2).getAuthor());
    }

    @Test
    public void getById() {
        Long id = 1L;
        Book book = new Book(id, "Design Pattern", "John Clim", "If you need to improve DP");

        Mockito.when(bookRepository.findBookById(id)).thenReturn(book);

        Book result = bookService.getById(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Design Pattern", result.getName());
        assertEquals("John Clim", result.getAuthor());
        assertEquals("If you need to improve DP", result.getDescription());

    }

    @Test
    public void save() {
        Book bookToSave = new Book(1L, "Design Pattern", "John Clim", "If you need to improve DP");

        Mockito.when(bookRepository.save(bookToSave)).thenReturn(bookToSave);

        Book savedBook = bookService.save(bookToSave);
        assertNotNull(savedBook);
        assertEquals(1L, savedBook.getId());
        assertEquals("Design Pattern", savedBook.getName());
        assertEquals("John Clim", savedBook.getAuthor());
        assertEquals("If you need to improve DP", savedBook.getDescription());
    }

    @Test
    void update() {
        Long id = 1L;
        Book existingBook = new Book(id, "Design Pattern", "John Clim", "If you need to improve DP");
        Book updatedBook = new Book(id, "Updated Pattern", "Updated Author", "Updated Description");

        Mockito.when(bookRepository.findBookById(id)).thenReturn(existingBook);
        Mockito.when(bookRepository.save(existingBook)).thenReturn(existingBook);

        Book result = bookService.update(id, updatedBook);
        assertNotNull(result);
        assertEquals("Updated Pattern", result.getName());
        assertEquals("Updated Author", result.getAuthor());
        assertEquals("Updated Description", result.getDescription());

    }

    @Test
    public void deleteById() {
        Long id = 1L;

        Mockito.doNothing().when(bookRepository).deleteById(id);

        bookService.deleteById(id);
    }
}