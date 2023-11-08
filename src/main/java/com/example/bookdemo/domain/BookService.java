package com.example.bookdemo.domain;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book save(Book book) {
        bookRepository.save(book);
        return book;
    }

    public Book update(Long id, Book book) {
        Book updateBook = bookRepository.findBookById(id);
        updateBook.setName(book.getName());
        updateBook.setAuthor(book.getAuthor());
        updateBook.setDescription(book.getDescription());
        bookRepository.save(updateBook);
        return updateBook;
    }

    public Book getById(Long id) {
        return bookRepository.findBookById(id);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

}

