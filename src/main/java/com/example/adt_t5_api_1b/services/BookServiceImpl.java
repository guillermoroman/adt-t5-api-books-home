package com.example.adt_t5_api_1b.services;

import com.example.adt_t5_api_1b.model.Author;
import com.example.adt_t5_api_1b.model.Book;
import com.example.adt_t5_api_1b.repositories.AuthorRepository;
import com.example.adt_t5_api_1b.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findAllByYearPublished(Integer year) {
        return bookRepository.findAllByPublishedYear(year);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public Book createBook(String title, int yearPublished, Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        Book book = new Book(title, yearPublished, author);
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        this.bookRepository.deleteAll();
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }
}