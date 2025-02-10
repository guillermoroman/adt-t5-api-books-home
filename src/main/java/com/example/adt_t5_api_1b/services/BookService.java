package com.example.adt_t5_api_1b.services;

import com.example.adt_t5_api_1b.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    List<Book> findAllByYearPublished(Integer year);

    Optional<Book> findById(Long id);
    Optional<Book> findByTitle(String title);

    Book createBook(String title, int yearPublished, Long authorId);


    void deleteById(Long id);
    void deleteAll();

    Book save(Book book);
}