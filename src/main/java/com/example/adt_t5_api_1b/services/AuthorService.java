package com.example.adt_t5_api_1b.services;

import com.example.adt_t5_api_1b.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface AuthorService {

    // READ (GET)
    List<Author> findAll();
    List<Author> findAllByBirthYear(Integer year);

    Optional<Author> findById(Long id);
    Optional<Author> findByName(String name);

    // CREATE (POST)
    Author save (Author author);

    // DELETE (DELETE)
    void deleteById(Long id);
    void deleteAll();

    // UPDATE (PUT)

}
