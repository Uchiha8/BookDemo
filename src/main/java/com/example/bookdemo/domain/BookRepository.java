package com.example.bookdemo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book getBookById(Long id);

    Book findBookById(Long id);
}
