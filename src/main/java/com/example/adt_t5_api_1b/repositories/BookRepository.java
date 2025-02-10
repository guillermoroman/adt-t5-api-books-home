package com.example.adt_t5_api_1b.repositories;

import com.example.adt_t5_api_1b.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String name);

    List<Book> findAllByPublishedYear(Integer year);
}